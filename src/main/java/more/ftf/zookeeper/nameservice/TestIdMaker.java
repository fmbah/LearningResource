package more.ftf.zookeeper.nameservice;

import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author a8079
 * @title: TestIdMaker
 * @projectName nio
 * @description: TODO
 * @date 2020/1/220:12
 */
public class TestIdMaker{

    public static void main(String[] args) throws Exception {


        System.out.println(new SecureRandom().nextInt(2000));
        IdMaker idMaker = new IdMaker("192.168.56.105:2181",
                "/NameService/IdGen", "ID");
        idMaker.start();

        try {
            for (int i = 0; i < 1000; i++) {
                String id = idMaker.generateId(IdMaker.RemoveMethod.DELAY);
                System.out.println(id);
            }
        } finally {
            idMaker.stop();

        }

//        Runtime.getRuntime().addShutdownHook(new Thread(()->{
//            System.out.println("jvm 关闭.....");
//        }));

        // 自定义jvm退出回调钩子
        Signal sig = new Signal(getOSSignalType());
        Signal.handle(sig, new ShutdownHandler());
    }

    private static String getOSSignalType() {
        return System.getProperties().getProperty("os.name").
                toLowerCase().startsWith("win") ? "INT" : "USR2";
    }

    public static class ShutdownHandler implements SignalHandler {
        /**
         * 处理信号
         *
         * @param signal 信号
         */
        public void handle(Signal signal) {

            System.out.println("handle.....");
            registerShutdownHook();

            Runtime.getRuntime().exit(0);
        }

        private void registerShutdownHook() {
            System.out.println("registerShutdownHook.....");
            Thread t = new Thread(new ShutdownHook(), "ShutdownHook-Thread");
            Runtime.getRuntime().addShutdownHook(t);
        }
    }

    static class ShutdownHook implements Runnable {
        @Override
        public void run() {
            System.out.println("ShutdownHook execute start...");
            try {
                TimeUnit.SECONDS.sleep(10);//模拟应用进程退出前的处理操作
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ShutdownHook execute end...");
        }
    }

}
