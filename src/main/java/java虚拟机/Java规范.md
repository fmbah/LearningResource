### Java规范(参照:阿里巴巴Java开发手册, https://files-cdn.cnblogs.com/files/han-1034683568/%E9%98%BF%E9%87%8C%E5%B7%B4%E5%B7%B4Java%E5%BC%80%E5%8F%91%E6%89%8B%E5%86%8C%E7%BB%88%E6%9E%81%E7%89%88v1.3.0.pdf)
#####1. 命名规则
    * 禁止: _ $ 符号开头, _ $ 符号结尾
    * 类名驼峰形式, 除DO/BO/VO/AO/DTO, UpperCamelCase风格 
    * 方法名/参数名/成员变量, lowerCamelCase风格
    * 常量命名, 全部大写，单词间用下划线隔开，力求语义表达完整清楚，不要嫌名字长
    * 抽象类使用Abstract或Base开头
    * 异常类使用Exception结尾
    * 测试类使用Test结尾
    * 包名小写,单数形式
    * 尽量完整单词描述类含义
    * 类使用了设计模式,使用设计模式名称结尾
    * 接口和实现类, Service/Dao, ServiceImpl/DaoImpl
        * 如果是形容能力的接口名称，取对应的形容词做接口名 + able, AbstractTranslator implements Translatatable 
    * 枚举类使用Enum结尾, 成员变量其实就是常量,大写
    * 各层命名规定
        * Service/Dao方法层命名
            * 获取单个对象方法使用get前缀
            * 获取多个对象方法使用list前缀
            * 获取统计值方法使用count前缀
            * 插入的方法使用save/insert前缀
            * 删除的方法使用remove/delete前缀
            * 修改的方法使用update前缀
        * 领域模型命名
            * 数据对象, xxxDO, xxx为数据表名
            * 数据传输对象, xxxDTO, xxx为业务领域相关的名称
            * 展示对象, xxxVO, xxx为网页名称
            
#####2. 常量定义
    * Long/long 定义使用大写L
    * 缓存常量类: CacheConsts
    * 系统配置相关常量类: ConfigConsts
    * 常量复用: 类常量, 包常量(constant目录下),子工程常量(子工程的constant目录下),应用内共享常量(第一方库),跨应用内共享常量变量(二方库)
        *   一方库：本工程中的各模块的相互依赖
        *   二方库：公司内部的依赖库，一般指公司内部的其他项目发布的jar包
        *   三方库：公司之外的开源库， 比如apache、ibm、google等发布的依赖
    * 变量在范围内,且有特殊意义；public Enum { MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6),SUNDAY(7);}
    
#####3. 代码格式
    * 大括号
    * 小括号
    * if/for/while/switch/do等保留字与括号之间都必须加空格
    * 二目运算符或三目运算符左右空格
        * ++, -- 一目
        * +, -, * 二目
        * ?: 三目
    * Tab缩进四个空格
        * 首行缩进4个空格
        * int flag = 0;
        * if (flag == 0) {
        * 
        * }
    * // 注释符号与内容有且仅有一个空格
    * 逗号后空格
    * IDE text file encoding utf-8,  换行符使用unix格式
    * 方法体内定义, 执行, 不同的业务逻辑/不同的语义需要换行, 相同业务逻辑/语义不需要换行
    
#####4. OOP规约
    * 静态方法, 类名.静态方法
    * Override注解
    * public String getUsers(String type, Integer ...ids){...}, 尽量使用不可变参数
    * 过时方法禁用
    * 常量.equals(变量), 防止空指针异常
    * 包装类比较 equals, -128~127
    * POJO类 使用 包装类型
    * RPC方法 返回值和参数必须使用 包装类型 (如果是基本类型, RPC接口调用失败, 返回默认值, 0 和 null的意义不同)
    * 所有局部变量使用基本数据类型
    * POJO不允许设置默认值
    * serialVersionUID序列化和反序列化
    * 构造方法不允许业务逻辑, 可放在init中
    * POJO类必须重写toString, super.toString()
    * split需要做内容检查,避免出现空内容的情况
    * 同名方法按顺序放在一起
    * setter/getter不要增加业务逻辑
    * StringBuilder().append()方法每次都会创建一个StringBuilder()对象,最后toString返回String对象,避免内存浪费
    * final
        * 不允许继承的类, String
        * 不允许修改引用的域对象, 如: POJO类的域变量
        * 不允许重写方法, setter
        * 不允许运行过程中重新赋值的变量
        * 避免上下文重复使用一个变量，使用final描述可以强制重新定义一个变量，方便更好地进行重构。
    * Object clone浅拷贝, 需要重写进行深度拷贝
    * 不允许new, 构造方法private
    * 工具类不允许public或default构造方法
    * 类非static成员变量与子类共享, 必须是protected
    * 类非static成员变量尽在本类使用, 必须是private
    * 类static成员变量仅在本类使用,必须是private, 考虑final
    * 类成员变量仅在本类使用, private
    * 类成员变量对继承类公开, protected
    
#####5. 集合处理
    * equals/hashCode, String, Set, Map
    * ArrayList.subList不可转换成ArrayList, SubList是ArrayList的内部类, 对原集合的修改均会ConcurrentModificationException异常
    * toArray(T[] a), 不会自动转型,并且必须传入数量一致参数一致的数组,否则多的位置会null,少的位置会重新调整大小
    * Arrays.asList(), 不可增删,会报UnsupportedOperationException异常, 返回的是内部类,未实现原集合的修改方法, 适配器模式, 只是转换接口, 后台仍是数组
        * String[] strs = {"1", "2", "3"}; strs[0] = "lalal" ==> list也会变化
        * List list = Arrays.asList(strs); list.add("123") ==> 异常
    * PECS(Producer Extends Customer Super)频繁读取<? extends T>, 经常插入<? super T>
    * foreach不要remove元素[ConcurrentModificationException]； iterator可循环时remove元素,并发下加锁
    * Comparator满足三个条件, 否则Arrays.sort() Collection.sort()  IllegalArgumentException异常
        * a > b 则 b < a
        * a > b b > c 则 a > c
        * a = b 则 a/b 与 c 的比较结果相同
    * 集合初始化, 指定集合初始化大小
        * new HashMap(16) --> 未指定大小的话，inintialCapacity = (需要存储的数量 / 负载因子(0.75)) + 1
            * 如果存储1024个元素，未指定大小， 则扩容七次后， resize需要重新建立hash表， 严重影响性能
        * entrySet遍历Map类集合KV， values()是value集合， keySet()是key集合
        * 集合类   Key     Value       Super       说明
        * HashTable 非null  非null    Dictionary      线程安全
        * ConcurrentHashMap 非null 非null AbstractMap 锁分段技术 Cas
        * TreeMap 非null    允许null   AbstractMap     线程不安全
        * HashMap 允许null  允许null    AbstractMap   线程不安全
    * 有序性（sort）:按照一定规则进行排序的
    * 稳定性（order）  每次遍历次序是一致的
        * ArrayList 是 order/unsort
        * HashMap 是 unsort/order
        * TreeSet 是 sort/order
    * 避免使用List.contains
    
#####6. 并发处理
    * 获取单例对象保证线程安全， 其中的方法也要保证线程安全
        * 资源驱动类/工具类/单例工厂类等
    * 创建线程或线程池请标明有意义的线程名称
    * 线程资源必须通过线程池提供，避免产生大量同类线程或过度切换问题
    * 不允许Executors创建线程池
        * FixedThreadPool/SingleThreadPool/CachedThreadPool/ScheduledThreadPool的允许创建线程数量为Integer.MAX_VALUE, 可能积累大量的线程，导致OOM
    * SimpleDateFormt 线程不安全， 一般不要定义为static常量， 如果使用static 则必须加锁
        > private static final ThreadLocal<DateFormat> date = new ThreadLocal<DateFormat>(){
        >   @Override
        >   protected DateFormat initialValue() {
        >       return new SimpleDateFormat("yyyy-MM-dd");
        >       }
        >   };
        * JDK8, Instant代替Date, LocalDateTime代替Calendar, DateTimeFormatter代替SimpleDateFormat
    * 锁的范围，锁的顺序
        * 能不用锁，就不用锁； 能锁区块， 就锁区块； 能锁对象， 就不要锁类
        * 线程一对a,b,c依次加锁后才可以进行更新操作， 则线程二的加锁顺序也必须是 a b c， 否则会出现死锁情况
        * 并发修改同一记录，未避免更新丢失， 要么在应用层加锁， 要么在缓存层加锁， 要么在数据库层使用乐观锁，使用version作为更新依据
            * 如果每次访问冲突概率小于20%, 推荐使用乐观锁，否则使用悲观锁， 乐观锁的重试次数不得小于3次
    * 定时任务， Timer运行多个TimeTask时，只要其中之一没有捕获抛出的异常，其它任务便会终止任务运行， 使用ScheduledExecutorService则没有这个问题
    * 使用CountDownLatch进行异步转同步操作，每个线程退出前必须调用countDown方法，注意捕捉异常（子线程抛出异常，主线程不会捕捉到）， 确保countDown方法被执行到，避免主线程无法执行至await方法，直到超时才返回结果
    * Random实例包括java.util.Random的实例或Math.random()的方式，避免因共享该实例而在并发时竞争同一seed导致性能下降， jdk7后使用ThreadLocalRandom代替
    * volatile解决内存不可见问题， 对于一写多读，是可以解决变量同步问题，但是如果多写，同样无法解决线程安全问题
        * count++ ==> AtomicInteger count = new AtomicInteger(); count.addAndGet(1)
        * LongAdder对象比AtomicLong性能更好， 减少乐观锁的重试次数
    * HashMap在容量不够时进行resize时由于高并发可能出现死链， 导致cpu飙升， 开发中可使用其它数据结构或者加锁来规避此风险
    * ThreadLocal 修饰为 static, 同一线程内共享此类所有变量
    
#####7. 控制语句
    * switch 需要注释执行哪步骤/bread/return， 必须default，哪怕没有代码
    * if/else/for/while/do必须使用大括号，避免一行代码 if (conditions) statements; 
    * if else 不可超过3层
        * if (conditions) {
        *   reutrn;
        * }
        * // 执行else操作
    * if 判断条件可赋值给有意义的boolean变量, final boolean isExist = xxx && xxx1
    * 循环体内避免定义对象/变量/捕捉异常/获取数据库链接
    
#####8. 注释规约
    * 类/类属性/类方法，必须使用Javadoc规范， /**内容*/格式
    * 类必须添加创建者/创建时间
    * 方法内注释
        * 单行注释语句上一行// 注释内容
        * 多行注释语句 /*注释内容*/ 与注释语句对齐
    * 所有枚举类型字段必须有注释
    * //TODO 待处理说明
    * //FIXME  错误代码需要处理
    
#####9. 其它
    * 正则表达式利用其预编译功能，避免方法内Pattern pattern = new Pattern(正则表达式)
    * 后台传送变量至页面， $!{var}，如果var不存在或为null，则直接在页面上显示${var}
    * ///三个斜杠说明注释代码可能后续会恢复的原由
        
    
        
        
        
    
          
    