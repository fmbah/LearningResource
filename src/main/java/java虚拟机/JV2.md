参照: https://mp.weixin.qq.com/s/-K56NWVFiFsL8JSIH42QMA
````
grep forest f.txt     #文件查找
grep forest f.txt cpf.txt #多文件查找
grep 'log' /home/admin -r -n #目录下查找所有符合关键字的文件
cat f.txt | grep -i shopbase    
grep 'shopbase' /home/admin -r -n --include *.{vm,java} #指定文件后缀
grep 'shopbase' /home/admin -r -n --exclude *.{vm,java} #反匹配
netstat -nat|awk  '{print $6}'|sort|uniq -c|sort -rn 
#查看当前连接，注意close_wait偏高的情况，比如如下
ps -ef | grep java
top -H -p pid 获得线程10进制转16进制后jstack去抓看这个线程到底在干啥
````


````
- jps
sudo -u admin /opt/taobao/java/bin/jps -mlvV
- jstack 
sudo -u admin /opt/taobao/install/ajdk-8_1_1_fp1-b52/bin/jstack 2815
native+java栈:
sudo -u admin /opt/taobao/install/ajdk-8_1_1_fp1-b52/bin/jstack -m 2815
- jinfo 可看系统启动的参数，如下
sudo -u admin /opt/taobao/install/ajdk-8_1_1_fp1-b52/bin/jinfo -flags 2815
- jmap
1. 查看堆使用情况
sudo -u admin /opt/taobao/install/ajdk-8_1_1_fp1-b52/bin/jmap -heap 2815
2. dump
sudo -u admin /opt/taobao/install/ajdk-8_1_1_fp1-b52/bin/jmap -dump:live,format=b,file=/tmp/heap2.bin 2815
或
sudo -u admin /opt/taobao/install/ajdk-8_1_1_fp1-b52/bin/jmap -dump:format=b,file=/tmp/heap3.bin 2815
3.看看堆都被谁占了? 再配合zprofiler和btrace，排查问题简直是如虎添翼
sudo -u admin /opt/taobao/install/ajdk-8_1_1_fp1-b52/bin/jmap -histo 2815 | head -10
- jstat
sudo -u admin /opt/taobao/install/ajdk-8_1_1_fp1-b52/bin/jstat -gcutil 2815 1000 
- jdb
时至今日，jdb也是经常使用的。 http://docs.oracle.com/javase/7/docs/technotes/tools/windows/jdb.html
jdb可以用来预发debug,假设你预发的java_home是/opt/taobao/java/，远程调试端口是8000.那么
sudo -u admin /opt/taobao/java/bin/jdb -attach 8000

- 你的类到底是从哪个文件加载进来的？
-XX:+TraceClassLoading
结果形如[Loaded java.lang.invoke.MethodHandleImpl$Lazy from D:\progr

- 应用挂了输出dump文件
-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/admin/logs/java.hprof
集团的vm参数里边基本都有这个选项
````

