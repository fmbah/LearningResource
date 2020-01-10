package more.ftf.zookeeper.balance.client;

/**
 * @author a8079
 * @title: BalanceProvider
 * @projectName nio
 * @description: TODO
 * @date 2020/1/1015:12
 */
public interface BalanceProvider<T> {

    public T getBalanceItem();

}
