package more.thread.test.设计模式.单例模式;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName D6单例模式enum枚举数据类型
 * @Description
 * @Author root
 * @Date 18-11-10 上午10:40
 * @Version 1.0
 **/
public class D6单例模式enum枚举数据类型 {
    public static void main (String args[]) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
//                System.out.println(MyObject6.connectionFactory.getJedisPool().hashCode());
                System.out.println(MyObject6_1.getConnection());
            }
        };

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runnable);
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(8);
        jedisPoolConfig.setMaxWaitMillis(-1);
        //redis-13079.c61.us-east-1-3.ec2.cloud.redislabs.com:13079
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "redis-13079.c61.us-east-1-3.ec2.cloud.redislabs.com", 13079, 0, "9ZPbQnCGY6LlZFU0UqhHTSOU2FGv6ppl", 0);

        System.out.println("jedisPool :" + jedisPool);
    }

}

/**
 * @Auther: Fmbah
 * @Date: 18-11-10 上午11:03
 * @Description:  以下的设计违反了职责单一原则
 */
enum MyObject6 {
    connectionFactory;

    private JedisPool jedisPool;

    private MyObject6 () {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(8);
        jedisPoolConfig.setMaxWaitMillis(-1);
        //redis-13079.c61.us-east-1-3.ec2.cloud.redislabs.com:13079
        jedisPool = new JedisPool(jedisPoolConfig, "redis-13079.c61.us-east-1-3.ec2.cloud.redislabs.com", 13079, 0, "9ZPbQnCGY6LlZFU0UqhHTSOU2FGv6ppl", 0);
    }

    public JedisPool getJedisPool () {
        return jedisPool;
    }
}

class MyObject6_1 {

    private enum MyEnumSingleton {
        connectionFactory;

        private JedisPool jedisPool;

        private MyEnumSingleton () {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxIdle(8);
            jedisPoolConfig.setMaxWaitMillis(-1);
            //redis-13079.c61.us-east-1-3.ec2.cloud.redislabs.com:13079
            jedisPool = new JedisPool(jedisPoolConfig, "redis-13079.c61.us-east-1-3.ec2.cloud.redislabs.com", 13079, 0, "9ZPbQnCGY6LlZFU0UqhHTSOU2FGv6ppl", 0);
        }

        public JedisPool getJedisPool () {
            return jedisPool;
        }
    }

    public static JedisPool getConnection () {
        return MyEnumSingleton.connectionFactory.getJedisPool();
    }
}