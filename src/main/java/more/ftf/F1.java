package more.ftf;

/**
 * @ClassName F1
 * @Description 来源占小狼订阅号
 * @Author root
 * @Date 18-10-31 上午9:31
 * @Version 1.0
 **/
public class F1 {

    public static void main(String args[]) {
        //自我介绍
        //项目介绍
        //8种基本数据类型 byte char boolean double float int long string
        //Integer的缓冲数据范围-128~127
        //Object类的方法 toString equals hasCode wait notify notifyAll
        //String str = "123", String newStr = new String("123")生成几个对象
        //wait和sleep区别 wait 自动释放锁 sleep不释放锁
        //hashcode用在哪里 map concurrentmap
        //hashmap
        //JVM
        //内存溢出内存泄漏
        //数据库4大特性:原子性 一致性  隔离性  持久性
        //数据库隔离级别
        //计算机网络7层,物理层,数据链路层,网络层,传输层,会话层,表示层,应用层
        //TCP/UDP/HTTP

        //ArrayList和Linkedlist
        //linkedlist可以用for循环吗
        //Concurrenthashmap
        //


        int a = 10 >> 1;
        int b = a++;
        int c = ++a;
        int d = b * a++;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);

        //单例模式
        //虚拟机,虚拟机内存模型
        //垃圾回收算法
        //ServivorRatio=8:1:1
        //二叉树
        //二叉树的深度搜索遍历
//        void DFS(TreeNode root) {
//            if (root == null) {
//                return;
//            }
//            if (root.left != null) {
//                DFS(root.left);
//            }
//            if (root.right != null) {
//                DFS(root.right)
//            }
//            System.out.println(root.val);
//        }

        //单例模式详解
        //垃圾回收器中,标记回收多次后,可能会出现什么问题? 内存碎片,由于内存不连续,会发生fullGC
        //fullGc/MINORGC
        //项目中的内存溢出,内存泄漏例子,ThreadLocal的键是弱引用,内存回收可能会将其清除,没有了键,就无法找到value了,造成了内存泄漏
        //动态规划题目(大体是N*M的矩阵方格中,找一个最大的正方形是几乘几的)


        try {
            "".wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
