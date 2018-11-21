package io.netty.example.阻塞IO实例;

import java.io.*;
import java.net.Socket;

/**
 * @ClassName BIOClient0
 * @Description
 * @Author root
 * @Date 18-11-15 下午1:47
 * @Version 1.0
 **/
public class BIOClient0 {

    private static class Client {
        //initialize socket inputStream outputStream
        private Socket socket = null;
        private DataInputStream input = null;
        private InputStream inputStream = null;
        private DataOutputStream out = null;
        private OutputStream outputStream = null;

        public Client (String address, int port) {
            try {
                //establish a connection
                socket = new Socket(address, port);
                System.out.println("connected....");

                // takes input from terminal
//                input = new DataInputStream(System.in);
                inputStream = System.in;

                // sends output to the socket
//                out = new DataOutputStream(socket.getOutputStream());
                outputStream = socket.getOutputStream();

                String line = "";
//                DataInputStream serverIn = new DataInputStream(socket.getInputStream());
                //keep reading until "Over" is input
//                while (!line.equals("Over")) {
//                    line = input.readLine();
//                    System.out.println("客户端输入的消息: " + line);
//                    out.writeUTF(line);
//
//                    if (serverIn != null) {
//                        String inStr = serverIn.readUTF();
//                        System.out.println(inStr);
//                    }
//                }

                byte[] bytes = new byte[10];
                int read = inputStream.read(bytes);
                while (read != -1) {
                    line = new String(bytes, 0, read);
                    outputStream.write(line.getBytes());
                    outputStream.flush();
                    read = inputStream.read(bytes);
                }

                //close connection
                System.out.println("开始销毁流对象");
                if (socket != null) {
                    socket.close();
                }
//                    if (input != null) {
//                        input.close();
//                    }
//                    if (out != null) {
//                        out.close();
//                    }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main (String args[]) {
        Client client = new Client("127.0.0.1", 8007);
    }
}
