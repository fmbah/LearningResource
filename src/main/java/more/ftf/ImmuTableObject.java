package more.ftf;

import java.util.HashMap;

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
    }
}
