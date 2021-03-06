### RocketMQ特性实践

##### 特性
* 消峰（解决瞬时写压力大于应用服务能力导致消息丢失、系统崩溃）
* 解耦（解决系统间不必要的依赖）
* 提升性能（当存在一对多调用时，通过发送消息至MQ服务中，让消息系统通知相关业务）
* 蓄流压测（线上有些链路不好压测，可以通过堆积一定量的消息再放开来压测）

##### 优点
1. 事务消息（rabbitmq 和 kafka 不支持）
2. 数据最终一致性
3. 18个级别的延迟消息（rabbitmq 和 kafka 不支持）
4. 支持指定次数和时间间隔的失败消息重发
5. 支持消费端通过主题过滤，减少不必要的网路传输（rabbitmq 和 kafka 不支持）
6. 支持重复消费

##### 组件
1. Name Server
    1. 几乎无状态节点，可集群部署，节点之间无信息同步
2. Broker
    1. Master（BrokerId = 0）
    2. Slave（BrokerId != 0）；通过指定相同Broker Name来判断是否对应
3. Name Server 与 Broker 关系
    1. 每个Broker 与 Name Server 集群的所有节点 创建长连接
    2. 每个Broker 定时（每隔30S） 注册Topic信息到Name Server
    3. Name Server（每隔10S）扫描所有存活Broker的连接，如果超过两分钟无响应，则断开连接
4. Producer
    1. 与Name Server集群中的一个节点 建立 长连接，定期从Name Server获取Topic路由信息，并向Topic的Master Broker节点建立长连接且定时向Master发送心跳
    2. Producer每隔30s（由ClientConfig的pollNameServerInterval）从Name server获取所有topic队列的最新情况，这意味着如果Broker不可用，Producer最多30s能够感知，在此期间内发往Broker的所有消息都会失败。
    3. Producer每隔30s（由ClientConfig中heartbeatBrokerInterval决定）向所有关联的broker发送心跳，Broker每隔10s中扫描所有存活的连接，如果Broker在2分钟内没有收到心跳数据，则关闭与Producer的连接。
5. Consumer
    1. Consumer与Name Server集群中的其中一个节点(随机选择)建立长连接，定期从Name Server取Topic路由信息，并向提供Topic服务的Master、Slave建立长连接，且定时向Master、Slave发送心跳。
        Consumer既可以从Master订阅消息，也可以从Slave订阅消息，订阅规则由Broker配置决定。
    2. Consumer每隔30s从Name server获取topic的最新队列情况，这意味着Broker不可用时，Consumer最多最需要30s才能感知。
    3. Consumer每隔30s（由ClientConfig中heartbeatBrokerInterval决定）向所有关联的broker发送心跳，Broker每隔10s扫描所有存活的连接，若某个连接2分钟内没有发送心跳数据，则关闭连接；
        并向该Consumer Group的所有Consumer发出通知，Group内的Consumer重新分配队列，然后继续消费。
    4. 当Consumer得到master宕机通知后，转向slave消费，slave不能保证master的消息100%都同步过来了，因此会有少量的消息丢失。
        但是一旦master恢复，未同步过去的消息会被最终消费掉。
    5. 消费者对列是消费者连接之后（或者之前有连接过）才创建的。
        我们将原生的消费者标识由 {IP}@{消费者group}扩展为 {IP}@{消费者group}{topic}{tag}，
        （例如xxx.xxx.xxx.xxx@mqtest_producer-group_2m2sTest_tag-zyk）。
        任何一个元素不同，都认为是不同的消费端，每个消费端会拥有一份自己消费对列（默认是broker对列数量*broker数量）。
        新挂载的消费者对列中拥有commitlog中的所有数据。

##### 示例
1. 生产消息步骤
````
1. 创建生成者组；Instantiate with a producer group name.
2. 指定NameServer地址；Specify name server addresses.
3. 启动NameServer；Launch the instance.
4. 创建消息实例；Create a message instance, specifying topic, tag and message body.
5. 发送消息；Call send message to deliver message to one of brokers.
6. 关闭生产者组；Shut down once the producer instance is not longer in use.
````
2. 消费消息步骤
````
1. 创建生产者组；Instantiate with specified consumer group name.
2. 指定NameServer地址；Specify name server addresses.
3. 订阅消息主题；Subscribe one more more topics to consume.
4. 注册回调消息；Register callback to execute on arrival of messages fetched from brokers.
5. 启动生产者实例；Launch the consumer instance.
````

3. [全局消息], 顺序不保证，因为消息被发送各个队列当中，每个消息被消费的顺序由队列来保证，但是多个队列则消费顺序无法保证

4. [顺序消息], 将消息发送给指定队列中去， 按队列特性顺序消费每个消息
````
SendResult sendResult = producer.send(msg, new MessageQueueSelector(){
        @Override
        // 第一个参数：消息队列列表，如果集群，则是单机的队列数量 × 集群数量
        // 第二个参数：消息
        // 第三个参数：指定队列索引 ==> send 中的第三个参数
        public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
            Integer index = (Integer) o;
            System.out.printf("当前消息队列数量：【%d】", list.size());
            return list.get(index);
        }
    }, NumberUtils.INTEGER_ZERO);
````

5. [事物消息]，两阶段提交，最终数据一致性；![具体原理](https://img-blog.csdnimg.cn/20190510231845733.png)

6. [广播消息]|[批量消息]，默认消费模式是集群消费模式，改为广播消费模式，支持消息被每个消费者消费

##### 集群方式
1. 双主双从，同步复制，异步刷盘
2. mkdir -p /host/cluster/rocketmq/logs/nameserver-a
   mkdir -p /host/cluster/rocketmq/logs/nameserver-b
   mkdir -p /host/cluster/rocketmq/store/nameserver-a
   mkdir -p /host/cluster/rocketmq/store/nameserver-b
   mkdir -p /host/cluster/rocketmq/logs/broker-a
   mkdir -p /host/cluster/rocketmq/logs/broker-b
   mkdir -p /host/cluster/rocketmq/store/broker-a
   mkdir -p /host/cluster/rocketmq/store/broker-b
   mkdir -p /host/cluster/rocketmq/broker-a/
   mkdir -p /host/cluster/rocketmq/broker-b/
3. 创建broker.conf
   broker.conf是Broker的配置文件，因为此时RocketMQ镜像还没有拉取,所以还没有默认的broker.conf。所以这里直接写好，到时候通过命令替换默认的broker.conf。
   因为是双主模式部署，所以会有两个broker.conf,这里暂且命名 broker-a.conf 和 broker-b.conf
   1、当前Broker对外暴露的端口号
   2、注册到NameServer的地址，看到这里有两个地址，说明NameServer也是集群部署。
   3、当前Broker的角色，是主还是从，这里表示是主。
        1. broker-a.conf
        ````
        brokerClusterName = rocketmq-cluster
        brokerName = broker-a
        brokerId = 0
        #这个很有讲究 如果是正式环境 这里一定要填写内网地址（安全）
        #如果是用于测试或者本地这里建议要填外网地址，因为你的本地代码是无法连接到阿里云内网，只能连接外网。
        brokerIP1 = 172.16.0.4
        deleteWhen = 04
        fileReservedTime = 48
        brokerRole = ASYNC_MASTER
        flushDiskType = ASYNC_FLUSH
        # 内网的(阿里云有内网IP和外网IP)
        namesrvAddr=172.16.0.4:9876;172.16.0.4:9877
        autoCreateTopicEnable=true
        #Broker 对外服务的监听端口,
        listenPort = 10911
        #Broker角色
        #- ASYNC_MASTER 异步复制Master
        #- SYNC_MASTER 同步双写Master
        #- SLAVE
        brokerRole=ASYNC_MASTER
        #刷盘方式
        #- ASYNC_FLUSH 异步刷盘
        #- SYNC_FLUSH 同步刷盘
        flushDiskType=ASYNC_FLUSH
        ````
        2. broker-b.conf
        ````
        brokerClusterName = rocketmq-cluster
        brokerName = broker-b
        brokerId = 0
        brokerIP1 = 172.16.0.4
        deleteWhen = 04
        fileReservedTime = 48
        brokerRole = ASYNC_MASTER
        flushDiskType = ASYNC_FLUSH
        # 内网的(阿里云有内网IP和外网IP)
        namesrvAddr=172.16.0.4:9876;172.16.0.4:9877
        autoCreateTopicEnable=true
        #Broker 对外服务的监听端口,
        listenPort = 10909
        #Broker角色
        #- ASYNC_MASTER 异步复制Master
        #- SYNC_MASTER 同步双写Master
        #- SLAVE
        brokerRole=ASYNC_MASTER
        #刷盘方式
        #- ASYNC_FLUSH 异步刷盘
        #- SYNC_FLUSH 同步刷盘
        flushDiskType=ASYNC_FLUSH
        ````
3. 编写 docker-compose.yml
        ````
        version: '3.5'
            services:
              rmqnamesrv-a:
                image: rocketmqinc/rocketmq:4.4.0
                container_name: rmqnamesrv-a
                ports:
                  - 9876:9876
                volumes:
                  - /host/cluster/rocketmq/logs/nameserver-a:/opt/logs
                  - /host/cluster/rocketmq/store/nameserver-a:/opt/store
                command: sh mqnamesrv
                networks:
                    rmq:
                      aliases:
                        - rmqnamesrv-a
            
              rmqnamesrv-b:
                image: rocketmqinc/rocketmq:4.4.0
                container_name: rmqnamesrv-b
                ports:
                  - 9877:9877
                volumes:
                  - /host/cluster/rocketmq/logs/nameserver-b:/opt/logs
                  - /host/cluster/rocketmq/store/nameserver-b:/opt/store
                command: sh mqnamesrv
                networks:
                    rmq:
                      aliases:
                        - rmqnamesrv-b
            
              rmqbroker-a:
                image: rocketmqinc/rocketmq:4.4.0
                container_name: rmqbroker-a
                ports:
                  - 10911:10911
                volumes:
                  - /host/cluster/rocketmq/logs/broker-a:/opt/logs
                  - /host/cluster/rocketmq/store/broker-a:/opt/store
                  - /host/cluster/rocketmq/broker-a/broker-a.conf:/opt/rocketmq-4.4.0/conf/broker.conf 
                environment:
                    TZ: Asia/Shanghai
                    NAMESRV_ADDR: "rmqnamesrv-a:9876"
                    JAVA_OPTS: " -Duser.home=/opt"
                    JAVA_OPT_EXT: "-server -Xms256m -Xmx256m -Xmn256m"
                command: sh mqbroker -c /opt/rocketmq-4.4.0/conf/broker.conf autoCreateTopicEnable=true &
                links:
                  - rmqnamesrv-a:rmqnamesrv-a
                  - rmqnamesrv-b:rmqnamesrv-b
                networks:
                  rmq:
                    aliases:
                      - rmqbroker-a
            
              rmqbroker-b:
                image: rocketmqinc/rocketmq:4.4.0
                container_name: rmqbroker-b
                ports:
                  - 10909:10909
                volumes:
                  - /host/cluster/rocketmq/logs/broker-b:/opt/logs
                  - /host/cluster/rocketmq/store/broker-b:/opt/store
                  - /host/cluster/rocketmq/broker-b/broker-b.conf:/opt/rocketmq-4.4.0/conf/broker.conf 
                environment:
                    TZ: Asia/Shanghai
                    NAMESRV_ADDR: "rmqnamesrv-b:9876"
                    JAVA_OPTS: " -Duser.home=/opt"
                    JAVA_OPT_EXT: "-server -Xms256m -Xmx256m -Xmn256m"
                command: sh mqbroker -c /opt/rocketmq-4.4.0/conf/broker.conf autoCreateTopicEnable=true &
                links:
                  - rmqnamesrv-a:rmqnamesrv-a
                  - rmqnamesrv-b:rmqnamesrv-b
                networks:
                  rmq:
                    aliases:
                      - rmqbroker-b
              rmqconsole:
                image: styletang/rocketmq-console-ng
                container_name: rmqconsole
                ports:
                  - 9001:9001
                environment:
                    JAVA_OPTS: -Drocketmq.namesrv.addr=rmqnamesrv-a:9876;rmqnamesrv-b:9877 -Dcom.rocketmq.sendMessageWithVIPChannel=false
                networks:
                  rmq:
                    aliases:
                      - rmqconsole
            networks:
              test-net:
                name: test-net
                driver: bridge
        ````