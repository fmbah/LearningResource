### Mysql
*   列 
    * AUTO_INCREMENT:自动增量
    * UNSIGNED:正数, 范围翻大一倍
    * NOT NULL:非空
    * TINYINT: (0~127) UNSIGNED（0～255） 超过范围AUTO_INCREMENT失效
    * select @@version;
    * select @@autocommit;
    * show table status from '数据库名' where name = '表名';
    * show engines;
    * show collation;
    * alter table t default character set utf8mb4 collate=utf8mb4_general_ci;
    * alter database a default character set utf8mb4 collate=utf8mb4_general_ci;
    
     1、一张表，里面有ID自增主键，当insert了17条记录之后，删除了第15,16,17条记录，再把Mysql重启，再insert一条记录，这条记录的ID是18还是15 ？
     
  
      2、Mysql的技术特点是什么？
  
  Mysql数据库软件是一个客户端或服务器系统，其中包括：支持各种客户端程序和库的多线程SQL服务器、不同的后端、广泛的应用程序编程接口和管理工具。
  
      3、Heap表是什么？
  
  HEAP表存在于内存中，用于临时高速存储。
  
      BLOB或TEXT字段是不允许的
      只能使用比较运算符=，<，>，=>，= <
      HEAP表不支持AUTO_INCREMENT
      索引不可为NULL
  
      4、Mysql服务器默认端口是什么？
  
  Mysql服务器的默认端口是3306。
  
      5、与Oracle相比，Mysql有什么优势？
  
  Mysql是开源软件，随时可用，无需付费。
  Mysql是便携式的
  带有命令提示符的GUI。
  使用Mysql查询浏览器支持管理
  
      6、如何区分FLOAT和DOUBLE？
  
  以下是FLOAT和DOUBLE的区别：
  
      浮点数以8位精度存储在FLOAT中，并且有四个字节。
      浮点数存储在DOUBLE中，精度为18位，有八个字节。
  
      7、区分CHAR_LENGTH和LENGTH？
  
  CHAR_LENGTH是字符数，而LENGTH是字节数。Latin字符的这两个数据是相同的，但是对于Unicode和其他编码，它们是不同的。
  
      8、请简洁描述Mysql中InnoDB支持的四种事务隔离级别名称，以及逐级之间的区别？
  
  SQL标准定义的四个隔离级别为：
  
      read uncommited ：读到未提交数据
      read committed：脏读，不可重复读
      repeatable read：可重读
      serializable ：串行事物
  
      9、在Mysql中ENUM的用法是什么？
  
  ENUM是一个字符串对象，用于指定一组预定义的值，并可在创建表时使用。
  
  Create table size(name ENUM('Smail,'Medium','Large');
  
      10、如何定义REGEXP？
  
  REGEXP是模式匹配，其中匹配模式在搜索值的任何位置。
  
      11、CHAR和VARCHAR的区别？
  
  以下是CHAR和VARCHAR的区别：
  
      CHAR和VARCHAR类型在存储和检索方面有所不同
      CHAR列长度固定为创建表时声明的长度，长度值范围是1到255
  
  当CHAR值被存储时，它们被用空格填充到特定长度，检索CHAR值时需删除尾随空格。
  
      12、列的字符串类型可以是什么？
  
  字符串类型是：
  
      SET
      BLOB
      ENUM
      CHAR
      TEXT
      VARCHAR
  
      13、如何获取当前的Mysql版本？
  
  SELECT VERSION();用于获取当前Mysql的版本。
  
      14、Mysql中使用什么存储引擎？
  
  存储引擎称为表类型，数据使用各种技术存储在文件中。
  
  技术涉及：
  
      Storage mechanism
      Locking levels
      Indexing
      Capabilities and functions.
  
      15、Mysql驱动程序是什么？
  
  以下是Mysql中可用的驱动程序：
  
      PHP驱动程序
      JDBC驱动程序
      ODBC驱动程序
      CWRAPPER
      PYTHON驱动程序
      PERL驱动程序
      RUBY驱动程序
      CAP11PHP驱动程序
      Ado.net5.mxj
  
      16、TIMESTAMP在UPDATE CURRENT_TIMESTAMP数据类型上做什么？
  
  创建表时TIMESTAMP列用Zero更新。只要表中的其他字段发生更改，UPDATE CURRENT_TIMESTAMP修饰符就将时间戳字段更新为当前时间。
  
      17、主键和候选键有什么区别？
  
  表格的每一行都由主键唯一标识,一个表只有一个主键。
  
  主键也是候选键。按照惯例，候选键可以被指定为主键，并且可以用于任何外键引用。
  
      18、如何使用Unix shell登录Mysql？
  
  我们可以通过以下命令登录：
  
  # [mysql dir]/bin/mysql -h hostname -u <UserName> -p <password>
  
      19、 myisamchk是用来做什么的？
  
  它用来压缩MyISAM表，这减少了磁盘或内存使用。
  
      20、MYSQL数据库服务器性能分析的方法命令有哪些?
  
      21、如何控制HEAP表的最大尺寸？
  
  Heal表的大小可通过称为max_heap_table_size的Mysql配置变量来控制。
  
      22、MyISAM Static和MyISAM Dynamic有什么区别？
  
  在MyISAM Static上的所有字段有固定宽度。动态MyISAM表将具有像TEXT，BLOB等字段，以适应不同长度的数据类型。
  
  MyISAM Static在受损情况下更容易恢复。
  
      23、federated表是什么？
  
  federated表，允许访问位于其他服务器数据库上的表。
  
      24、如果一个表有一列定义为TIMESTAMP，将发生什么？
  
  每当行被更改时，时间戳字段将获取当前时间戳。
  
      25、列设置为AUTO INCREMENT时，如果在表中达到最大值，会发生什么情况？
  
  它会停止递增，任何进一步的插入都将产生错误，因为密钥已被使用。
  
      26、怎样才能找出最后一次插入时分配了哪个自动增量？
  
  LAST_INSERT_ID将返回由Auto_increment分配的最后一个值，并且不需要指定表名称。
  
      27、你怎么看到为表格定义的所有索引？
  
  索引是通过以下方式为表格定义的：
  
  SHOW INDEX FROM <tablename>;
  
      28.、LIKE声明中的％和_是什么意思？
  
  ％对应于0个或更多字符，_只是LIKE语句中的一个字符。
  
      29、如何在Unix和Mysql时间戳之间进行转换？
  
      UNIX_TIMESTAMP是从Mysql时间戳转换为Unix时间戳的命令
      FROM_UNIXTIME是从Unix时间戳转换为Mysql时间戳的命令
  
      30、列对比运算符是什么？
  
  在SELECT语句的列比较中使用=，<>，<=，<，> =，>，<<，>>，<=>，AND，OR或LIKE运算符。
  
      31、我们如何得到受查询影响的行数？
  
  行数可以通过以下代码获得：
  
  SELECT COUNT(user_id)FROM users;
  
      32、Mysql查询是否区分大小写？
  
  不区分
  
  SELECT VERSION(), CURRENT_DATE;
  
  SeLect version(), current_date;
  
  seleCt vErSiOn(), current_DATE;
  
  所有这些例子都是一样的，Mysql不区分大小写。
  
      33.、LIKE和REGEXP操作有什么区别？
  
  LIKE和REGEXP运算符用于表示^和％。
  
  12
  	
  
  SELECT * FROM employee WHERE emp_name REGEXP "^b";SELECT * FROM employee WHERE emp_name LIKE "%b";
  
      34.、BLOB和TEXT有什么区别？
  
  BLOB是一个二进制对象，可以容纳可变数量的数据。有四种类型的BLOB -
  
      TINYBLOB
      BLOB
      MEDIUMBLOB和
      LONGBLOB
  
  它们只能在所能容纳价值的最大长度上有所不同。
  
  TEXT是一个不区分大小写的BLOB。四种TEXT类型
  
      TINYTEXT
      TEXT
      MEDIUMTEXT和
      LONGTEXT
  
  它们对应于四种BLOB类型，并具有相同的最大长度和存储要求。
  
  BLOB和TEXT类型之间的唯一区别在于对BLOB值进行排序和比较时区分大小写，对TEXT值不区分大小写。
  
      35、mysql_fetch_array和mysql_fetch_object的区别是什么？
  
  以下是mysql_fetch_array和mysql_fetch_object的区别：
  
  mysql_fetch_array（） - 将结果行作为关联数组或来自数据库的常规数组返回。
  
  mysql_fetch_object - 从数据库返回结果行作为对象。
  
      36、我们如何在mysql中运行批处理模式？
  
  以下命令用于在批处理模式下运行：
  
  mysql;
  
  mysql mysql.out
  
      37、MyISAM表格将在哪里存储，并且还提供其存储格式？
  
  每个MyISAM表格以三种格式存储在磁盘上：
  
  ·“.frm”文件存储表定义
  
  数据文件具有“.MYD”（MYData）扩展名
  
  索引文件具有“.MYI”（MYIndex）扩展名
  
      38.、Mysql中有哪些不同的表格？
  
  共有5种类型的表格：
  
      MyISAM
      Heap
      Merge
      INNODB
      ISAM
  
  MyISAM是Mysql的默认存储引擎。
  
      39、ISAM是什么？
  
  ISAM简称为索引顺序访问方法。它是由IBM开发的，用于在磁带等辅助存储系统上存储和检索数据。
  
      40、InnoDB是什么？
  
  lnnoDB是一个由Oracle公司开发的Innobase Oy事务安全存储引擎。
  
      41、Mysql如何优化DISTINCT？
  
  DISTINCT在所有列上转换为GROUP BY，并与ORDER BY子句结合使用。
  
  1
  	
  
  SELECT DISTINCT t1.a FROM t1,t2 where t1.a=t2.a;
  
  42、如何输入字符为十六进制数字？
  
  如果想输入字符为十六进制数字，可以输入带有单引号的十六进制数字和前缀（X），或者只用（Ox）前缀输入十六进制数字。
  
  如果表达式上下文是字符串，则十六进制数字串将自动转换为字符串。
  
      43、如何显示前50行？
  
  在Mysql中，使用以下代码查询显示前50行：
  
  SELECT*FROM
  
  LIMIT 0,50;
  
      44、可以使用多少列创建索引？
  
  任何标准表最多可以创建16个索引列。
  
      45、NOW（）和CURRENT_DATE（）有什么区别？
  
  NOW（）命令用于显示当前年份，月份，日期，小时，分钟和秒。
  
  CURRENT_DATE（）仅显示当前年份，月份和日期。
  
      46、什么样的对象可以使用CREATE语句创建？
  
  以下对象是使用CREATE语句创建的：
  
      DATABASE
      EVENT
      FUNCTION
      INDEX
      PROCEDURE
      TABLE
      TRIGGER
      USER
      VIEW
  
      47、Mysql表中允许有多少个TRIGGERS？
  
  在Mysql表中允许有六个触发器，如下：
  
      BEFORE INSERT
      AFTER INSERT
      BEFORE UPDATE
      AFTER UPDATE
      BEFORE DELETE
      AFTER DELETE
  
      48、什么是非标准字符串类型？
  
  以下是非标准字符串类型：
  
      TINYTEXT
      TEXT
      MEDIUMTEXT
      LONGTEXT
  
      49、什么是通用SQL函数？
  
      CONCAT(A, B) - 连接两个字符串值以创建单个字符串输出。通常用于将两个或多个字段合并为一个字段。
      FORMAT(X, D)- 格式化数字X到D有效数字。
      CURRDATE(), CURRTIME()- 返回当前日期或时间。
      NOW（） - 将当前日期和时间作为一个值返回。
      MONTH（），DAY（），YEAR（），WEEK（），WEEKDAY（） - 从日期值中提取给定数据。
      HOUR（），MINUTE（），SECOND（） - 从时间值中提取给定数据。
      DATEDIFF（A，B） - 确定两个日期之间的差异，通常用于计算年龄
      SUBTIMES（A，B） - 确定两次之间的差异。
      FROMDAYS（INT） - 将整数天数转换为日期值。
  
      50、解释访问控制列表
  
  ACL（访问控制列表）是与对象关联的权限列表。这个列表是Mysql服务器安全模型的基础，它有助于排除用户无法连接的问题。
  
  Mysql将ACL（也称为授权表）缓存在内存中。当用户尝试认证或运行命令时，Mysql会按照预定的顺序检查ACL的认证信息和权限。
  
      51、MYSQL支持事务吗？
  
  在缺省模式下，MYSQL是autocommit模式的，所有的数据库更新操作都会即时提交，所以在缺省情况下，mysql是不支持事务的。
  
  但是如果你的MYSQL表类型是使用InnoDB Tables 或 BDB tables的话，你的MYSQL就可以使用事务处理,使用SET AUTOCOMMIT=0就可以使MYSQL允许在非autocommit模式，在非autocommit模式下，你必须使用COMMIT来提交你的更改，或者用ROLLBACK来回滚你的更改。
  
  示例如下：
  
  一
  
  START TRANSACTION;
  
  SELECT @A:=SUM(salary) FROM table1 WHERE type=1;
  
  UPDATE table2 SET summmary=@A WHERE type=1;
  
  COMMIT;
  
      52、 mysql里记录货币用什么字段类型好
  
  NUMERIC和DECIMAL类型被Mysql实现为同样的类型，这在SQL92标准允许。他们被用于保存值，该值的准确精度是极其重要的值，例如与金钱有关的数据。当声明一个类是这些类型之一时，精度和规模的能被(并且通常是)指定。
  
  例如：
  
  salary DECIMAL(9,2)
  
  在这个例子中，9(precision)代表将被用于存储值的总的小数位数，而2(scale)代表将被用于存储小数点后的位数。
  
  因此，在这种情况下，能被存储在salary列中的值的范围是从-9999999.99到9999999.99。在ANSI/ISO SQL92中，句法DECIMAL(p)等价于DECIMAL(p,0)。
  
  同样，句法DECIMAL等价于DECIMAL(p,0)，这里实现被允许决定值p。Mysql当前不支持DECIMAL/NUMERIC数据类型的这些变种形式的任一种。
  
  这一般说来不是一个严重的问题，因为这些类型的主要益处得自于明显地控制精度和规模的能力。
  
  DECIMAL和NUMERIC值作为字符串存储，而不是作为二进制浮点数，以便保存那些值的小数精度。
  
  一个字符用于值的每一位、小数点(如果scale>0)和“-”符号(对于负值)。如果scale是0，DECIMAL和NUMERIC值不包含小数点或小数部分。
  
  DECIMAL和NUMERIC值得最大的范围与DOUBLE一样，但是对于一个给定的DECIMAL或NUMERIC列，实际的范围可由制由给定列的precision或scale限制。
  
  当这样的列赋给了小数点后面的位超过指定scale所允许的位的值，该值根据scale四舍五入。
  
  当一个DECIMAL或NUMERIC列被赋给了其大小超过指定(或缺省的）precision和scale隐含的范围的值，Mysql存储表示那个范围的相应的端点值。
  
  我希望本文可以帮助你提升技术水平。那些，感觉学的好难，甚至会令你沮丧的人，别担心，我认为，如果你愿意试一试本文介绍的几点，会向前迈进，克服这种感觉。这些要点也许对你不适用，但你会明确一个重要的道理：接受自己觉得受困这个事实是摆脱这个困境的第一步。
  
      53、MYSQL数据表在什么情况下容易损坏？
  
  服务器突然断电导致数据文件损坏。
  
  强制关机，没有先关闭mysql 服务等。
  
      54、mysql有关权限的表都有哪几个？
  
  Mysql服务器通过权限表来控制用户对数据库的访问，权限表存放在mysql数据库里，由mysql_install_db脚本初始化。这些权限表分别user，db，table_priv，columns_priv和host。
  
      55、Mysql中有哪几种锁？
  
      MyISAM支持表锁，InnoDB支持表锁和行锁，默认为行锁
      表级锁：开销小，加锁快，不会出现死锁。锁定粒度大，发生锁冲突的概率最高，并发量最低
      行级锁：开销大，加锁慢，会出现死锁。锁力度小，发生锁冲突的概率小，并发度最高
* mysql自带工具mysqlslap压力测试
* mysql -uroot -p -hlocalhost -P3306
    1. 显示当前数据库服务器的连接数：show processlist
* mysqlslap
    1. 常用参数
    ````
    --auto-generate-sql, -a 自动生成测试表和数据，表示用mysqlslap工具自己生成的SQL脚本来测试并发压力。
    --auto-generate-sql-load-type=type 测试语句的类型。代表要测试的环境是读操作还是写操作还是两者混合的。取值包括：read，key，write，update和mixed(默认)。
    --auto-generate-sql-add-auto-increment 代表对生成的表自动添加auto_increment列，从5.1.18版本开始支持。
    --number-char-cols=N, -x N 自动生成的测试表中包含多少个字符类型的列，默认1
    --number-int-cols=N, -y N 自动生成的测试表中包含多少个数字类型的列，默认1
    --number-of-queries=N 总的测试查询次数(并发客户数×每客户查询次数)
    --query=name,-q 使用自定义脚本执行测试，例如可以调用自定义的一个存储过程或者sql语句来执行测试。
    --create-schema 代表自定义的测试库名称，测试的schema，MySQL中schema也就是database。
    --commint=N 多少条DML后提交一次。
    --compress, -C 如果服务器和客户端支持都压缩，则压缩信息传递。
    --concurrency=N, -c N 表示并发量，也就是模拟多少个客户端同时执行select。可指定多个值，以逗号或者--delimiter参数指定的值做为分隔符。例如：--concurrency=100,200,500。
    --engine=engine_name, -e engine_name 代表要测试的引擎，可以有多个，用分隔符隔开。例如：--engines=myisam,innodb。
    --iterations=N, -i N 测试执行的迭代次数，代表要在不同并发环境下，各自运行测试多少次。
    --only-print 只打印测试语句而不实际执行。
    --detach=N 执行N条语句后断开重连。
    --debug-info, -T 打印内存和CPU的相关信息。
    ````
    2. 实例
    ````
    # 单线程测试。测试做了什么。
    mysqlslap -a -uroot -p123456
     
    # 多线程测试。使用–concurrency来模拟并发连接。
    mysqlslap -a -c 100 -uroot -p123456
     
    # 迭代测试。用于需要多次执行测试得到平均值。
    mysqlslap -a -i 10 -uroot -p123456
     
    mysqlslap ---auto-generate-sql-add-autoincrement -a -uroot -p123456
    mysqlslap -a --auto-generate-sql-load-type=read -uroot -p123456
    mysqlslap -a --auto-generate-secondary-indexes=3 -uroot -p123456
    mysqlslap -a --auto-generate-sql-write-number=1000 -uroot -p123456
    mysqlslap --create-schema world -q "select count(*) from City" -uroot -p123456
    mysqlslap -a -e innodb -uroot -p123456
    mysqlslap -a --number-of-queries=10 -uroot -p123456
     
    # 测试同时不同的存储引擎的性能进行对比：
    mysqlslap -a --concurrency=50,100 --number-of-queries 1000 --iterations=5 --engine=myisam,innodb --debug-info -uroot -p123456
     
    # 执行一次测试，分别50和100个并发，执行1000次总查询：
    mysqlslap -a --concurrency=50,100 --number-of-queries 1000 --debug-info -uroot -p123456
     
    # 50和100个并发分别得到一次测试结果(Benchmark)，并发数越多，执行完所有查询的时间越长。为了准确起见，可以多迭代测试几次:
    mysqlslap -a --concurrency=50,100 --number-of-queries 1000 --iterations=5 --debug-info -uroot -p123456
    ````
    
    
    网上有很多的文章教怎么配置MySQL服务器，但考虑到服务器硬件配置的不同，具体应用的差别，那些文章的做法只能作为初步设置参考，我们需要根据自己的情况进行配置优化，好的做法是MySQL服务器稳定运行了一段时间后运行，根据服务器的”状态”进行优化。
    　　mysql> show global status;
    　　可以列出MySQL服务器运行各种状态值，另外，查询MySQL服务器配置信息语句：
    　　mysql> show variables;
    　　一、慢查询 
    　　mysql> show variables like '%slow%'; 
    　　+------------------+-------+ 
    　　| Variable_name | Value | 
    　　+------------------+-------+ 
    　　| log_slow_queries | ON | 
    　　| slow_launch_time | 2 | 
    　　+------------------+-------+ 
    　　mysql> show global status like '%slow%'; 
    　　+---------------------+-------+ 
    　　| Variable_name | Value | 
    　　+---------------------+-------+ 
    　　| Slow_launch_threads | 0 | 
    　　| Slow_queries | 4148 | 
    　　+---------------------+-------+　　
    
            配置中打开了记录慢查询，执行时间超过2秒的即为慢查询，系统显示有4148个慢查询，你可以分析慢查询日志，找出有问题的SQL语句，慢查询时间不宜设置过长，否则意义不大，最好在5秒以内，如果你需要微秒级别的慢查询，可以考虑给MySQL打补丁：http://www.percona.com/docs/wiki/release:start，记得找对应的版本。
    　　打开慢查询日志可能会对系统性能有一点点影响，如果你的MySQL是主-从结构，可以考虑打开其中一台从服务器的慢查询日志，这样既可以监控慢查询，对系统性能影响又小。
    
    
    　　二、连接数 
    　　经常会遇见”MySQL: ERROR 1040: Too many connections”的情况，一种是访问量确实很高，MySQL服务器抗不住，这个时候就要考虑增加从服务器分散读压力，另外一种情况是MySQL配置文件中max_connections值过小： 
    　　mysql> show variables like 'max_connections'; 
    　　+-----------------+-------+ 
    　　| Variable_name | Value | 
    　　+-----------------+-------+ 
    　　| max_connections | 256 | 
    　　+-----------------+-------+　　
    
           这台MySQL服务器最大连接数是256，然后查询一下服务器响应的最大连接数： 
    　　mysql> show global status like ‘Max_used_connections’;
    　　MySQL服务器过去的最大连接数是245，没有达到服务器连接数上限256，应该没有出现1040错误，比较理想的设置是：
    　　Max_used_connections / max_connections * 100% ≈ 85%
    　　最大连接数占上限连接数的85%左右，如果发现比例在10%以下，MySQL服务器连接数上限设置的过高了。
    
    
    　　三、Key_buffer_size 
    　　key_buffer_size是对MyISAM表性能影响最大的一个参数，下面一台以MyISAM为主要存储引擎服务器的配置： 
    　　mysql> show variables like ‘key_buffer_size’; 
    　　+-----------------+------------+ 
    　　| Variable_name | Value | 
    　　+-----------------+------------+ 
    　　| key_buffer_size | 536870912 | 
    　　+-----------------+------------+　　
    
           分配了512MB内存给key_buffer_size，我们再看一下key_buffer_size的使用情况： 
    　　mysql> show global status like 'key_read%'; 
    　　+------------------------+-------------+ 
    　　| Variable_name | Value | mysql 
    　　+------------------------+-------------+ 
    　　| Key_read_requests | 27813678764 | 
    　　| Key_reads | 6798830 | 
    　　+------------------------+-------------+　　
    
           一共有27813678764个索引读取请求，有6798830个请求在内存中没有找到直接从硬盘读取索引，计算索引未命中缓存的概率：
    　　key_cache_miss_rate = Key_reads / Key_read_requests * 100%
    　　比如上面的数据，key_cache_miss_rate为0.0244%，4000个索引读取请求才有一个直接读硬盘，已经很BT了,key_cache_miss_rate在0.1%以下都很好(每1000个请求有一个直接读硬盘)，如果key_cache_miss_rate在0.01%以下的话，key_buffer_size分配的过多，可以适当减少。
    　　MySQL服务器还提供了key_blocks_*参数： 
    　　mysql> show global status like 'key_blocks_u%'; 
    　　+------------------------+-------------+ 
    　　| Variable_name | Value | 
    　　+------------------------+-------------+ 
    　　| Key_blocks_unused | 0 | 
    　　| Key_blocks_used | 413543 | 
    　　+------------------------+-------------+　　
    
           Key_blocks_unused表示未使用的缓存簇(blocks)数，Key_blocks_used表示曾经用到的最大的blocks数，比如这台服务器，所有的缓存都用到了，要么增加key_buffer_size，要么就是过渡索引了，把缓存占满了。比较理想的设置： 
    　　Key_blocks_used / (Key_blocks_unused + Key_blocks_used) * 100% ≈ 80%
    
    
    　　四、临时表 
    　　mysql> show global status like 'created_tmp%'; 
    　　+-------------------------+---------+ 
    　　| Variable_name | Value | 
    　　+-------------------------+---------+ 
    　　| Created_tmp_disk_tables | 21197 | 
    　　| Created_tmp_files | 58 | 
    　　| Created_tmp_tables | 1771587 | 
    　　+-------------------------+---------+　　
    
           每次创建临时表，Created_tmp_tables增加，如果是在磁盘上创建临时表，Created_tmp_disk_tables也增加,Created_tmp_files表示MySQL服务创建的临时文件文件数，比较理想的配置是：
    　　Created_tmp_disk_tables / Created_tmp_tables * 100% <= 25% 
    　　比如上面的服务器Created_tmp_disk_tables / Created_tmp_tables * 100% = 1.20%，应该相当好了。我们再看一下MySQL服务器对临时表的配置： 
    　　mysql> show variables where Variable_name in ('tmp_table_size', 'max_heap_table_size'); 
    　　+---------------------+-----------+ 
    　　| Variable_name | Value | 
    　　+---------------------+-----------+ 
    　　| max_heap_table_size | 268435456 | 
    　　| tmp_table_size | 536870912 | 
    　　+---------------------+-----------+　　
    
          只有256MB以下的临时表才能全部放内存，超过的就会用到硬盘临时表。
    　　五、Open Table情况 
    　　mysql> show global status like 'open%tables%'; 
    　　+---------------+-------+ 
    　　| Variable_name | Value | 
    　　+---------------+-------+ 
    　　| Open_tables | 919 | 
    　　| Opened_tables | 1951 | 
    　　+---------------+-------+　　
    
          Open_tables表示打开表的数量，Opened_tables表示打开过的表数量，如果Opened_tables数量过大，说明配置中table_cache(5.1.3之后这个值叫做table_open_cache)值可能太小，我们查询一下服务器table_cache值： 
    　　mysql> show variables like 'table_cache'; 
    　　+---------------+-------+ 
    　　| Variable_name | Value | 
    　　+---------------+-------+ 
    　　| table_cache | 2048 | 
    　　+---------------+-------+
    　　比较合适的值为： 
    　　Open_tables / Opened_tables * 100% >= 85%
    　　Open_tables / table_cache * 100% <= 95%
    
    
    　　六、进程使用情况
    　　mysql> show global status like ‘Thread%’; 
    　　+-------------------+-------+ 
    　　| Variable_name | Value | 
    　　+-------------------+-------+ 
    　　| Threads_cached | 46 | 
    　　| Threads_connected | 2 | 
    　　| Threads_created | 570 | 
    　　| Threads_running | 1 | 
    　　+-------------------+-------+　　
    
          如果我们在MySQL服务器配置文件中设置了thread_cache_size，当客户端断开之后，服务器处理此客户的线程将会缓存起来以响应下一个客户而不是销毁(前提是缓存数未达上限)。Threads_created表示创建过的线程数，如果发现Threads_created值过大的话，表明MySQL服务器一直在创建线程，这也是比较耗资源，可以适当增加配置文件中thread_cache_size值，查询服务器thread_cache_size配置： 
    　　mysql> show variables like 'thread_cache_size'; 
    　　+-------------------+-------+ 
    　　| Variable_name | Value | 
    　　+-------------------+-------+ 
    　　| thread_cache_size | 64 | 
    　　+-------------------+-------+　　
    
          示例中的服务器还是挺健康的。
    
    
    　　七、查询缓存(query cache) 
    　　mysql> show global status like 'qcache%'; 
    　　+-------------------------+-----------+ 
    　　| Variable_name | Value | 
    　　+-------------------------+-----------+ 
    　　| Qcache_free_blocks | 22756 | 
    　　| Qcache_free_memory | 76764704 | 
    　　| Qcache_hits | 213028692 | 
    　　| Qcache_inserts | 208894227 | 
    　　| Qcache_lowmem_prunes | 4010916 | 
    　　| Qcache_not_cached | 13385031 | 
    　　| Qcache_queries_in_cache | 43560 | 
    　　| Qcache_total_blocks | 111212 | 
    　　+-------------------------+-----------+　　
    
           MySQL查询缓存变量解释：
    　　Qcache_free_blocks：缓存中相邻内存块的个数。数目大说明可能有碎片。FLUSH QUERY CACHE会对缓存中的碎片进行整理，从而得到一个空闲块。
    　　Qcache_free_memory：缓存中的空闲内存。 
    　　Qcache_hits：每次查询在缓存中命中时就增大
    　　Qcache_inserts：每次插入一个查询时就增大。命中次数除以插入次数就是不中比率。
    　　Qcache_lowmem_prunes：缓存出现内存不足并且必须要进行清理以便为更多查询提供空间的次数。这个数字最好长时间来看;如果这个数字在不断增长，就表示可能碎片非常严重，或者内存很少。(上面的 free_blocks和free_memory可以告诉您属于哪种情况) 
    　　Qcache_not_cached：不适合进行缓存的查询的数量，通常是由于这些查询不是 SELECT 语句或者用了now()之类的函数。
    　　Qcache_queries_in_cache：当前缓存的查询(和响应)的数量。
    　　Qcache_total_blocks：缓存中块的数量。
    　　我们再查询一下服务器关于query_cache的配置： 
    　　mysql> show variables like 'query_cache%'; 
    　　+------------------------------+-----------+ 
    　　| Variable_name | Value | 
    　　+------------------------------+-----------+ 
    　　| query_cache_limit | 2097152 | 
    　　| query_cache_min_res_unit | 4096 | 
    　　| query_cache_size | 203423744 | 
    　　| query_cache_type | ON | 
    　　| query_cache_wlock_invalidate | OFF |
    
           +——————————+———–+
    　　各字段的解释：
    　　query_cache_limit：超过此大小的查询将不缓存
    　　query_cache_min_res_unit：缓存块的最小大小
    　　query_cache_size：查询缓存大小
    　　query_cache_type：缓存类型，决定缓存什么样的查询，示例中表示不缓存 select sql_no_cache 查询 
    　　query_cache_wlock_invalidate：当有其他客户端正在对MyISAM表进行写操作时，如果查询在query cache中，是否返回cache结果还是等写操作完成再读表获取结果。
    　　query_cache_min_res_unit的配置是一柄”双刃剑”，默认是4KB，设置值大对大数据查询有好处，但如果你的查询都是小数据查询，就容易造成内存碎片和浪费。
    　　查询缓存碎片率 = Qcache_free_blocks / Qcache_total_blocks * 100%
    　　如果查询缓存碎片率超过20%，可以用FLUSH QUERY CACHE整理缓存碎片，或者试试减小query_cache_min_res_unit，如果你的查询都是小数据量的话。
    　　查询缓存利用率 = (query_cache_size - Qcache_free_memory) / query_cache_size * 100%
    　　查询缓存利用率在25%以下的话说明query_cache_size设置的过大，可适当减小;查询缓存利用率在80%以上而且Qcache_lowmem_prunes > 50的话说明query_cache_size可能有点小，要不就是碎片太多。 
    　　查询缓存命中率 = (Qcache_hits - Qcache_inserts) / Qcache_hits * 100%
    　　示例服务器 查询缓存碎片率 = 20.46%，查询缓存利用率 = 62.26%，查询缓存命中率 = 1.94%，命中率很差，可能写操作比较频繁吧，而且可能有些碎片。
    
    
    　　八、排序使用情况 
    　　mysql> show global status like 'sort%'; 
    　　+-------------------+------------+ 
    　　| Variable_name | Value | 
    　　+-------------------+------------+ 
    　　| Sort_merge_passes | 29 | 
    　　| Sort_range | 37432840 | 
    　　| Sort_rows | 9178691532 | 
    　　| Sort_scan | 1860569 | 
    　　+-------------------+------------+　　
    
           Sort_merge_passes 包括两步。MySQL 首先会尝试在内存中做排序，使用的内存大小由系统变量 Sort_buffer_size 决定，如果它的大小不够把所有的记录都读到内存中，MySQL 就会把每次在内存中排序的结果存到临时文件中，等 MySQL 找到所有记录之后，再把临时文件中的记录做一次排序。这再次排序就会增加 Sort_merge_passes。实际上，MySQL 会用另一个临时文件来存再次排序的结果，所以通常会看到 Sort_merge_passes 增加的数值是建临时文件数的两倍。因为用到了临时文件，所以速度可能会比较慢，增加 Sort_buffer_size 会减少 Sort_merge_passes 和 创建临时文件的次数。但盲目的增加 Sort_buffer_size 并不一定能提高速度，见 How fast can you sort data with MySQL?(引自http://qroom.blogspot.com/2007/09/mysql-select-sort.html，貌似被墙) mysql
    　　另外，增加read_rnd_buffer_size(3.2.3是record_rnd_buffer_size)的值对排序的操作也有一点的好处，参见：http://www.mysqlperformanceblog.com/2007/07/24/what-exactly-is-read_rnd_buffer_size/
    
    
    　　九、文件打开数(open_files) 
    　　mysql> show global status like 'open_files'; 
    　　+---------------+-------+ 
    　　| Variable_name | Value | 
    　　+---------------+-------+ 
    　　| Open_files | 1410 | 
    　　+---------------+-------+ 
    　　mysql> show variables like 'open_files_limit'; 
    　　+------------------+-------+ 
    　　| Variable_name | Value | 
    　　+------------------+-------+ 
    　　| open_files_limit | 4590 | 
    　　+------------------+-------+　　
    
          比较合适的设置：Open_files / open_files_limit * 100% <= 75%
    
    
    　　十、表锁情况 
    　　mysql> show global status like 'table_locks%'; 
    　　+-----------------------+-----------+ 
    　　| Variable_name | Value | 
    　　+-----------------------+-----------+ 
    　　| Table_locks_immediate | 490206328 | 
    　　| Table_locks_waited | 2084912 | 
    　　+-----------------------+-----------+　　
    
           Table_locks_immediate表示立即释放表锁数，Table_locks_waited表示需要等待的表锁数，如果Table_locks_immediate / Table_locks_waited > 5000，最好采用InnoDB引擎，因为InnoDB是行锁而MyISAM是表锁，对于高并发写入的应用InnoDB效果会好些。示例中的服务器Table_locks_immediate / Table_locks_waited = 235，MyISAM就足够了。
    
    
    　　十一、表扫描情况 
    　　mysql> show global status like 'handler_read%'; 
    　　+-----------------------+-------------+ 
    　　| Variable_name | Value | 
    　　+-----------------------+-------------+ 
    　　| Handler_read_first | 5803750 | 
    　　| Handler_read_key | 6049319850 | 
    　　| Handler_read_next | 94440908210 | 
    　　| Handler_read_prev | 34822001724 | 
    　　| Handler_read_rnd | 405482605 | 
    　　| Handler_read_rnd_next | 18912877839 | 
    　　+-----------------------+-------------+　　
    
            各字段解释参见http://hi.baidu.com/thinkinginlamp/blog/item/31690cd7c4bc5cdaa144df9c.html，调出服务器完成的查询请求次数： 
    　　mysql> show global status like 'com_select'; 
    　　+---------------+-----------+ 
    　　| Variable_name | Value | 
    　　+---------------+-----------+ 
    　　| Com_select | 222693559 | 
    　　+---------------+-----------+　　
    
           计算表扫描率：
    　　表扫描率 = Handler_read_rnd_next / Com_select
    　　如果表扫描率超过4000，说明进行了太多表扫描，很有可能索引没有建好，增加read_buffer_size值会有一些好处，但最好不要超过8MB。
    
    
    enum 
    set find_in_set('aa', column_name)
    blob 
    
    
    
    
#### 参照
[mysqlslap](http://xstarcd.github.io/wiki/index.html)
[性能测试](https://www.cnblogs.com/jackchen001/p/6964411.html)
[mysql锁](https://www.cnblogs.com/huanongying/p/7021555.html)


select @@autocommit;
//会话1中： 只锁当前一行
set autocommit = 0;
select * from 表名 where 表名.索引列 for update;
commit;
//会话2中： 锁当前表
set autocommit = 0;
select * from 表名 where 表名.非索引列 for update;
commit;

//mysql8以前版本
select @@tx_isolation;
set tx_isolation = 'read-uncommitted';
//mysql5以前版本
select @@transaction_isolation;
set transaction_isolation = 'read-uncommitted';


脏读：事物A更新了某数据但未提交，事物B可以到到事物A中未提交的数据，而后由于某原因事物A回滚了事物，则事物B中看到的数据是脏数据，即脏读。
不可重复读：事务 A 多次读取同一数据，事务 B 在事务A多次读取的过程中，对数据作了更新并提交，导致事务A多次读取同一数据时，结果 不一致。
幻读：系统管理员A将数据库中所有学生的成绩从具体分数改为ABCDE等级，但是系统管理员B就在这个时候插入了一条具体分数的记录，当系统管理员A改结束后发现还有一条记录没有改过来，就好像发生了幻觉一样，这就叫幻读。
会话1中：


SERIALIZABLE

create table tb_account ( id int(10) auto_increment not null primary key,name varchar(12) not null,balance decimal(9,2) not null) comment ''
create unique index tb_account_id_uindex on tb_account (id);
desc tb_account;
insert into tb_account (name, balance) values ('A', 500);
insert into tb_account (name, balance) values ('B', 1500);
insert into tb_account (name, balance) values ('C', 2500);
insert into tb_account (name, balance) values ('D', 3500);

READ-UNCOMMITTED（未提交读）
开启会话1：mysql -uroot -p -hlocalhost -P6379
            mysql> set transaction_isolation = 'read-uncommitted'
            mysql> start transaction
            mysql> select * from tb_account;
            +----+------+---------+
            | id | name | balance |
            +----+------+---------+
            |  1 | A    |  500.00 |
            |  2 | B    | 1500.00 |
            |  3 | C    | 2500.00 |
            |  4 | D    | 3500.00 |
            +----+------+---------+
            4 rows in set (0.00 sec)
            
            mysql> update tb_account set balance = balance - 50 where id = 1;
            Query OK, 1 row affected (0.00 sec)
            Rows matched: 1  Changed: 1  Warnings: 0
            
            mysql> select * from tb_account;
            +----+------+---------+
            | id | name | balance |
            +----+------+---------+
            |  1 | A    |  450.00 |
            |  2 | B    | 1500.00 |
            |  3 | C    | 2500.00 |
            |  4 | D    | 3500.00 |
            +----+------+---------+
            4 rows in set (0.00 sec)
开启会话2：mysql -uroot -p -hlocalhost -P6379
            set transaction_isolation = 'read-uncommitted'
            mysql> select * from tb_account;
            +----+------+---------+
            | id | name | balance |
            +----+------+---------+
            |  1 | A    |  450.00 |
            |  2 | B    | 1500.00 |
            |  3 | C    | 2500.00 |
            |  4 | D    | 3500.00 |
            +----+------+---------+
            4 rows in set (0.00 sec)
            可以看到在会话1中未提交事物，但是已经在会话2中可以查看到会话1的更新后的数据了，这属于脏读。
            
READ-COMMITTED（提交读）
会话1中：
mysql> select * from tb_account;
+----+------+---------+
| id | name | balance |
+----+------+---------+
|  1 | A    |  350.00 |
|  2 | B    | 1500.00 |
|  3 | C    | 2500.00 |
|  4 | D    | 3500.00 |
+----+------+---------+
4 rows in set (0.00 sec)

mysql> start transaction;
Query OK, 0 rows affected (0.00 sec)

mysql> update tb_account set balance = balance - 50 where id = 1;
Query OK, 1 row affected (0.01 sec)
Rows matched: 1  Changed: 1  Warnings: 0

mysql> select * from tb_account;
+----+------+---------+
| id | name | balance |
+----+------+---------+
|  1 | A    |  300.00 |
|  2 | B    | 1500.00 |
|  3 | C    | 2500.00 |
|  4 | D    | 3500.00 |
+----+------+---------+
4 rows in set (0.00 sec
mysql> commit;    
会话2中：
mysql> start transaction;
Query OK, 0 rows affected (0.00 sec)

mysql> select * from tb_account;
+----+------+---------+
| id | name | balance |
+----+------+---------+
|  1 | A    |  350.00 |
|  2 | B    | 1500.00 |
|  3 | C    | 2500.00 |
|  4 | D    | 3500.00 |
+----+------+---------+
4 rows in set (0.00 sec)

mysql> select * from tb_account;
+----+------+---------+
| id | name | balance |
+----+------+---------+
|  1 | A    |  300.00 |
|  2 | B    | 1500.00 |
|  3 | C    | 2500.00 |
|  4 | D    | 3500.00 |
+----+------+---------+
4 rows in set (0.00 sec)

REPEATABLE-READ（可重复读）     
set transaction_isolation = 'REPEATABLE-READ'
会话1
mysql> select * from tb_account;
+----+------+---------+
| id | name | balance |
+----+------+---------+
|  1 | A    |  250.00 |
|  2 | B    | 1500.00 |
|  3 | C    | 2500.00 |
|  4 | D    | 3500.00 |
+----+------+---------+
4 rows in set (0.00 sec)

mysql> start transaction;
Query OK, 0 rows affected (0.00 sec)

mysql> update tb_account set balance = balance - 50 where id = 1;
Query OK, 1 row affected (0.00 sec)
Rows matched: 1  Changed: 1  Warnings: 0

mysql> commit;
Query OK, 0 rows affected (0.00 sec)
          
会话2
mysql> start transaction;
Query OK, 0 rows affected (0.01 sec)

mysql> select * from tb_account;
+----+------+---------+
| id | name | balance |
+----+------+---------+
|  1 | A    |  250.00 |
|  2 | B    | 1500.00 |
|  3 | C    | 2500.00 |
|  4 | D    | 3500.00 |
+----+------+---------+
4 rows in set (0.00 sec)

mysql> select * from tb_account;
+----+------+---------+
| id | name | balance |
+----+------+---------+
|  1 | A    |  250.00 |
|  2 | B    | 1500.00 |
|  3 | C    | 2500.00 |
|  4 | D    | 3500.00 |
+----+------+---------+
4 rows in set (0.00 sec)

mysql> select * from tb_account;
+----+------+---------+
| id | name | balance |
+----+------+---------+
|  1 | A    |  250.00 |
|  2 | B    | 1500.00 |
|  3 | C    | 2500.00 |
|  4 | D    | 3500.00 |
+----+------+---------+
4 rows in set (0.00 sec)

mysql> commit;
Query OK, 0 rows affected (0.00 sec)

mysql> select * from tb_account;
+----+------+---------+
| id | name | balance |
+----+------+---------+
|  1 | A    |  200.00 |
|  2 | B    | 1500.00 |
|  3 | C    | 2500.00 |
|  4 | D    | 3500.00 |
+----+------+---------+
4 rows in set (0.00 sec)

逻辑架构：线程/连接处理-》解析器-》缓存-》优化器-》存储引擎
并发控制：
事物：ACDI 原子性/一致性/持久性/隔离性
隔离性：未提交读/提交读/重复读/串行化
死锁：
事物日志：先修改数据的内存拷贝对象，然后将内存数据写到事物日志中（顺序IO追加方式），然后将事物日志中的数据库操作写到硬盘中。
显/隐加锁：隐：根据搜索引擎的隔离级别在需要的时候加锁
			显：select * lock in share mode
				select * for update
服务器级别的加锁： lock table 
					unlock table
INNODB MVCC:
	每个表多了两个隐藏字段，创建时间/删除时间======》版本号
	select 操作： 小于等于当前事务版本号/未定义或者大于当前事务版本号
	insert操作：	保存当前事务版本号/
	delete操作：						/保存当前事务版本号
	update操作：先新增一条记录：保存当前事务版本号/
				将原纪录修改：						/保存当前事务版本号
show table status like '表名' \G;

mysql基准测试
	mysql应用测试/整个系统测试
		吞吐量：单位时间内事物的处理数
		响应时间或者延迟：测试任务所需的整体时间
		并发性：一个网站可能同时有5000用户在线，但是可能只有10-15个并发请求mysql数据库
		可扩展性：增加一个cpu原吞吐量也应该增加一倍
	集成测试框架：
		ab
		http_load
		jmeter
	组件测试：
		mysqlslap
		sql-bench
		database test suite
		percona's tpcc-mysql tool
		(sysbench)
服务器性能评估
	性能：响应时间
	优化：提高CPU利用率，提高响应时间
vmstat
iostat

整数类型：
	tinyint	8位
	smallint	16位
	mediumint	24位
	int			32位
	bigint		64位
实数类型：
	float	4字节
	double	8字节
	decimal	
	万分之一精度：所有数字乘以100万后存储在bigint中留侯计算
字符串类型
	char
	varchar
	blob
	text
枚举类型：使用字符串作为枚举值，如果使用数字作为枚举值，则会出现双重意思的问题
	create table enum_text(e enum('fish','apple','dog') not null));
	insert into enum_test(e) values('fish'),('dog'),('apple');
mysql> select e + 0 from enum_test;
+-------+
| e + 0 |
+-------+
|     1 |
|     2 |
|     3 |
+-------+
创建存储过程
mysql> delimiter $$
mysql> CREATE PROCEDURE batchInsert1() BEGIN declare num int; set num=1; while num<=1000000 do insert into t(`a`) values(num); set num=num+1; end while; end$$
时间类型：timestamp  4字节	和linux时间戳相同	表示：1970-2038年
		 datetime	8字节	
位类型：
	BIT
	SET
mysql> create table enum_set_test(e enum('a','b'), b bit(8), s set('s1', 's2', 's3'));
Query OK, 0 rows affected (0.04 sec)

mysql> select * from enum_set_test;
Empty set (0.00 sec)

mysql> insert into enum_set_test(e, b, s) values('a', b'00000010', 's1');
Query OK, 1 row affected (0.01 sec)

mysql> select * from enum_set_test;
+------+------+------+
| e    | b    | s    |
+------+------+------+
| a    |     | s1   |
+------+------+------+
1 row in set (0.00 sec)

mysql> select b + 0 from enum_set_test;
+-------+
| b + 0 |
+-------+
|     2 |
+-------+
1 row in set (0.00 sec)

范式/反范式----》减少了distinct和groupby的使用/减少了联表查询加快了速度不用引擎重复移动指针找数据，加大了数据维护量

MYSQL物化视图

alter table 表名 modify column 列名 tinyint(3) not null default 5;会重新建表，然后将数据复制过去
alter table 表名 alter column 列名 set default 5;直接操作frm数据文件，不会重新修改表数据

索引优点
	索引减少了服务器扫描的数据量
	避免了临时表和排序
	随机IO转化为顺序IO
	
	简化where后条件，这样可方便的使用索引列，说白了不要有操作符
	select * from 表名 where 列1 = xx1 or 列2 = xx2 会导致全表扫描 ===》select * from 表名 where 列1 = xx1 union all select * from 表 where 列2 = xx2
	索引合并在区分度不高的情况下，会导致占用空间大/cpu/内存占用高等问题，不如使用单列索引union的方式更加快
	范围查找不会使用索引，而多个等值条件查询则无这个限制
	延迟关联：适用场景：排序加分页查询（order by 列名 limit 10000，10）
		select <cols> from <表名> where <col>='M' order by <col> limit 10000,10;
		select <cols> from <表名> inner join (select <primary key col> from <表名> where <col>='M' order by <col> limit 10000, 10) as x using(primary key col);
		也可以between to
	in（子查询） =》exists（子查询）
        explain select * from tb_item where tb_item.id in (select tb_order_item.item_id from tb_order_item);
        explain select * from tb_item where exists (select 1 from tb_order_item where tb_item.id = tb_order_item.item_id);
	update 表1 inner join (子查询) as 表2 using(链接字段) set 表1.列 = 表2.列
	count(*) 忽略行中每列值，直接统计总行数 count(列) 统计不为null的列总行树
	explain select (select count(*) from tb_item) - count(*) from tb_item where tb_item.id > 20;优化器会直接将子查询优化为常数
	group by 列（应为索引列）
查询的步骤：
	缓存-》sql解释器优化器变为执行计划-》调用存储引擎的api执行查询-》返回查询结果
	
	
