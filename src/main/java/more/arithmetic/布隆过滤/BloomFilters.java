package more.arithmetic.布隆过滤;

import org.junit.Assert;

/**
 * @ClassName BloomFilters
 * @Description
 *
 * Google Guava
 *     public void guavaTest() {
 *         long l = System.currentTimeMillis();
 *         BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(), 10000000, 0.01);
 *         for (int i = 0; i < 10000000; i++) {
 *             filter.put(i);
 *         }
 *         Assert.assertTrue(filter.mightContain(1));
 *         long s = System.currentTimeMillis();
 *         System.out.println("执行时间: " + (s - l));
 *     }
 *
 * @Author root
 * @Date 18-11-30 下午3:22
 * @Version 1.0
 **/
public class BloomFilters {

    private int arraySize;
    private int[] array;

    public BloomFilters(int arraySize) {
        this.arraySize = arraySize;
        array = new int[arraySize];
    }

    public static void main(String[] args) {
        bloomFiltersTest();
    }
    public static void bloomFiltersTest() {
        long start = System.currentTimeMillis();
        BloomFilters bloomFilters = new BloomFilters(10000000);
        for (int i = 0; i < 10000000; i++) {
            bloomFilters.add(i + "");
        }

        Assert.assertTrue(bloomFilters.check(1 + ""));
        Assert.assertTrue(bloomFilters.check(2 + ""));
        Assert.assertTrue(bloomFilters.check(3 + ""));
        Assert.assertTrue(bloomFilters.check(99999 + ""));
        Assert.assertFalse(bloomFilters.check(400230340 + ""));

        long end = System.currentTimeMillis();
        System.out.println("执行时间: " + (end - start));
    }

    public void add (String key) {
        int first = hashcode_1(key);
        int second = hashcode_2(key);
        int third = hashcode_3(key);

        array[first % arraySize] = 1;
        array[second % arraySize] = 1;
        array[third % arraySize] = 1;
    }

    public boolean check(String key) {
        int first = hashcode_1(key);
        int second = hashcode_2(key);
        int third = hashcode_3(key);

        int firstIndex = array[first % arraySize];
        if (firstIndex == 0) {
            return false;
        }
        int secondIndex = array[second % arraySize];
        if (secondIndex == 0) {
            return false;
        }
        int thirdIndex = array[third % arraySize];
        if (thirdIndex == 0) {
            return false;
        }
        return true;
    }

    private int hashcode_1(String key) {
        int hash = 0;
        int i;
        for (i = 0; i < key.length(); ++i) {
            hash = 33 * hash + key.charAt(i);
        }
        return Math.abs(hash);
    }

    private int hashcode_2(String data) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < data.length(); ++i) {
            hash = (hash ^ data.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        return Math.abs(hash);
    }

    private int hashcode_3(String key) {
        int hash, i;
        for (hash = 0, i = 0; i < key.length(); ++i) {
            hash += key.charAt(i);
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);

        return Math.abs(hash);
    }


}
