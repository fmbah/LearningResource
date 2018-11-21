package more.ftf;

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName F2
 * @Description
 * @Author root
 * @Date 18-11-19 下午5:13
 * @Version 1.0
 **/
public class F2 {
    /**
     *
     * 功能描述:
     *
     * 阿里巴巴:
     *  1 开发中用的较多的数据结构有哪些
     *  2 谈谈你的hashmap的理解,基层的基本实现,hashmap怎么解决碰撞问题,这些数据结构中是线程安全的吗?假如你回答不是线程安全的,hashtab是线程安全的,接着问你有没有线程安全的map,接下来问了current包
     *  3 对jvm熟悉不,简单说说类加载过程,里面执行了那些操作,问了GC和内存管理,平时有没有在tomcat中进行相应配置
     *  4 问了http协议,get和post的基本区别,接着tcp/ip协议,三次握手,窗口滑动机制
     *  5 开发用那些数据库,mysql? 存储引擎有那些,悲观锁和乐观锁的使用场景,分布式集群实现的原理
     *  6 springmvc 和 mybatis工作原理,有没有看过底层源码
     *  7 redis中的基本存储类型,事物,使用场景
     *
     * 京东金融:
     *  1 dubbo超时重试,超时时间设置
     *  2 如何保证请求执行顺序
     *  3 分布式事物以及分布式锁(扣款不要出现负数)
     *  4 分布式session设置
     *  5 执行某操作,前50次成功,第51次失败,a全部回滚/b前五十次提交,第51次异常,ab场景如何设置spring事物的传播特性
     *  6 Zookeeper有哪些作用
     *  7 JVM内存模型
     *  8 数据库垂直和水平拆分
     *  9 Mybatis如何分页,如何设置缓存,mysql分页
     *  10 熟悉io吗,与NIO的区别,阻塞与非阻塞的区别
     *  11 分布式session一致性
     *  12 分布式接口幂等性设计(不能重复扣款)
     *
     * 美团一面经验:
     *  1 画目前项目架构图
     *  2 JVM老年代新生代比例
     *  3 YGC和FGC的发生场景
     *  4 jstack jmap jutil 分别意义,如何线上排查JVM相关问题
     *  5 线程池的构造类的方法的5个参数的具体意义
     *  6 单机上一个线程池正在处理服务如果忽然断电了怎么办,正在处理和阻塞队列里的请求怎么处理
     *  7 使用无界阻塞队列会有什么问题
     *  8 接口如何处理重复请求
     *  9 具体的处理方案是什么
     *  10 如何保证共享变量的原子性
     *  11 设计一个对外服务的接口实现类,在1,2,3这三个主机(不同ip)上实现负载均衡和顺序轮寻机制(考虑并发)
     *
     * 滴滴一面:
     *  1 自我介绍
     *  2 技术特点
     *  3 兴趣是什么 优势是什么
     *  4 Dubbo底层原理 Zookeeper是什么
     *  5 concureentMap机制 TreeMap
     *  6 volatile关键字
     *  7 快速排序,广度深度搜索(队列实现)
     *
     * @param:
     * @return:
     * @auther: Fmbah
     * @date: 18-11-19 下午5:16
     */
    public static void main(String args[]) {


        //arraylist linkedlist hashmap
        //链表数组,链表对象中存放前后元素的指针和当前的键值,当多个元素的hashcode值相同,也就会出现元素存放在一个bucket上,那么会先便成链表进行值的存放,如果超过约定上限(目前是8个),会转化为treemap红黑树进行存放,如果key实现了comparable方法的话,可以按照key的大小进行存放,如果未实现,那么性能上也不会有很大的提升...
        HashMap hashMap = new HashMap();
        Map<Object, Object> objectObjectMap = Collections.synchronizedMap(new HashMap<>());
        //volatile 指令重排, 保证可见性,不保证原子性(i++)
        //jvm 堆(年轻代(eden区:from survivor0:To survivor1区, 默认比: 8:1:1)/老年代) 栈(本地方法栈 虚拟机栈) 方法区(永久区/元数据区)
        //-Xss 栈 ； -XX:NewSize Eden from survivor0 to survivor1； -XX:MaxNewSize: Eden from survivor0 to survivor1 virtual；
        //-Xms -Xss + 堆区 ； -Xmx -Xms + virtual * 2
        //-XX: PermSize 1.8失效了... -XX: MaxMe....
        //jvm 类加载参考  http://www.importnew.com/23742.html
        //jvm 类加载是将.class文件中的二进制数据流读入内存中,将其存放在运行时数据区的方法区内,并在堆内创建一个java.lang.Class对象,用来封装类在方法区的数据结构,
        //类的加载最终产品是位于堆内的Class对象,Class对象封装了类在方法区的数据结构,并且向java程序员提供了访问方法区内的数据结构的接口.
        //加载类的方式(本地加载,网络加载,归档文件中提取后加载,动态编译后加载,从专有数据库中加载(???))
        //类加载的生命周期(加载,验证(连接),准备,解析,初始化)
        //加载 1 通过类的全限定名获取其定义的二进制字节流
        //    2 将这个二进制流锁代表的静态存储结构转化为方法区的运行时数据结构
        //    3 在java堆中生成一个代表这个类的Class对象,作为对方法区这些数据的访问入口
        //验证(连接) 1 文件格式验证,
        //          2 元数据验证
        //          3 字节码验证
        //          4 符号验证
        //准备        1 为类的静态变量分配内存,并将其初始化为默认值
        //解析        1 将类的符号引用转化为直接引用
        //初始化       1 由虚拟机执行初始化操作
        //类初始化的时机: new 反射 子类 操作静态变量

        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);
        ClassLoader contextClassLoaderParent = contextClassLoader.getParent();
        System.out.println(contextClassLoaderParent);
        ClassLoader contextClassLoaderParentParent = contextClassLoaderParent.getParent();
        System.out.println(contextClassLoaderParentParent);//此处为null,是由于bootclassloader是由c语言编写,找不到一个确定的返回父类的方式,于是就返回null

        //双亲委派模型(当收到一个加载class类的请求的时候,首先不会主动自己去加载,而是委托给父类去加载,只有当父类无法加载此类的时候,才会由子类加载)
        //比如AppClassLoader收到加载a类的请求,不会自己主动去加载,
        // 而是找到父类ExtClassLoader去加载,ExtClassLoader也不会主动加载,
        // 而是交由BootstrapClassLoader去加载,只有当最顶层父类无法加载的时候,
        // 才会轮到子类加载器去加载,最后都加载不了,就会提示ClassNotFoundException
        //保证了内存中不会出现多份同样的字节码文件

        //自定义类加载器(如网络传输了加密的字节码)


    }

    private static class MyClassLoader extends ClassLoader {

        private String root;
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            return super.findClass(name);
        }

        private byte[] loadClassData(String className) {
            String fileName = root + File.separator+ className.replace('.', File.separatorChar) + ".class";
            try {
                InputStream ins = new FileInputStream(fileName);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int bufferSize = 1024;
                byte[] buffer = new byte[bufferSize];
                int length = 0;
            while ((length = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
                return baos.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        public String getRoot() {
            return root;
        }

        public void setRoot(String root) {
            this.root = root;
        }
    }
}
