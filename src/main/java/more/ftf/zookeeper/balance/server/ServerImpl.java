package more.ftf.zookeeper.balance.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

/**
 * @author a8079
 * @title: ServerImpl
 * @projectName nio
 * @description: TODO
 * @date 2020/1/1013:56
 */
public class ServerImpl implements Server {

    private EventLoopGroup bossGroup = new NioEventLoopGroup();
    private EventLoopGroup workGroup = new NioEventLoopGroup();
    private ServerBootstrap bootStrap = new ServerBootstrap();
    private ChannelFuture cf;
    private String zkAddress;
    private String serversPath;
    private String currentServerPath;
    private ServerData sd;

    private volatile boolean binded = false;

    private final ZkClient zc;
    private final RegistProvider registProvider;

    private static final Integer SESSION_TIME_OUT = 10000;
    private static final Integer CONNECT_TIME_OUT = 10000;



    public String getCurrentServerPath() {
        return currentServerPath;
    }

    public String getZkAddress() {
        return zkAddress;
    }

    public String getServersPath() {
        return serversPath;
    }

    public ServerData getSd() {
        return sd;
    }

    public void setSd(ServerData sd) {
        this.sd = sd;
    }

    public ServerImpl(String zkAddress, String serversPath, ServerData sd){
        this.zkAddress = zkAddress;
        this.serversPath = serversPath;
        this.zc = new ZkClient(this.zkAddress,SESSION_TIME_OUT,CONNECT_TIME_OUT,new SerializableSerializer());
        this.registProvider = new DefaultRegistProvider();
        this.sd = sd;
    }

    //初始化服务端
    private void initRunning() throws Exception {
        String mePath = serversPath.concat("/").concat(sd.getPort().toString());
        //注册到zookeeper
        registProvider.regist(new ZooKeeperRegistContext(mePath,zc,sd));
        currentServerPath = mePath;
    }

    public void bind() {

        if (binded){
            return;
        }

        System.out.println(sd.getPort()+":binding...");

        try {
            initRunning();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        bootStrap.group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new ServerHandler(new DefaultBalanceUpdateProvider(currentServerPath,zc)));
                    }
                });

        try {
            cf =  bootStrap.bind(sd.getPort()).sync();
            binded = true;
            System.out.println(sd.getPort()+":binded...");
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }

}
