### Java规范(参照:阿里巴巴Java开发手册, https://files-cdn.cnblogs.com/files/han-1034683568/%E9%98%BF%E9%87%8C%E5%B7%B4%E5%B7%B4Java%E5%BC%80%E5%8F%91%E6%89%8B%E5%86%8C%E7%BB%88%E6%9E%81%E7%89%88v1.3.0.pdf)
#####1. 应用分层
    * DO/DTO/VO/AO/BO/QUERY
    * 第二方库依赖 GroupId： com.{公司名}.{业务线}.{子业务线}，最多4层
        * com.taobao.jstorm
        * com.alibabab.dubbo.register
    * ArtifactId：产品线名-模块名
        * dubbo-client
        * fastjson-api
    * Version：主版本号.次版本号.修订号
        * 主版本号：产品方向改变，或者大规模 API 不兼容，或者架构不兼容升级
        * 次版本号：保持相对兼容性，增加主要功能特性，影响范围极小的 API 不兼容修改
        * 修订号：保持完全兼容性，修复 BUG、新增次要功能特性等
        * 1.3.3 -> 1.3.4/1.4.0/2.0.0
        
 #####2. 服务器
    * 高并发服务器，调小TCP协议的time_wait超时时间
        * /etc/sysctl.conf, net.ipv4.tcp_fin_timeout = 30
    * 最大文件句柄数
    * 给 JVM 设置-XX:+HeapDumpOnOutOfMemoryError 参数
    * 在线上生产环境，JVM 的 Xms 和 Xmx 设置一样大小的内存容量，避免在 GC 后调整堆大小带来的压力。
    * 服务器内部重定向使用 forward；外部重定向地址使用 URL 拼装工具类来生成，否则会带来 URL 维护不一致的问题和潜在的安全风险。