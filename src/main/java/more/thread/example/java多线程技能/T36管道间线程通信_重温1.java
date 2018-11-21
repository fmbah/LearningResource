package more.thread.example.java多线程技能;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @ClassName T36管道间线程通信_重温1
 * @Description
 *
 *              管道间通信,
 *              PipedOutputStream
 *                  out.write();out.close()
 *              PipedInputStream
 *                  byte[] bytes = new byte[10]
 *                  int length = in.read(bytes);
 *                  while (length != -1) {
 *                      String readStr = new String(bytes, 0, length);
 *                      length = in.read(bytes)
 *                  }
 *                  in.close();
 *              out.connect(in)
 *              启动写线程,向相应管道内写入数据,
 *              启动读线程,从相应管道内读取数据
 * @Author root
 * @Date 18-11-15 上午10:00
 * @Version 1.0
 **/
public class T36管道间线程通信_重温1 {

    private static class MyDataProcess {
        private PipedOutputStream out;
        private PipedInputStream in;
        public MyDataProcess (PipedOutputStream out, PipedInputStream in) {
            this.out = out;
            this.in = in;
        }

        public void writeData () {
            try {
                System.out.println("write start!");
                for (int i = 0; i < 100; i++) {
                    out.write((i + "").getBytes());
                }
                System.out.println("write end!");
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void readData () {
            try {
                System.out.println("read start!");
                byte[] bytes = new byte[10];
                int read = in.read(bytes);
                while (read != -1) {
                    System.out.println(new String(bytes, 0, read) + "\t");
                    read = in.read(bytes);
                }
                System.out.println("read end!");
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static class MyDataWriteThread36 extends Thread{
        private MyDataProcess myDataProcess;
        public MyDataWriteThread36 (MyDataProcess myDataProcess) {
            this.myDataProcess = myDataProcess;
        }

        @Override
        public void run() {
            super.run();
            myDataProcess.writeData();
        }
    }

    private static class MyDataReadThread36 extends Thread {
        private MyDataProcess myDataProcess;
        public MyDataReadThread36 (MyDataProcess myDataProcess) {
            this.myDataProcess = myDataProcess;
        }

        @Override
        public void run() {
            super.run();
            myDataProcess.readData();
        }
    }

    public static void main (String args[]) {
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        PipedInputStream pipedInputStream = new PipedInputStream();
        try {
            pipedOutputStream.connect(pipedInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MyDataProcess myDataProcess = new MyDataProcess(pipedOutputStream, pipedInputStream);

        MyDataWriteThread36 myDataWriteThread36 = new MyDataWriteThread36(myDataProcess);
        myDataWriteThread36.start();

        try {
            myDataWriteThread36.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MyDataReadThread36 myDataReadThread36 = new MyDataReadThread36(myDataProcess);
        myDataReadThread36.start();
    }

}
