### Java规范(参照:阿里巴巴Java开发手册, https://files-cdn.cnblogs.com/files/han-1034683568/%E9%98%BF%E9%87%8C%E5%B7%B4%E5%B7%B4Java%E5%BC%80%E5%8F%91%E6%89%8B%E5%86%8C%E7%BB%88%E6%9E%81%E7%89%88v1.3.0.pdf)
#####1. 异常
    * 异常范围， 明确异常原因/异常的执行速度低于流程
    * finally中的return会提前结束方法执行，不会执行try中的return
    * 防止NPE，调用方责任；利用JDK8中的Optional来防止NPE问题
    * 不允许直接抛出RuntimeException或者Throwable，应该使用业界自定义的异常，如：DaoException/ServiceException等
    * 公司外部api要异常情况使用错误码
    * 公司内部api可以抛出异常，跨应用RPC，则应封装Result类，包含是否成功方法/错误码/错误信息等信息

#####2. 日志规约
    * 必须使用slf4j，门面模式的日志框架，有利于维护各个类的日志处理
        * import org.slf4j.Logger;
        * import org.slf4j.LoggerFactory;
        * privaate static final Logger logger = LoggerFactory.getLogger(A.class)
    * Logger日志顺序
    * 避免重复打印日志， log4j.xml设置additivity=false
    
#####3. 测试
    *  assert
    
#####4. 建表规约
    * 是否概念， is_xxx, 1是0否， 非负数unsigned tinyint
    * 表名/字段名全部小写/非复数，禁止数字开头，下划线中间一个数字的情况
    * 主键索引名为pk_字段名（primary key）， 唯一索引名为uk_字段名（unique key）， 普通索引名为idx_字段名（index）
    * 小数使用decimal，如果不够存放，将整数和小数拆开来存放
    * 如果存储的字符串长度几乎相等，则使用char来存储
    * varchar是可变字符串，不需预先分配空间，长度不要超过5000，否则使用text字段，并拆分出来另一个表来存放，避免影响索引查询
    * 表必备三个字段 id unsigned, gmt_create date_time, gmt_modified date_time
    * 字段允许适当冗余（如：商品中冗余商品类目字段）
        * 不要频繁修改字段
        * 不是varchar超长字段
    * 单表超过500W行或者容量达到2G，推荐分库分表
    * 适当的字段长度提高索引速度
        * tinyint/smallint/int/bigint
        
#####4. 索引规约
    * 多表join，join字段类型必须一致，且需要有索引
    * B-tree最左前缀匹配原则，禁止使用左模糊和全模糊
    * order by 字段是索引/组合索引一部分， where a=? and b=? order by c则索引为a_b_c
    * explain 的 extra 列会出现 using index
    * SELECT a.* FROM 表 1 a, (select id from 表 1 where 条件 LIMIT 100000,20 ) b where a.id=b.id        
    * explain 的 type类型为index，全表扫描，非常慢
        * consts 索引查询，最多一行
        * ref 普通索引
        * range 索引范围搜索
             
#####5. SQL语句
    * 使用count(*)统计行数，包含统计值为null的行，count(列名)则不包含
    * 使用isnull来判断是否为null，null与任何值比较都是null
    * 分页查询如果count为0，则停止分页语句的查询
    * 禁止使用存储过程，存储过程难以调试和扩展，更没有移植性
    * 删除和更新记录，需要先select
    * utfmb4 与 utf-8
    
#####6. ORM映射
    * 不允许*查询
    * 布尔型pojo不可is开头， 数据库必须is_开头，resultMap中对应关系维护好是必须的
    * 不可使用resultClass当作返回参数
    * sql.xml不要使用#{}方式，容易出现sql注入， #parama#
    * 不允许hashMap/hashTable作为查询的返回结果，因为值的字段类型不可控制
    * 不要写一个大而全的更新接口，易出错，效率低， binlog存储大
    * 事物注解会降低qps，使用注意缓存回滚，消息补偿，统计修复等问题
    