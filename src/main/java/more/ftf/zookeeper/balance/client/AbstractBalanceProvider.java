package more.ftf.zookeeper.balance.client;

import java.util.List;

/**
 * @author a8079
 * @title: AbstractBalanceProvider
 * @projectName nio
 * @description: TODO
 * @date 2020/1/1015:12
 */
public abstract class AbstractBalanceProvider<T> implements BalanceProvider<T> {

    protected abstract T balanceAlgorithm(List<T> items);
    protected abstract List<T> getBalanceItems();

    public T getBalanceItem(){
        return balanceAlgorithm(getBalanceItems());
    }

}
