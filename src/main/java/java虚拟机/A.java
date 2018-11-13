public class A {
  public static void main (String args[]) {
      String strOld = "";
    for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String str = new String("数字是多少, i = " + i);
            double random = Math.random();
        strOld += ((int)(random));
		try {
		        Thread.sleep(100);
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
            System.out.println("str: [" + str + "], random: [" + random + "] ");
        }
  }
}
