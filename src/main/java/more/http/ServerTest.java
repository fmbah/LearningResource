package more.http;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9999);
            while (true) {


                Socket accept = serverSocket.accept();
                System.out.println("连接成功");
                InputStream inputStream = accept.getInputStream();
                System.out.println("获取数据流");
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                byte[] bytes = new byte[1024];
                int i;
                int available = bufferedInputStream.available();
            StringBuilder sb = new StringBuilder();
                    i = bufferedInputStream.read(bytes);
                    System.out.println(i);

                    String tmpStr = new String(bytes);
                    sb.append(tmpStr);

                System.out.println("client: " + sb.toString());

                PrintWriter printWriter = new PrintWriter(accept.getOutputStream());
                printWriter.write("i know.....");
                printWriter.flush();

                printWriter.close();
                bufferedInputStream.close();
                accept.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null != serverSocket) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
