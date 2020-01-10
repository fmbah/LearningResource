package more.ftf.zookeeper.balance.server;

/**
 * @author a8079
 * @title: BalanceUpdateProvider
 * @projectName nio
 * @description: TODO
 * @date 2020/1/1014:38
 */
public interface BalanceUpdateProvider {

    // 增加负载
    public boolean addBalance(Integer step);

    // 减少负载
    public boolean reduceBalance(Integer step);

}
