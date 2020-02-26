package more.ftf.zookeeper.my_balance.server;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkBadVersionException;
import org.apache.zookeeper.data.Stat;

/**
 * @author a8079
 * @title: ServerHandler
 * @projectName nio
 * @description:
 * @date 2020/2/2511:42
 */
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
