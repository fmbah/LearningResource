package more.ftf.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author a8079
 * @title: TestRedis
 * @projectName nio
 * @description: TODO
 * @date 2020/2/2514:33
 */
public class TestRedis {


    @Test
    public void testSetNx() {
        JedisPool jedisPool = new JedisPool("192.168.56.105", 16379);

        Jedis jedis = jedisPool.getResource();

        String nxKey = "nx:s0";
        String nxVal = "nx:v0";
        Long setnx = jedis.setnx(nxKey, nxVal);
        System.out.println(setnx);

    }

}
