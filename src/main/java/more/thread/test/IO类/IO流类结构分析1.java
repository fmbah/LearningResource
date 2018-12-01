package more.thread.test.IO类;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @ClassName IO流类结构分析1
 * @Description
 *      字节流 FileInputStream
 *            FIleOutputStream
 * io流
 *      字符流 FileReader
 *            FileWriter
 *
 *
 * @Author root
 * @Date 18-11-29 下午3:15
 * @Version 1.0
 **/
public class IO流类结构分析1 {

    /**
     *
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-29 下午3:15
     */
    public static void main(String[] args) throws Exception{
        File iofile = File.createTempFile("iofile", ".text");
        FileOutputStream fileOutputStream = new FileOutputStream(iofile);
        fileOutputStream.write(1111);
        fileOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream(iofile);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fileInputStream.read(bytes)) != -1) {
            System.out.println(new String(bytes, 0, length));
        }
        fileInputStream.close();
        if (iofile.exists()) {
            iofile.delete();
        }
    }
}
