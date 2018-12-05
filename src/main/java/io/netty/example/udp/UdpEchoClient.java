package io.netty.example.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @ClassName UdpEchoClient
 * @Description
 * @Author root
 * @Date 18-12-4 上午11:46
 * @Version 1.0
 **/
public class UdpEchoClient {

    private DatagramSocket socket;
    private InetAddress address;

    private byte[] buf;

    public UdpEchoClient() throws Exception{
        socket = new DatagramSocket();
        address = InetAddress.getByName("localhost");
    }

    public String sendEcho(String msg) throws Exception{
        buf = msg.getBytes();
        DatagramPacket packet
                = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(
                packet.getData(), 0, packet.getLength());
        return received;
    }

    public void close() {
        socket.close();
    }

}
