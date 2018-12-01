````
-Xms256M 
-Xmx512M
-Xmn128M(等同于-XX:NewSize=128M -XX:MaxNewSize=128M, 或者通过-XX:NewRatio=3新生代和老年代比例设置新生代大小(新生代:老年代=1:3,则新生代占整个堆的四分之一))
-XX:SurvivorRatio=8(代表新生代(两个Survivor:Eden=2:8), 则一个survivor占新生代的十分之一)
老年代内存(初始化大小:-Xms减-Xmn；最大大小:-Xmx减-Xmn)
-Xss1M(栈大小,与线程数量反比,与方法调用深度成正比)
-XX:PermSize=128M
-XX:MaxPermSize=256M
-XX:MetaspaceSize=128M
-XX:MaxMetaspaceSize=256M(MetspaceSize上限大小与JVM位数有关,64位可用完所有机器内存, MaxMetspaceSize会动态的调整大小)
-XX:MaxDirectMemorySize(直接内存,使用NIOapi可使用直接内存,默认大小64M,超过此值会出现FullGc现象)
-XX:MaxTenuringThreshold=15(设置新生代进入老年代的年龄,设置为0,直接进入老年代,对于老年代比较多的应用,可提高效率,设置较大,增加了回收的几率)



不常用参数:
-XX:MaxHeapFreeRatio=70(GC后java堆中空闲量占的最大比例,大于该值,则堆内存会减小)
-XX:MinHeapFreeRatio=50(GC后java堆中空闲量占的最小比例,小于该值,则堆内存会增加)
-XX:PretenureSizeThreshold=1024(大于该值,直接分配到老年代)
-XX:TLABWasteTargetPercent=1(TLAB占Eden区的百分比)

````

1. 如何判断对象是否死亡（两种方法）。引用计数法/可达性分析算法(虚拟机栈中引用的对象/本地方法栈中引用的对象/堆中类静态属性引用的对象)(后者很好的解决了两个无用对象互相引用导致无法GC的问题)
2. 简单的介绍一下强引用、软引用、弱引用、虚引用（虚引用与软引用和弱引用的区别、使用软引用能带来的好处）。
    (强引用跟随着对象的生命周期(Object obj = new Object())；软引用:可用非必须,在发生内存异常之前,再次进行回收软引用；
     弱引用发生gc都会回收与之关联的对象；虚引用:唯一作用在GC的时候收到系统通知)
3. 如何判断一个常量是废弃常量(这部分我们会比较容易理解，就举一个简单的例子，String str= "abc";这时候abc就已经进入了常量池中，当str改变类值，那么久没有对象引用"abc"这个常量，这时候，"abc"就会被系统清理出常量池。常量池中的其他类、方法、字段的符号引用也是如此)
4. 如何判断一个类是无用的类
    1. 该类所有的实例已经被回收, 即java堆中已经不存在该类实例
    2. 加载该类的ClassLoader已经被回收
    3. 该类对应的java.lang.Class对象没有在任何地方被引用,即无法在任何地方通过反射访问该类的方法
5. 垃圾收集有哪些算法，各自的特点？
6. HotSpot为什么要分为新生代和老年代？
7. 常见的垃圾回收器有那些？
8. 介绍一下CMS,G1收集器。
9. Minor Gc和Full GC 有什么不同呢？


[jvm param参考: https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/gc01/index.html]
[jconsole远程链接jvm参考: https://docs.oracle.com/javase/tutorial/jmx/remote/jconsole.html]
1. 远程链接报错时, -Dcom.sun.management.jmxremote.port=9595′,
            -Dcom.sun.management.jmxremote.ssl=false’,
            -Dcom.sun.management.jmxremote.authenticate=false
            -Djava.rmi.server.hostname=72.14.204.147 #ip of the remote machine, yes the ip, not the name
            

````
-XX:+UseSerialGC(启用串行收集器)(java -Xmx12m -Xms3m -Xmn1m -XX:PermSize=20m -XX:MaxPermSize=20m -XX:+UseSerialGC -jar c:\javademos\demo\jfc\Java2D\Java2demo.jar)
-XX:+UseParallelGC(启用并行收集器/吞吐量收集器)(java -Xmx12m -Xms3m -Xmn1m -XX:PermSize=20m -XX:MaxPermSize=20m -XX:+UseParallelGC -jar c:\javademos\demo\jfc\Java2D\Java2demo.jar)
-XX:ParallelGCThreads=<desired number>(控制并行收集器(吞吐量收集器)的线程数量)
-XX:+UseParallelOldGC(年轻代老年代都使用多线程进行处理,每次GC后会压缩空间,使内存连续(java -Xmx12m -Xms3m -Xmn1m -XX:PermSize=20m -XX:MaxPermSize=20m -XX:+UseParallelOldGC -jar c:\javademos\demo\jfc\Java2D\Java2demo.jar))
-XX:+UseConcMarkSweepGC(启用CMS收集器/低暂停收集器(对于年轻代收集算法与并行收集器相同)(java -Xmx12m -Xms3m -Xmn1m -XX:PermSize=20m -XX:MaxPermSize=20m -XX:+UseConcMarkSweepGC -XX:ParallelCMSThreads=2 -jar c:\javademos\demo\jfc\Java2D\Java2demo.jar))
-XX:ParallelCMSThreads=<n>(设置CMS收集器的线程数量)
-XX:+UseG1GC((G1收集器:并行,并发,可压缩的低暂停)(被设计为可替换CMS收集器)java -Xmx12m -Xms3m -XX:+UseG1GC -jar c:\javademos\demo\jfc\Java2D\Java2demo.jar)


垃圾回收器的选择
串行收集器: 灭绝了,不能利用多cpu条件,单CPU时可使用
并行收集器/吞吐量收集器: 吞吐量优先,垃圾回收时间小于1s,多cpu可使用
G1收集器: 不能容忍长时间垃圾回收暂停时间,
````
[垃圾回收器的选择参考: http://karunsubramanian.com/websphere/how-to-choose-the-correct-garbage-collector-java-generational-heap-and-garbage-collection-explained/]