package java虚拟机.url;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author a8079
 * @title: UrlTest
 * @projectName LearningResource
 * @description: TODO
 * @date 2019/11/911:14
 */
public class UrlTest {
    public static void main(String[] args) {
        String url = "https%253A%252F%252Fwww.kwaishop.com%252Fmerchant%252Fshop%252Flist%253Fid%253D1442088036%2526webviewClose%253Dfalse%2526biz%253Dmerchant%2526carrierType%253D3%2526from%253Dprofile%2526hyId%253Dkwaishop%23_nt%3D1573268145144";

        try {
            System.out.println(URLDecoder.decode(url, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
