package more.http;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class HttpTest {

    private static final String GET_POST_URL = "http://localhost:8080/zb_ywwl_trial_oper_web_war_exploded/";

    public static void main(String[] args) throws Exception{
        // 方案://用户名:密码@主机名:端口/服务器上资源的路径;参数?查询#片段
        URL url = new URL("http://feibao:123456@localhost:8080/zb_ywwl_trial_oper_web_war_exploded?param0=value0&param1=value1#maodian");
        // getPort: url冒号跟的端口号;  getDefaultPort 根据协议获取默认端口号 http 80 ;https 443; ftp:21
        System.out.println(url.getPort() + " ===== " + url.getDefaultPort());
        System.out.println(url.getHost() + " ===== " + url.getPath());
        System.out.println(url.getAuthority() + " ===== " + url.getProtocol());
        // 获取url的用户信息    url的锚点
        System.out.println(url.getUserInfo() + " ===== " + url.getRef());
        System.out.println(url.getQuery());

        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        urlConnection.setRequestMethod("GET");
        // post 设置 true; get 设置 false
        urlConnection.setDoOutput(true);

        // 建立链接   三次握手完成
        urlConnection.connect();

        StringBuilder builder = new StringBuilder();
        // 数据交换完成
//        InputStream inputStream = urlConnection.getInputStream();
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//        String line = null;
//        while((line = bufferedReader.readLine()) != null) {
//            builder.append(line);
//        }
//        bufferedReader.close();

//        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
//        byte[] readBytes = new byte[1024];
////        int available = bufferedInputStream.available();
//        int i;
//        while ((i = bufferedInputStream.read(readBytes)) != -1) {
//            String readStr = new String(readBytes, "UTF-8");
//            builder.append(readStr);
//        }

        TimeUnit.SECONDS.sleep(2L);

        urlConnection.disconnect();

        System.out.println("结果： " + builder.toString());
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("", 80));
//        URLConnection urlProxyConnection = url.openConnection(proxy);
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost.getCanonicalHostName() + "><" + localHost.getHostAddress() + "><" + localHost.getHostName());


    }
}
