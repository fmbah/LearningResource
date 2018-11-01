package more.thread.test.map数据结构;

import java.util.*;

/**
 * @ClassName M1
 * @Description
 * @Author root
 * @Date 18-10-31 下午4:52
 * @Version 1.0
 **/
public class M1 {
    public static void main(String args[]) {
        HashMap<String, String> map = new HashMap<String, String>();
        String putRs = map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");
        map.put("k4", "v4");
        map.put("k5", "v5");
        map.put("k6", "v6");
        
        String putRs1 = map.putIfAbsent("k1", "v2");
        System.out.println(putRs + "==" + putRs1);
        Set<String> strings = map.keySet();
        System.out.println(map.getOrDefault("K2", "默认值"));
        System.out.println(strings.toString() + "==" + map.values().toString());

        int k2 = Objects.hashCode("k2");
        int v2 = Objects.hashCode("v2");
        System.out.println(k2 + "==" + (3340 & 5));
        int i = k2 ^ v2;
        System.out.println(26>>>3);

        Test<String> test = new Test<String>();
        test.setV("a");
        System.out.println(test.getV());

        Map<String, String> stringStringMap = Collections.synchronizedMap(new HashMap<String, String>());

        int h;
        h = Objects.hash(5);
        int i1 = 999999999>>> 16;
        System.out.println(h + "==" + i1);
        System.out.println((1 ^ 8) + "==" + (35 & 8));
        System.out.println(3 % 11);
        System.out.println(10 % 4);

    }
}

class Test<V> {
    private V v;

    public V getV() {
        return v;
    }

    public void setV(V v) {
        this.v = v;
    }
}

