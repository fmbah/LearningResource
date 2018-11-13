package java虚拟机;

/**
 * @ClassName JV1
 * @Description
 * @Author root
 * @Date 18-11-10 下午3:27
 * @Version 1.0
 **/
public class JV1 {
    public static void main (String args[]) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String str = new String(i + "");
            double random = Math.random();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("str: [" + str + "], random: [" + random + "] ");
        }
    }
}
