package more.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientTest {
    public static void main(String[] args) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("localhost", 9999), 500);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String line = bufferedReader.readLine();
            while (!"exit".equals(line)) {
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                printWriter.write(line);
                printWriter.flush();

                System.out.println("111111");

                bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
