### JVM概述
1. jdk1.8之前:
    * 线程公有: 堆heap / 方法区Method Area(运行时常量池 Runtime Constant Pool)
    * 线程私有: 虚拟机VM Stack / 本地方法栈Native Method Stack / 程序计数器Program Counter Register
    * 直接内存
2. jdk1.8之后:
    * 线程公有: 堆heap / 元空间Metaspace
    * 线程私有: 虚拟机栈VM Stack / 本地方法栈Native Method Stack / 程序计数器Program Counter Register
    * 直接内存
 
3. 程序计数器: 字节码解释器通过改变程序计数器选取下一条执行的字节码指令,唯一一个不会出现OutOfMemoryError的区域
4. 虚拟机栈: 
    * StackOverFlowError: 栈大小不允许动态扩展, 超出则会栈溢出
    * OutOfMemoryError: java虚拟机栈的内存大小允许动态扩展, 且当线程请求栈时内存用完了, 无法动态扩展,抛出内存溢出
5. 本地方法栈:
    * 虚拟机使用Native方法服务, 同样会抛出栈溢出/内存溢出
6. 堆:
    * 新生代
        * eden(对象默认分配区域)
        * s0 (年龄加1, 达到阀值后晋升为老年代)
        * s1
    * 老年代
        * tentired
7. 对象创建
    * 类加载检查
    * 分配内存(取决于内存是否规整: 标记清除 / 标记压缩)
        * 指针碰撞
        * 空闲列表
        * CAS+ 
        * TLAB
    * 初始化零值
    * 设置对象头
    * 执行init方法
8. 对象的内存分布
    * 对象头
    * 实例数据
    * 对齐填充
9. 对象的访问定位
    * 使用句柄
    * 直接指针
10. String
    * 常量池: "str" "ing", "str" + "ing"
    * 堆: new String("str"), s1 + s2
    * new String("123"), 俩对象: 1常量池(编译器) 2堆(运行期)
11. Integer常量池 / Float, Double无常量池
    
    

    