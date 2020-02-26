package more.ftf.zookeeper.nameservice;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author a8079
 * @title: IdMaker
 * @projectName nio
 * @description: TODO
 * @date 2020/1/220:09
 */
public class IdMaker {

    private ZkClient client = null;
    private final String server;
    // zookeeper顺序节点的父节点
    private final String root;
    // 顺序节点的名称
    private final String nodeName;
    // 标识当前服务是否正在运行
    private volatile boolean running = false;
    private ExecutorService cleanExector = null;

    public enum RemoveMethod{
        NONE,IMMEDIATELY,DELAY

    }

    public IdMaker(String zkServer,String root,String nodeName){

        this.root = root;
        this.server = zkServer;
        this.nodeName = nodeName;

    }

    // 启动服务
    public void start() throws Exception {

        if (running)
            throw new Exception("server has stated...");
        running = true;

        init();

    }

    // 停止服务
    public void stop() throws Exception {

        if (!running)
            throw new Exception("server has stopped...");
        running = false;

        freeResource();

    }

    // 初始化服务资源
    private void init(){

        client = new ZkClient(server,5000,5000,new BytesPushThroughSerializer());

//        client.readData("", new Stat());

        cleanExector = Executors.newFixedThreadPool(10);
        try{
            client.createPersistent(root,true);
        }catch (ZkNodeExistsException e){
            //ignore;
        }

    }

    // 释放服务器资源
    private void freeResource(){

        // 释放线程池
        cleanExector.shutdown();
        try{
            cleanExector.awaitTermination(2, TimeUnit.SECONDS);
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally{
            cleanExector = null;
        }

        if (client!=null){
            client.close();
            client=null;

        }
    }

    // 检测当前服务是否正在运行
    private void checkRunning() throws Exception {
        if (!running)
            throw new Exception("请先调用start");

    }

    // 从顺序节点名中提取我们要的ID值
    private String ExtractId(String str){
        int index = str.lastIndexOf(nodeName);
        if (index >= 0){
            index+=nodeName.length();
            return index <= str.length()?str.substring(index):"";
        }
        return str;

    }

    // 生成ID
    public String generateId(RemoveMethod removeMethod) throws Exception{
        checkRunning();

        // 构造顺序节点的完整路径
        final String fullNodePath = root.concat("/").concat(nodeName);
        // 创建持久化顺序节点
        final String ourPath = client.createPersistentSequential(fullNodePath, null);


        System.out.println(ourPath);

        // 避免zookeeper的顺序节点暴增，直接删除掉刚创建的顺序节点
        if (removeMethod.equals(RemoveMethod.IMMEDIATELY)){ // 立即删除
            client.delete(ourPath);
        }else if (removeMethod.equals(RemoveMethod.DELAY)){ // 延迟删除
            cleanExector.execute(new Runnable() { // 用线程池执行删除，让generateId()方法尽快返回
                public void run() {
                    client.delete(ourPath);
                }
            });

        }
        //node-0000000000, node-0000000001
        return ExtractId(ourPath);
    }
}
