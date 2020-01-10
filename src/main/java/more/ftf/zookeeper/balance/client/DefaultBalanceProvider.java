package more.ftf.zookeeper.balance.client;

import more.ftf.zookeeper.balance.server.ServerData;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author a8079
 * @title: DefaultBalanceProvider
 * @projectName nio
 * @description: TODO
 * @date 2020/1/1015:12
 */
public class DefaultBalanceProvider extends AbstractBalanceProvider<ServerData> {

    private final String zkServer; // zookeeper服务器地址
    private final String serversPath; // servers节点路径
    private final ZkClient zc;

    private static final Integer SESSION_TIME_OUT = 10000;
    private static final Integer CONNECT_TIME_OUT = 10000;

    public DefaultBalanceProvider(String zkServer, String serversPath) {
        this.serversPath = serversPath;
        this.zkServer = zkServer;

        this.zc = new ZkClient(this.zkServer, SESSION_TIME_OUT, CONNECT_TIME_OUT,
                new SerializableSerializer());

    }

    @Override
    protected ServerData balanceAlgorithm(List<ServerData> items) {
        if (items.size()>0){
            Collections.sort(items); // 根据负载由小到大排序
            return items.get(0); // 返回负载最小的那个
        }else{
            return null;
        }
    }

    /**
     * 从zookeeper中拿到所有工作服务器的基本信息
     */
    @Override
    protected List<ServerData> getBalanceItems() {

        List<ServerData> sdList = new ArrayList<ServerData>();
        List<String> children = zc.getChildren(this.serversPath);
        for(int i=0; i<children.size();i++){
            ServerData sd = zc.readData(serversPath+"/"+children.get(i));
            sdList.add(sd);
        }
        return sdList;

    }

}
