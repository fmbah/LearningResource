package more.thread.example.java多线程技能;

import java.io.*;

/**
 * @ClassName T36管道间线程通信
 * @Description 一个线程发送数据到输出管道,另一个线程从输入管道中读取数据,通过使用管道,
 *              实现不同线程间的通信,无需借用中间类文件
 *
 *              1 PipedInputStream 和 PipedOutputStream      字节流
 *              2 PipedReader 和 PipedWriter       字符流
 * @Author root
 * @Date 18-11-3 下午3:34
 * @Version 1.0
 **/
public class T36管道间线程通信 {


    /**
     *
     * 功能描述: 展示了字节流,字符流间进行线程间通信
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-3 下午4:11
     */
    public static void main (String args[]) {
        WriteData36 writeData36 = new WriteData36();
        ReadData36 readData36 = new ReadData36();

        PipedInputStream input = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream();

        try {
            out.connect(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ThreadWrite36 threadWrite36 = new ThreadWrite36(writeData36, out);
        threadWrite36.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ThreadRead36 threadRead36 = new ThreadRead36(readData36, input);
        threadRead36.start();


        WriteData36_Writer writeData36_writer = new WriteData36_Writer();
        ReadData36_Reader readData36_reader = new ReadData36_Reader();

        PipedReader pipedReader = new PipedReader();
        PipedWriter pipedWriter = new PipedWriter();

        try {
            pipedWriter.connect(pipedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ThreadWrite36_Writer threadWrite36_writer = new ThreadWrite36_Writer(writeData36_writer, pipedWriter);
        threadWrite36_writer.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ThreadRead36_Reader threadRead36_reader = new ThreadRead36_Reader(readData36_reader, pipedReader);
        threadRead36_reader.start();
    }

}


class WriteData36 {
    public void writeMethod(PipedOutputStream out) {
        try {
            System.out.println("write :");
            for (int i = 0; i < 300; i++) {
                String outData = "" + (i + 1);
                out.write(outData.getBytes());
                System.out.print(outData);
            }
            System.out.println();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class WriteData36_Writer {
    public void writeMethod(PipedWriter writer) {
        try {
            System.out.println("write :");
            for (int i = 0; i < 300; i++) {
                String outData = "" + (i + 1);
                writer.write(outData);
                System.out.print(outData);
            }
            System.out.println();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ReadData36 {
    public void readMethod(PipedInputStream input) {
        try {
            System.out.println("read :");
            byte[] byteArray = new byte[20];
            int readLength = input.read(byteArray);
            while (readLength != -1) {
                String newData = new String(byteArray, 0, readLength);
                System.out.println(newData);
                readLength = input.read(byteArray);
            }
            System.out.println();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ReadData36_Reader {
    public void readMethod(PipedReader reader) {
        try {
            System.out.println("read :");
            char[] byteArray = new char[20];
            int readLength = reader.read(byteArray);
            while (readLength != -1) {
                String newData = new String(byteArray, 0, readLength);
                System.out.println(newData);
                readLength = reader.read(byteArray);
            }
            System.out.println();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ThreadWrite36 extends Thread {
    private WriteData36 writeData36;
    private PipedOutputStream out;

    public ThreadWrite36 (WriteData36 writeData36, PipedOutputStream out) {
        this.out = out;
        this.writeData36 = writeData36;
    }

    @Override
    public void run() {
        super.run();
        writeData36.writeMethod(out);
    }
}

class ThreadWrite36_Writer extends Thread {
    private WriteData36_Writer writeData36;
    private PipedWriter out;

    public ThreadWrite36_Writer (WriteData36_Writer writeData36, PipedWriter out) {
        this.out = out;
        this.writeData36 = writeData36;
    }

    @Override
    public void run() {
        super.run();
        writeData36.writeMethod(out);
    }
}

class ThreadRead36 extends Thread {
    private ReadData36 readData36;
    private PipedInputStream input;

    public ThreadRead36 (ReadData36 readData36, PipedInputStream input) {
        this.input = input;
        this.readData36 = readData36;
    }

    @Override
    public void run() {
        super.run();
        readData36.readMethod(input);
    }
}

class ThreadRead36_Reader extends Thread {
    private ReadData36_Reader readData36;
    private PipedReader input;

    public ThreadRead36_Reader (ReadData36_Reader readData36, PipedReader input) {
        this.input = input;
        this.readData36 = readData36;
    }

    @Override
    public void run() {
        super.run();
        readData36.readMethod(input);
    }
}