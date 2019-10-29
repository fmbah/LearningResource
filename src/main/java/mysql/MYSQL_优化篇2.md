字符集：

可以设置 服务器默认字符集

数据库默认字符集

表默认字符集

甚至到列默认字符集

某个级别没有指定，那么继承上级。

mysql4.1及其之后的版本，对字符集的支持分为四个层次:

服务器(server)，数据库(database)，数据表(table)和连接(connection)：
character_set_server：这是设置服务器使用的字符集
character_set_client ：这是设置客户端发送查询使用的字符集
character_set_connection ：这是设置服务器需要将收到的查询串转换成的字符集
character_set_results ：这是设置服务器要将结果数据转换到的字符集，转换后才发送给客户端
整个过程：
- client(如php程序)发送一个查询；
- 服务器收到查询，将查询串从character_set_client 转换到character_set_connection，然后执行转换后的查询；
- 服务器将结果数据转换到character_set_results字符集后发送回客户端。

1 、告诉服务器发送的数据是什么类型的。

character_set_client

2、告诉转换器，转换什么编码

character_set_connection

3、查询结果用什么编码


 

character_set_results

如果三者都是字符集N

 那么 可以简写为 set names N;

 

例如：

客户端字符集和连接字符集

 

 

 mysql <wbr>字符集（CHARACTER <wbr>SET）和校对集（COLLATE）


mysql <wbr>字符集（CHARACTER <wbr>SET）和校对集（COLLATE）


如果connection和 服务器的字符集，比client小时，容易丢失数据。

mysql <wbr>字符集（CHARACTER <wbr>SET）和校对集（COLLATE）


 

create table 时的 charset 是 服务器的字符编码



校对集：

可以理解为，排序规则等。一个字符集可能有多种校对集合；



mysql <wbr>字符集（CHARACTER <wbr>SET）和校对集（COLLATE）


mysql <wbr>字符集（CHARACTER <wbr>SET）和校对集（COLLATE）

------ 官方文档---------

1、数据库字符集和校对

CREATE DATABASE db_name

    [[DEFAULT] CHARACTER SET charset_name]

    [[DEFAULT] COLLATE collation_name]

例如：

CREATE DATABASE db_name
    DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
MySQL这样选择数据库字符集和数据库校对规则：

·         如果指定了CHARACTER SET X和COLLATE Y，那么采用字符集X和校对规则Y。

·         如果指定了CHARACTER SET X而没有指定COLLATE Y，那么采用CHARACTER SET X和CHARACTER SET X的默认校对规则。

·         否则，采用服务器字符集和服务器校对规则。

2、表字符集和校对
每一个表有一个表字符集和一个校对规则，它不能为空。为指定表字符集和校对规则，CREATE TABLE 和ALTER TABLE语句有一个可选的子句：

CREATE TABLE tbl_name (column_list)

    [DEFAULT CHARACTER SET charset_name [COLLATE collation_name]]

 

ALTER TABLE tbl_name

    [DEFAULT CHARACTER SET charset_name] [COLLATE collation_name]

MySQL按照下面的方式选择表字符集和 校对规则：

·         如果指定了CHARACTER SET X和COLLATE Y，那么采用CHARACTER SET X和COLLATE Y。

·         如果指定了CHARACTER SET X而没有指定COLLATE Y，那么采用CHARACTER SET X和CHARACTER SET X的默认校对规则。

·         否则，采用服务器字符集和服务器校对规则。

如果在列定义中没有指定列字符集和校对规则，则默认使用表字符集和校对规则。表字符集和校对规则是MySQL的扩展;在标准SQL中没有。

3、列字符集和校对
每一个“字符”列（即，CHAR、VARCHAR或TEXT类型的列）有一个列字符集和一个列 校对规则，它不能为空。列定义语法有一个可选子句来指定列字符集和校对规则：

col_name {CHAR | VARCHAR | TEXT} (col_length)

    [CHARACTER SET charset_name [COLLATE collation_name]]

例如：

CREATE TABLE Table1

(

    column1 VARCHAR(5) CHARACTER SET latin1 COLLATE latin1_german1_ci

);

MySQL按照下面的方式选择列字符集和校对规则：

·         如果指定了CHARACTER SET X和COLLATE Y，那么采用CHARACTER SET X和COLLATE Y。

·         如果指定了CHARACTER SET X而没有指定COLLATE Y，那么采用CHARACTER SET X和CHARACTER SET X的默认校对规则。

·         否则，采用表字符集和服务器校对规则。

CHARACTER SET和COLLATE子句是标准的SQL。

示例1：表和列定义

CREATE TABLE t1
(
    c1 CHAR(10) CHARACTER SET latin1 COLLATE latin1_german1_ci
) DEFAULT CHARACTER SET latin2 COLLATE latin2_bin;
在这里我们有一个列使用latin1字符集和latin1_german1_ci校对规则。是显式的定义，因此简单明了。需要注意的是，在一个latin2表中存储一个latin1列不会存在问题。

示例2：表和列定义

CREATE TABLE t1
(
    c1 CHAR(10) CHARACTER SET latin1
) DEFAULT CHARACTER SET latin1 COLLATE latin1_danish_ci;
这次我们有一个列使用latin1字符集和一个默认校对规则。尽管它显得自然，默认校对规则却不是表级。相反，因为latin1的默认校对规则总是latin1_swedish_ci，列c1有一个校对规则latin1_swedish_ci（而不是latin1_danish_ci）。

示例3：表和列定义

CREATE TABLE t1
(
    c1 CHAR(10)
) DEFAULT CHARACTER SET latin1 COLLATE latin1_danish_ci;
我们有一个列使用一个默认字符集和一个默认校对规则。在这种情况下，MySQL查找表级别来确定列字符集和 校对规则。因此，列c1的字符集是latin1，它的 校对规则是latin1_danish_ci。

示例4：数据库、表和列定义

CREATE DATABASE d1
    DEFAULT CHARACTER SET latin2 COLLATE latin2_czech_ci;
USE d1;
CREATE TABLE t1
(
    c1 CHAR(10)
);
我们创建了一个没有指定字符集和校对规则的列。我们也没有指定表级字符集和校对规则。在这种情况下，MySQL查找数据库级的相关设置。（数据库的设置变为表的设置，其后变为列的设置。）因此，列c1的字符集为是latin2，它的 校对规则是latin2_czech_ci。

在SQL语句中使用COLLATE
使用COLLATE子句，能够为一个比较覆盖任何默认校对规则。COLLATE可以用于多种SQL语句中。下面是一些例子：
·         使用ORDER BY：

·                SELECT k

·                FROM t1

·                ORDER BY k COLLATE latin1_german2_ci;

·         使用AS：

·                SELECT k COLLATE latin1_german2_ci AS k1

·                FROM t1

·                ORDER BY k1;

·         使用GROUP BY：

·                SELECT k

·                FROM t1

·                GROUP BY k COLLATE latin1_german2_ci;

·         使用聚合函数：

·                SELECT MAX(k COLLATE latin1_german2_ci)

·                FROM t1;

·         使用DISTINCT：

·                SELECT DISTINCT k COLLATE latin1_german2_ci

·                FROM t1;

·         使用WHERE：

·                     SELECT *

·                     FROM t1

·                     WHERE _latin1 'Müller' COLLATE latin1_german2_ci = k;

·                     SELECT *

·                     FROM t1

·                     WHERE k LIKE _latin1 'Müller' COLLATE latin1_german2_ci;

·         使用HAVING：

·                SELECT k

·                FROM t1

·                GROUP BY k

·                HAVING k = _latin1 'Müller' COLLATE latin1_german2_ci;

 COLLATE子句优先

COLLATE子句有较高的优先级（高于||），因此下面两个表达式是等价的：

x || y COLLATE z

x || (y COLLATE z)