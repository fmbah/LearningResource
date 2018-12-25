package more.thread.test.设计模式.单例模式;

import java.io.*;

/**
 * @ClassName D4单例模式序列化反序列化
 * @Description
 *
 *              静态内部类方式的单例模式实现了线程安全问题,但是在序列化的时候,返回的实例还是多例的!
 *
 * @Author root
 * @Date 18-11-10 上午10:15
 * @Version 1.0
 **/
public class D4单例模式序列化反序列化 {

    private static class MyObject4 implements Serializable{
        private static class MyObject4Handler {
            private static MyObject4 myObject4 = new MyObject4();
        }

        private MyObject4 () {}

        public static MyObject4 getInstance () {
            return MyObject4Handler.myObject4;
        }

        /**
         *
         * 功能描述: 保证了在序列化的时候,实例出来同一个对象
         *
         * @param:
         * @return:
         * @auther: Fmbah
         * @date: 18-11-10 上午10:32
         */
        protected Object readResolve () {
            System.out.println("调用了 readResolve 方法!");
            return MyObject4Handler.myObject4;
        }
    }


    public static void main (String args[]) {
        System.out.println(MyObject4.getInstance().hashCode());
        System.out.println(MyObject4.getInstance().hashCode());


        try {
            MyObject4 myObject4 = MyObject4.getInstance();
            FileOutputStream fosRef = new FileOutputStream(new File("myObjectFile.txt"));
            ObjectOutput oosRef = new ObjectOutputStream(fosRef);
            oosRef.writeObject(myObject4);
            oosRef.close();
            fosRef.close();
            System.out.println(myObject4.hashCode());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fisRef = new FileInputStream(new File("myObjectFile.txt"));
            ObjectInputStream iosRef = new ObjectInputStream(fisRef);
            MyObject4 myObject4 = (MyObject4)iosRef.readObject();
            fisRef.close();
            iosRef.close();
            System.out.println(myObject4.hashCode());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
