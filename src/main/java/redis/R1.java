package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

/**
 * @ClassName R1
 * @Description
 * @Author root
 * @Date 18-11-7 下午3:07
 * @Version 1.0
 **/
public class R1 {

    public static void main (String argsp[]) {
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(8);
//        jedisPoolConfig.setMaxWaitMillis(-1);
//        //redis-13079.c61.us-east-1-3.ec2.cloud.redislabs.com:13079
//        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "redis-13079.c61.us-east-1-3.ec2.cloud.redislabs.com", 13079, 0, "9ZPbQnCGY6LlZFU0UqhHTSOU2FGv6ppl", 0);
//
//        try (Jedis jedis = jedisPool.getResource()) {
//            for (int i = 0; i < Integer.MAX_VALUE; i++) {
//                Set<String> keys = jedis.keys("*");
//                for (String key : keys) {
//                    System.out.println(key + "==" + jedis.get(key));
//                }
//            }
//        }

        System.out.println(null + "-" + "123");
    }

}
