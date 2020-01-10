package more.ftf.zookeeper.balance.server;

/**
 * @author a8079
 * @title: RegistProvider
 * @projectName nio
 * @description: TODO
 * @date 2020/1/1014:35
 */
public interface RegistProvider {

    public void regist(Object context) throws Exception;

    public void unRegist(Object context) throws Exception;

}
