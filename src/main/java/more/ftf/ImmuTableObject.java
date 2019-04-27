package more.ftf;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @ClassName ImmuTableObject
 * @Description 不可变对象
 * @Author root
 * @Date 19-2-13 上午10:07
 * @Version 1.0
 **/
public final class ImmuTableObject {



    private final String text;
    private final HashMap<String, String> mp;

    public String getText() {
        return text;
    }

    public HashMap<String, String> getMp() {
        return (HashMap<String, String>)mp.clone();
    }

    public ImmuTableObject(String text, HashMap<String, String> mp) {
        this.text = text;
        this.mp = mp;
    }

    static class MyClass {
        public final double i = Math.random();
        public static double j = Math.random();
    }

    public static void main(String[] args) {
        String text = "123";
        HashMap<String, String> mp = new HashMap<>();
        mp.put("123", "123");
        ImmuTableObject immuTableObject = new ImmuTableObject(text, mp);
        System.out.println(immuTableObject.getText() + "=" + immuTableObject.getMp()+ "=>" + immuTableObject);
        text = "234";
        mp.put("444","444");
        System.out.println(immuTableObject.getText() + "=" + immuTableObject.getMp()+ "=>" + immuTableObject);

        MyClass myClass1 = new MyClass();
        MyClass myClass2 = new MyClass();
        System.out.println(myClass1.i);
        System.out.println(myClass1.j);
        System.out.println(myClass2.i);
        System.out.println(myClass2.j);

        System.out.println(Math.round(-1.5));
        System.out.println(Math.round(1.5));


        List<String> strings = Arrays.asList("lombok.patcher.Symbols", "aaaa");
        strings.forEach(s -> {
            System.out.println(s);
        });

        Queue<Integer> queue = new ArrayBlockingQueue<>(10);

        System.out.println(System.currentTimeMillis());


        mp.get("");


        DecimalFormat df = new DecimalFormat("0.00%");

        System.out.println(new BigDecimal("0.00").multiply(new BigDecimal(100)));


        BigDecimal skuProfit = new BigDecimal("120");

        BigDecimal part0 = skuProfit.multiply(new BigDecimal("0.04"));
        BigDecimal divide = new BigDecimal("15").divide(new BigDecimal("100"));
        BigDecimal part1 = skuProfit.multiply(divide);
        System.out.println(part0.add(part1));
        skuProfit = part0.add(part1);
        System.out.println(skuProfit);
        System.out.println(new BigDecimal("100000000.00100").stripTrailingZeros());

        System.out.println(LocalDateTime.ofInstant(new Date(System.currentTimeMillis()).toInstant(), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.CHINESE)));


        LocalDateTime of = LocalDateTime.of(2019, Month.APRIL, 28, 11, 0, 0);
        System.out.println(of);


    }
}
