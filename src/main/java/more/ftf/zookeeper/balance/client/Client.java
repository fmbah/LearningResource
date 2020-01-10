package more.ftf.zookeeper.balance.client;

/**
 * @author a8079
 * @title: Client
 * @projectName nio
 * @description: TODO
 * @date 2020/1/1015:10
 */
public interface Client {

    // 连接服务器
    public void connect() throws Exception;
    // 断开服务器
    public void disConnect() throws Exception;

}