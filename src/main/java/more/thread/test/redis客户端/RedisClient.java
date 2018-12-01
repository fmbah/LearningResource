package more.thread.test.redis客户端;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @ClassName RedisClient
 * @Description
 *
 * 参考; https://redis.io/topics/protocol
 *      http://redisdoc.com/topic/protocol.html
 *
 *
 * @Author root
 * @Date 18-11-30 下午4:34
 * @Version 1.0
 **/
public class RedisClient {


    private Socket socket;
    private OutputStream write;
    private InputStream read;

    public RedisClient(String host,int port) throws IOException {
        socket = new Socket(host,port);
        write = socket.getOutputStream();
        read = socket.getInputStream();
    }
    //set方法存数据
    public void set(String key, String val) throws IOException {
        StringBuffer sb = new StringBuffer();
        sb.append("*3").append("\r\n");//客户端将发送3段内容

        sb.append("$3").append("\r\n");//第一段内容（字符串）长度为3
        sb.append("SET").append("\r\n");//第一段的具体内容

        sb.append("$").append(key.getBytes().length).append("\r\n");//第二段内容（字符串）长度为key.getBytes().length
        sb.append(key).append("\r\n");//第二个参数内容

        sb.append("$").append(val.getBytes().length).append("\r\n");//第三段内容（字符串）长度为val.getBytes().length
        sb.append(val).append("\r\n");//第三个参数内容

        write.write(sb.toString().getBytes());
        byte[] bytes = new byte[1024];
        int read = this.read.read(bytes);
        System.out.println("set---------------------------------------------" + read);
        System.out.println("命令返回值: " + new String(bytes, 0, read, "utf-8"));
    }

    //get方法取数据
    public void get(String key) throws IOException {
        StringBuffer sb = new StringBuffer();
        sb.append("*2").append("\r\n");//代表2个参数

        sb.append("$3").append("\r\n");//第一个参数长度
        sb.append("GET").append("\r\n");//第一个参数的内容

        sb.append("$").append(key.getBytes().length).append("\r\n");//第二个参数长度
        sb.append(key).append("\r\n");//第二个参数内容

        write.write(sb.toString().getBytes());
        byte[] bytes = new byte[1024];
        int read = this.read.read(bytes);
        System.out.println("get---------------------------------------------" + read);
        System.out.println("命令返回值: " + new String(bytes, 0, read, "utf-8") +"<===");
    }

    public static void main(String args[]) throws IOException {

        RedisClient jedis = new RedisClient("10.220.110.228",6379);
        jedis.set("redisclient","value");
        jedis.get("redisclient");
    }
}
