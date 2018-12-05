###jvm GC日志分析
````
加入了参数: -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
4617.273: [GC (GCLocker Initiated GC) [PSYoungGen: 27744K->96K(28160K)] 210054K->182414K(261632K), 0.0060840 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
4617.426: [GC (Allocation Failure) [PSYoungGen: 27744K->96K(28160K)] 210064K->182440K(261632K), 0.0049822 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
YoungGC格式如下:
系统运行多少秒: [GC (因为?原因进行了YoungGC) [年轻代: YoungGC前新生代内存占用->YoungGC后新生代内存占用(新生代总大小)] YoungGC前JVM堆内存占用->YoungGC后JVM堆内存占用(JVM堆总大小), YoungGC耗时] [耗时: YoungGC用户耗时, YoungGC系统耗时, YoungGC实际耗时]



6.025: [Full GC (Ergonomics) [PSYoungGen: 512K->0K(28160K)] [PSOldGen: 231165K->61441K(233472K)] 231677K->61441K(261632K), [Metaspace: 33141K->33141K(1079296K)], 0.0958541 secs] [Times: user=0.07 sys=0.01, real=0.10 secs]
FullGC格式如下:
系统运行了多少秒: [Full GC (?原因进行了FullGC)] [年轻代: GC前内存占用->GC后内存占用(年轻代内存总大小)] [Old区: GC前Old区占用大小->GC后Old区占用大小(Old占用总大小)] GC前堆内存占用->GC后堆内存占用(GC堆内存总大小), [永久区/元数据区: GC前占用内存大小->GC后占用内存大小(永久区/元数据区总大小), GC耗时] [耗时: GC用户时间, GC系统耗时, GC实际耗时]



````

