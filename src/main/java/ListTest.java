import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author a8079
 * @title: ListTest
 * @projectName nio
 * @description: TODO
 * @date 2020/1/1019:14
 */
public class ListTest {
    public static void main(String[] args) throws IOException {

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("开始测试....");

//        // EasyExcel
        File file = File.createTempFile("test", ".xlsx");
        List<List<String>> headers = new ArrayList<>();
        for (int c = 0; c < 15; c++) {
            List<String> header = new ArrayList<>();
            header.add(c + "_header");
            headers.add(header);
        }
        ExcelWriter excelWriter = EasyExcel.write(file).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("test").head(headers).build();
        for (int r = 0; r < 1048500; r++) {
            List<List<String>> datas = new ArrayList<>();
            List<String> row = new ArrayList<>();
            for (int c = 0; c < 15; c++) {
                row.add(c + "_column");
            }
            datas.add(row);

            excelWriter.write(datas, writeSheet);
        }
        // 一百万 数据  占用内存
        System.out.println("结束测试....当前占用内存：" + Runtime.getRuntime().totalMemory());
        excelWriter.finish();
        System.out.println("结束测试....当前占用内存：" + Runtime.getRuntime().totalMemory());
//
//
////        while(true) {}
//
//
//        // APACHE POI 用户模式
//        XSSFWorkbook wb = new XSSFWorkbook();
//        XSSFSheet sheet = wb.createSheet("test");
//        XSSFRow row = sheet.createRow(0);
//
//        //声明列对象
//        XSSFCell cell = null;
//        //创建标题
//        for(int i=0;i<15;i++){
//            cell = row.createCell(i);
//            cell.setCellValue(i + "_header");
//        }
//        //创建内容
//        for(int i=0;i<1048500;i++){
//            row = sheet.createRow(i + 1);
//            for(int j=0;j<15;j++){
//                //将内容按顺序赋给对应的列对象
//                row.createCell(j).setCellValue(j + "_column");
//            }
//        }
//
//        File poiFile = File.createTempFile("poiFile", ".xls");
//        wb.write(new FileOutputStream(poiFile));
//
//        System.out.println("结束测试....当前占用内存：" + Runtime.getRuntime().totalMemory());
//        wb.close();
//        System.out.println("结束测试....当前占用内存：" + Runtime.getRuntime().totalMemory());
//
//
//        // java.lang.OutOfMemoryError: GC overhead limit exceeded 超过98%的时间用来做GC并且回收了不到2%的堆内存时会抛出此异常。
//        // 超出了GC开销限制。这个是JDK6新添的错误类型。是发生在GC占用大量时间为释放很小空间的时候发生的，是一种保护机制。一般是因为堆太小，导致异常的原因：没有足够的内存。


        // APACHE SAX  事件模型


    }
}
