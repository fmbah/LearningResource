package more.ftf.zookeeper.balance.server;

import org.I0Itec.zkclient.ZkClient;

/**
 * @author a8079
 * @title: ZooKeeperRegistContext
 * @projectName nio
 * @description: TODO
 * @date 2020/1/1014:37
 */
public class ZooKeeperRegistContext {

    private String path;
    private ZkClient zkClient;
    private Object data;

    public ZooKeeperRegistContext(String path, ZkClient zkClient, Object data) {
        super();
        this.path = path;
        this.zkClient = zkClient;
        this.data = data;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public ZkClient getZkClient() {
        return zkClient;
    }
    public void setZkClient(ZkClient zkClient) {
        this.zkClient = zkClient;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

}
