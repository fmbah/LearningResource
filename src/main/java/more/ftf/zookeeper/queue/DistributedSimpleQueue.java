package more.ftf.zookeeper.queue;

/**
 * @author a8079
 * @title: DistributedSimpleQueue
 * @projectName nio
 * @description: TODO
 * @date 2020/1/1717:03
 */

import org.I0Itec.zkclient.ExceptionUtil;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNoNodeException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 简单分布式队列
 */
public class DistributedSimpleQueue<T> {

    protected final ZkClient zkClient;
    // queue节点
    protected final String root;
    // 顺序节点前缀
    protected static final String Node_NAME = "n_";


    public DistributedSimpleQueue(ZkClient zkClient, String root) {
        this.zkClient = zkClient;
        this.root = root;
    }

    // 判断队列大小
    public int size() {
        return zkClient.getChildren(root).size();
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return zkClient.getChildren(root).size() == 0;
    }

    // 向队列提供数据
    public boolean offer(T element) throws Exception{

        // 创建顺序节点
        String nodeFullPath = root .concat( "/" ).concat( Node_NAME );
        try {
            zkClient.createPersistentSequential(nodeFullPath , element);
        }catch (ZkNoNodeException e) {
            zkClient.createPersistent(root);
            offer(element);
        } catch (Exception e) {
            throw ExceptionUtil.convertToRuntimeException(e);
        }
        return true;
    }


    // 从队列取数据
    public T poll() throws Exception {

        try {

            // 获取所有顺序节点
            List<String> list = zkClient.getChildren(root);
            if (list.size() == 0) {
                return null;
            }

            // 排序
            Collections.sort(list, new Comparator<String>() {
                public int compare(String lhs, String rhs) {
                    return getNodeNumber(lhs, Node_NAME).compareTo(getNodeNumber(rhs, Node_NAME));
                }
            });

            // 循环每个顺序节点名
            for ( String nodeName : list ){

                // 构造出顺序节点的完整路径
                String nodeFullPath = root.concat("/").concat(nodeName);
                try {
                    // 读取顺序节点的内容
                    T node = (T) zkClient.readData(nodeFullPath);
                    // 删除顺序节点
                    zkClient.delete(nodeFullPath);
                    return node;
                } catch (ZkNoNodeException e) {
                    // ignore 由其他客户端把这个顺序节点消费掉了
                }
            }

            return null;

        } catch (Exception e) {
            throw ExceptionUtil.convertToRuntimeException(e);
        }

    }

    private String getNodeNumber(String str, String nodeName) {
        int index = str.lastIndexOf(nodeName);
        if (index >= 0) {
            index += Node_NAME.length();
            return index <= str.length() ? str.substring(index) : "";
        }
        return str;

    }

}
