package more.ftf.zookeeper.my_balance.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fb
 * @title: ServerBalanceRunner
 * @projectName nio
 * @description:
 * @date 2020/2/2510:59
 */
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
