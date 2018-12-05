package io.netty.example.udp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @ClassName UdpTest
 * @Description
 * @Author root
 * @Date 18-12-4 上午11:48
 * @Version 1.0
 **/
public class UdpTest {
    UdpEchoClient client;

    @Before
    public void setup(){
        try {
            new UdpEchoServer().start();
            client = new UdpEchoClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenCanSendAndReceivePacket_thenCorrect() {
        String echo = null;
        try {
            echo = client.sendEcho("hello server");
            assertEquals("hello server", echo);
            echo = client.sendEcho("server is working");
            assertFalse(echo.equals("hello server"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        try {
            client.sendEcho("end");
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
