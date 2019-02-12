package io.netty.example;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName NioTest
 * @Description
 * @Author root
 * @Date 19-1-2 下午7:29
 * @Version 1.0
 **/
public class NioTest {
    public static void main(String[] args) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream("");
        FileChannel outputStreamChannel = fileOutputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(10);
        outputStreamChannel.write(buffer);
    }
}
