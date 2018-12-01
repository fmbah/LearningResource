package more.thread.test.设计模式.单例模式_Reload0;

/**
 * @ClassName D2单例模式懒汉模式
 * @Description
 * @Author root
 * @Date 18-11-29 下午2:34
 * @Version 1.0
 **/
public class D2单例模式懒汉模式 {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Idler.getInstance());
            }
        };
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(runnable);
        }
        for (int i = 0; i < 100; i++) {
            threads[i].start();
        }
    }
}
class Idler {
    private Idler () {}
    private volatile static Idler idler;
    public static Idler getInstance () {
        if (idler == null) {
            synchronized (Idler.class) {
                if (idler == null) {
                    idler = new Idler();
                }
            }
        }
        return idler;
    }
}
