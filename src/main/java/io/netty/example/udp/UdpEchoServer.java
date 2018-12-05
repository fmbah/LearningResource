package io.netty.example.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @ClassName UdpEchoServer
 * @Description
 * @Author root
 * @Date 18-12-4 上午11:38
 * @Version 1.0
 **/
public class UdpEchoServer extends Thread{

    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[256];

    public UdpEchoServer() {
        try {
            socket = new DatagramSocket(4445);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        running = true;

        try {
            while (running) {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);

                socket.receive(packet);

                InetAddress address = packet.getAddress();
                int port =  packet.getPort();

                packet = new DatagramPacket(buf, buf.length, address, port);

                String received = new String(packet.getData(), 0, packet.getLength());

                if (received.equals("end")) {
                    running =false;
                    continue;
                }
                socket.send(packet);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
