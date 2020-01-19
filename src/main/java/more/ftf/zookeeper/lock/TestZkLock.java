package more.ftf.zookeeper.lock;

import org.I0Itec.zkclient.ZkClient;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author a8079
 * @title: TestZkLock
 * @projectName nio
 * @description: TODO
 * @date 2020/1/1715:57
 */
public class TestZkLock {


    @Test
    public void test0 () {
        ZkClient client = new ZkClient("192.168.56.104:2181");

        String ephemeralSequentia1l1 = client.createEphemeralSequential("/sort/1", null);
        System.out.println(ephemeralSequentia1l1);

        String ephemeralSequential2 = client.createEphemeralSequential("/sort/1", null);
        System.out.println(ephemeralSequential2);

        List<String> children = client.getChildren("/sort");
        children.forEach(a-> {
            System.out.println(a);
        });
        Collections.sort(
                children, new Comparator<String>() {
                    public int compare(String lhs, String rhs) {
                        return getLockNodeNumber(lhs, "LOCK-").compareTo(getLockNodeNumber(rhs, "LOCK-"));
                    }
                }
        );
        children.forEach(a-> {
            System.out.println(a);
        });

    }

    private String getLockNodeNumber(String str, String lockName) {
        System.out.println(str + ":" + lockName);
        int index = str.lastIndexOf(lockName);
        if ( index >= 0 ) {
            index += lockName.length();
            return index <= str.length() ? str.substring(index) : "";
        }
        return str;
    }
}
