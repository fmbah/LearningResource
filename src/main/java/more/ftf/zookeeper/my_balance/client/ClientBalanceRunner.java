package more.ftf.zookeeper.my_balance.client;

import com.alibaba.fastjson.JSONObject;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import more.ftf.zookeeper.my_balance.server.ServerData;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author fb
 * @title: ClientBalanceRunner
 * @projectName nio
 * @description:
 * @date 2020/2/2513:42
 */
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
