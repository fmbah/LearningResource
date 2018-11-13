##jinfo
*进程的参数信息*
> sudo ./jinfo -flags 49519

- Attaching to process ID 49519, please wait...
- Debugger attached successfully.
- Server compiler detected.
- JVM version is 25.171-b11
- Non-default VM flags: 
- -XX:CICompilerCount=2 
- -XX:InitialHeapSize=33554432 
- -XX:MaxHeapSize=524288000 
- -XX:MaxNewSize=174587904 
- -XX:MinHeapDeltaBytes=524288 
- -XX:NewSize=11010048 
- -XX:OldSize=22544384 
- -XX:+UseCompressedClassPointers 
- -XX:+UseCompressedOops 
- -XX:+UseParallelGC 
- Command line:  
- -Djava.util.logging.config.file=/opt/apache-tomcat-9.0.8/conf/logging.properties 
- -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager 
- -Dspas.identity=/opt/apache-tomcat-9.0.8/bin/auth_scheduler.txt 
- -Dfile.encoding=UTF8 
- -Dsun.jnu.encoding=UTF8 
- -Djdk.tls.ephemeralDHKeySize=2048 
- -Djava.protocol.handler.pkgs=org.apache.catalina.webresources 
- -Dorg.apache.catalina.security.SecurityListener.UMASK=0027 
- -Xdebug -Xrunjdwp:transport=dt_socket,address=8088,server=y,suspend=n 
- -Dignore.endorsed.dirs= 
- -Dcatalina.base=/opt/apache-tomcat-9.0.8 
- -Dcatalina.home=/opt/apache-tomcat-9.0.8 
- -Djava.io.tmpdir=/opt/apache-tomcat-9.0.8/temp

*进程是否使用某回收机制*
- ./jinfo -flag UseParallelGC 49519
- -XX:+UseParallelGC
- ./jinfo -flag UseSerialGC 49519
- -XX:-UseSerialGC 

##GC类型
1. UseSerialGC  
> 虚拟机运行在Client模式的默认值，打开此开关参数后，使用Serial+Serial Old收集器组合进行垃圾收集。
2. UseParNewGC
> 打开此开关参数后，使用ParNew+Serial Old收集器组合进行垃圾收集。
3. UseConcMarkSweepGC
> 打开此开关参数后，使用ParNew+CMS+Serial Old收集器组合进行垃圾收集。Serial Old作为CMS收集器出现Concurrent Mode Failure的备用垃圾收集器。
4. UseParallelGC
> 虚拟机运行在Server模式的默认值，打开此开关参数后，使用Parallel Scavenge+Serial Old收集器组合进行垃圾收集。
5. UseParallelOldGC
> 打开此开关参数后，使用Parallel Scavenge+Parallel Old收集器组合进行垃圾收集。

> 使用-XX:+上述GC策略可以开启对应的GC策略。  

##GC日志打印
1. -XX:+PrintGC 输出GC日志
2. -XX:+PrintGCDetails 输出GC详细日志
3. -XX:+PrintGCTimeStamps 输出GC的时间戳(以基准时间的形式)
4. -XX:+PrintGCDateStamps 输出GC的时间戳(以日期的形式)
5. -XX:+PrintHeapAtGC 在进行GC的前后打印出堆的信息
6. -Xloggc:../logs/gc.log 日志文件的输出路径

````
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

javac A

jstat -gcutil pid 1000



````