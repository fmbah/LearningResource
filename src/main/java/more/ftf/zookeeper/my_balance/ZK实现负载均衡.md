### ZK 实现负载均衡

##### 服务端
1. 
````
public class ServerHandler extends SimpleChannelInboundHandler<ServerData> {

    private ServerData serverData;
    private ZkClient client;
    public ServerHandler(ZkClient client, ServerData serverData) {
        this.client = client;
        this.serverData = serverData;
    }
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ServerData serverData) throws Exception {
        System.out.println("建立连接...." + JSONObject.toJSONString(serverData));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);

        System.out.println("建立连接...." + JSONObject.toJSONString(serverData));

        Stat stat = new Stat();
        while (true) {
            try {

                ServerData sd = (ServerData)client.readData(this.serverData.getPath(), stat);

                sd.setBalance(sd.getBalance() + 1);

                client.writeData(this.serverData.getPath(), sd, stat.getVersion());

                System.out.println("加权成功：" + JSONObject.toJSONString(sd));

                break;
            } catch (ZkBadVersionException e) {
                e.printStackTrace();
                // 重试
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);

        System.out.println("断开连接...." + JSONObject.toJSONString(serverData));

        Stat stat = new Stat();
        while (true) {
            try {

                ServerData sd = (ServerData)client.readData(this.serverData.getPath(), stat);

                sd.setBalance(sd.getBalance() >  1 ? sd.getBalance() -  1 : 0);

                client.writeData(this.serverData.getPath(), sd, stat.getVersion());

                System.out.println("降权成功：" + JSONObject.toJSONString(sd));

                break;
            } catch (ZkBadVersionException e) {
                e.printStackTrace();
                // 重试
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
````
````
public class ServerData implements Serializable {

    private String path;
    private int balance;
    private int port;

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }
}
````
````
public class ServerBalanceRunner {


    private static final ZkClient client = new ZkClient("192.168.56.105:2181", 5000, 5000, new SerializableSerializer());
    private static final String path = "/my_balance/";

    public static void main(String[] args) {
        try {
            client.createPersistent(path.substring(0, path.lastIndexOf("/")));
        } catch (Exception e) {

        }

        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            final int tmpI = i;
            Thread thread = new Thread(() -> {
                ServerData serverData = new ServerData();
                serverData.setBalance(1);
                serverData.setPort(10800 + tmpI);
                serverData.setPath(path + 10800 + "" + tmpI);

                client.createEphemeral(serverData.getPath(), serverData);

                ServerBootstrap bootstrap = new ServerBootstrap();
                NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
                NioEventLoopGroup workGroup = new NioEventLoopGroup(1);
                bootstrap.group(bossGroup, workGroup)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel socketChannel) throws Exception {
                                socketChannel.pipeline().addLast(new ServerHandler(client, serverData));
                            }
                        });

                try {
                    ChannelFuture channelFuture = bootstrap.bind(10800 + tmpI).sync();

                    System.out.println("启动服务器：" + serverData.getPort());

                    channelFuture.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    bossGroup.shutdownGracefully();
                    workGroup.shutdownGracefully();
                }

            });

            threadList.add(thread);
            thread.start();
        }

        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}

````


##### 客户端
````
public class ClientHandler extends ChannelHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.channel().closeFuture().sync();
    }
}
````
````
public class ClientBalanceRunner {


    private static final ZkClient client = new ZkClient("192.168.56.105:2181", 5000, 5000, new SerializableSerializer());
    private static final String path = "/my_balance/";


    public static void main(String[] args) {

        // 获取服务端权最小的服务器
        List<String> children = client.getChildren(path.substring(0, path.lastIndexOf("/")));
        List<ServerData> sds = new ArrayList<>();
        for (String c : children) {
            ServerData sd = (ServerData)client.readData(path + c);
            sds.add(sd);
        }
        sds.sort(Comparator.comparingInt(ServerData::getBalance));
        // 客户端NIO连接服务端
        ServerData serverData = sds.get(0);

        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup workGroup = new NioEventLoopGroup(1);
        bootstrap.group(workGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new ClientHandler());
            }
        });

        try {
            ChannelFuture channelFuture = bootstrap.connect("localhost", serverData.getPort()).sync();
            System.out.println("客户端连接成功：" + JSONObject.toJSONString(serverData));
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
        }

    }

}
````