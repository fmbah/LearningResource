package io.netty.example.阻塞IO实例;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName BIOServer1
 * @Description
 * @Author root
 * @Date 18-11-15 下午2:48
 * @Version 1.0
 **/
public class BIOServer1 {

    private static class Server {
        private ServerSocket server = null;

        public Server (int port) {
            try {

                server = new ServerSocket(port);
                while (true) {
                    Socket socket = null;
                    InputStream in = null;
                    OutputStream out = null;


                    socket = server.accept();
                    System.out.println("establish a connection....");

                    in = socket.getInputStream();
                    out = socket.getOutputStream();

                    //将链接转到线程中进行处理
                    new ClientHandler(socket, in, out).start();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {

            }
        }
    }

    private static class ClientHandler extends Thread{
        private Socket socket = null;
        private InputStream in = null;
        private OutputStream out = null;

        public ClientHandler (Socket socket, InputStream in, OutputStream out) {
            this.socket = socket;
            this.in = in;
            this.out = out;
        }

        @Override
        public void run() {
            super.run();

            try {
                byte[] bytes = new byte[10];
                int read = in.read(bytes);
                String msg = "";
                while (read != -1) {

                    msg = new String(bytes, 0, read);
                    System.out.println(msg + (msg.equals("exit\n")));
                    if (msg.equals("exit")) {
                        break;
                    } else {
                        System.out.println(Thread.currentThread().getName() + "客户端消息: " + msg);
//                        out.write(("server copy, 客户端消息: " + msg).getBytes());
//                        out.flush();
                    }

                    read = in.read(bytes);
                }
                System.out.println("开始销毁流对象");
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }

        }
    }

    public static void main (String args[]) {
        new Server(8007);
    }
}
