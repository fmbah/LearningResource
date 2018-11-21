package io.netty.example.阻塞IO实例;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName BIOServer0
 * @Description
 * @Author root
 * @Date 18-11-15 下午1:47
 * @Version 1.0
 **/
public class BIOServer0 {

    private static class Server {
        //initialize socket and inputStream
        private Socket socket = null;
        private ServerSocket server = null;
        private DataInputStream in = null;
        private InputStream inputStream = null;
        private DataOutputStream out = null;
        private OutputStream outputStream = null;

        public Server (int port) {
            //start servers and wait for a connection
            try {
                server = new ServerSocket(port);
                System.out.println("Server started.....");

                System.out.println("Waiting for a client.....");

                socket = server.accept();
                System.out.println("Client accepted....");

                //takes input from the client socket
//                in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                inputStream = socket.getInputStream();

//                String line = "";
//                out = new DataOutputStream(socket.getOutputStream());
                outputStream = socket.getOutputStream();
                //reads messages from client until "Over" is sent
//                while (!line.equals("Over")) {
//                    if (in != null) {
//                        line = in.readUTF();
//
//                        System.out.println("客户端信息: " + line);
//
//                        out.writeUTF("server 收到了....你的消息是: " + line);
//                    }
//                }

                byte[] bytes = new byte[10];
                int read = inputStream.read(bytes);
                while (read != -1) {
                    String msg = new String(bytes, 0, read);
                    System.out.println("客户端消息: " + msg);
                    read = inputStream.read(bytes);
                }
                System.out.println("Closing connection");

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //close connection
                try {
                    if (socket != null) {
                        socket.close();
                    }
//                    if (in != null) {
//                        in.close();
//                    }
//                    if (out != null) {
//                        out.close();
//                    }

                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main (String args[]) {
        Server server = new Server(8007);
    }
}
