package more.ftf.zookeeper.my_balance.server;

import java.io.Serializable;

/**
 * @author fb
 * @title: ServerData
 * @projectName nio
 * @description: 会话流转对象：balance port path
 * @date 2020/2/2511:25
 */
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
