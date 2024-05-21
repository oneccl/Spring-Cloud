/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2022/12/8
 * Time: 19:54
 * Description:
 */
public class DemoSpringCloud {
    // Spring Cloud 框架
    // 用于微服务管理(Spring分布式微服务/Spring Boot单体项目管理框架)

    // 微服务 和 Spring Boot 和 Spring Cloud 区别与联系
    /*
    1、区别
     1）微服务：是一种设计/架构理念，提出了设计原则，从理论为具体的技术落地提供了指导思想
     2）Spring Boot：基于对框架组件的管理，是专注于快速方便的开发单体项目(微服务)的框架
     3）Spring Cloud：基于Cloud组价，是专注于全局微服务的协调与治理的框架
    2、联系
     1）Spring Cloud将Spring Boot开发的单体微服务整合并管理，为各个微服务间提供服务发现
       负载均衡器、路由网关代理、配置管理、事件总线、全局锁、精选决策、分布式回话等集成服务
       Spring Cloud是服务Spring Boot开发的单体微服务的
     2）Spring Boot可以离开Spring Cloud单独开发，而Spring Cloud依赖于Spring Boot
     */

    // 微服务项目架构的演变历程
    // 单体项目架构->垂直应用架构->分布式架构->SOP架构->微服务架构
    /*
    1、单体项目架构：如SpringBoot-sms工程
    1）优点：
    项目架构简单，小型项目开发成本低；项目部署在一个节点上(一只猫Tomcat即可)，维护方便
    2）缺点：
    功能集中，需求复杂后不易开发和维护；项目模块之间紧密耦合，单点容错率低；无法对某个模块进行水平扩展

    2、垂直应用架构：部署多台服务(垂直)；拆分子项目，项目集群(水平扩展)，提高并发
    1）优点：
    项目拆分做了分流，解决了并发问题，同时可以对模块进行针对性优化；一个子项目出问题不会影响到其他子项目，提高了容错率
    2）缺点：
    子项目间相互独立，无法进行相互调用，且存在重复冗余问题

    3、分布式架构：拆分子项目，垂直架构拆分维度依据是：业务功能的独立性；分布式的拆分维度依据是：业务重用性
    把项目拆分成表现层和服务层两个部分，服务层中包含业务逻辑；表现层只需要处理和页面的交互，业务逻辑都是调用服务层来实现
    1）优点：
    抽取公共的功能为服务层，提高代码复用性；可以针对不同服务进行垂直与水平扩展；建立自动扩缩容机制，提高系统可用性
    2）缺点：
    系统间耦合度变高，调用关系错综复杂，难以维护

    4、SOA架构：SOA架构引入资源(服务)调度/注册中心，实现所有服务(ip+port)的统一管理
    1）优点：
    使用注册中心解决了服务间调用关系的自动调节
    2）缺点：
    服务间会有依赖关系，一旦某个服务出错会影响较大(服务雪崩)；服务关系复杂，运维、测试部署困难

    5、微服务架构：服务架构是SOA架构的升级版，对服务做了更细致的拆分
    1）优点：
    服务原子化拆分，独立打包、部署和升级，保证每个微服务有清晰的任务划分，利于扩展；
    微服务之间采用RESTful(一种网络应用程序的设计风格和开发方式)等轻量级Http协议相互调用
    2）缺点：
    分布式系统开发的技术成本高(容错、分布式事务等)
     */

    // SpringCloud微服务架构的5大组件(SpringCloud全家桶)
    /*
    1、注册中心：Netflix Eureka
    （1）Spring Cloud提供了多种注册中心的支持，如Eureka、ZooKeeper等；Netflix Eureka本身
    是一个基于REST的服务，包含两个组件：Eureka Server和Eureka Client(生产者或消费者)
    （2）Eureka的基本架构，由3部分组成：
       1)Service Provider：暴露服务的服务提供方
       2)Service Consumer：调用远程服务的服务消费方
       3)Eureka Server：服务注册中心和服务发现中心
    （3）作用：提供服务注册与发现，各个节点启动后，会在Eureka Server中进行注册
    （4）Eureka Server的心跳机制：在应用启动后，Eureka Client将会向Eureka Server
    发送心跳默认周期为30秒，如果Eureka Server在多个心跳周期内(默认90秒)没有接收到
    某个节点的心跳，Eureka Server将会进入自我保护机制

    2、负载均衡：Netflix Ribbon
    （1）是一个基于HTTP和TCP的客户端负载均衡工具，它基于Netflix Ribbon实现
    通过Spring Cloud的封装，可以让我们轻松地将面向服务的REST模版请求自动转换成客户端负载均衡的服务调用
    （2）负载均衡策略
       1)RoundRobinRule: 轮询(默认)
       2)RandomRule: 随机
       3)RetryRule: 重试机制
       4)BestAvailableRule: 选择最小并发Server
       5)AvailabilityFilteringRule: 过滤高并发、失败Server
       6)WeightedResponseTimeRule: 根据响应时长比重选择中间值
       7)ZoneAvoidanceRule: 判断Server性能
    （3）作用：提供客户端的软件负载均衡算法，将Netflix的中间层服务连接在一起；Ribbon客户端组件提供一系列完善的配置项如连接超时，重试等

    3、熔断器：Netflix Hystrix
    （1）熔断器
       1)雪崩效应：多个微服务间若有一个出现故障，就会因为依赖关系而引发整个系统奔溃；
       2)服务熔断：当服务A调用服务B时，如果一定时间和次数没有收到B的响应，则A中断请求B
         此时A监控B，当B恢复时，A再请求B；如果一定时间后B没有恢复，则A直接返回服务繁忙
       3)服务降级：当服务A调用服务B发生熔断时，则A会使用一个默认值返回给用户，避免返回错误页面
       4)请求缓存：对接口返回数据进行缓存，可以大大降低生产者的压力，适用于更新频率低访问频繁的数据
       5)请求合并：将客户端的多个请求合并成一个http请求，该请求得到响应后再将结果
         分发给不同请求，提高了数据传输效率
    （2）作用：Netflix Hystrix实现了熔断器、线程隔离等一系列保护功能，用于隔离访问远程服务
    防止级联失败，从而提高了系统的可用性与容错性

    4、网关服务：Netflix Zuul
    （1）浏览器直接访问服务集群(Open Service)破坏了服务集群中Rest API无状态特点，为了保证对外服务
    的安全性，需要对服务访问进行权限控制/校验，因此不得不增加一个代理来做权限处理
    （2）网关服务将权限控制、日志收集从服务中抽取出来，并将负载均衡器移入，其核心是一系列过滤器：
       1)身份认证与安全：资源请求验证
       2)审查与监控：在边缘位置追踪有意义的数据和统计结果，带来精确的生产视图
       3)动态路由：动态的将请求路由到后台服务集群中不同的微服务
       4)压力测试：逐渐增加指向集群的流量，以了解微服务的性能
       5)负载分配：为每一种负载分配对应容量(限流)，并弃用超出限定值的请求
       6)静态响应处理：在边缘位置直接建立部分响应，避免其转发到集群内部
    （3）作用：统一向外提供Rest API、服务路由、负载均衡、权限控制等功能，为微服务架构提供安全保障
    提高了微服务集群的可用性和测试性

    5、配置中心：Spring Cloud Config
    （1）每个微服务都有自己的配置文件，如果微服务数量庞大，维护成本增加，维护困难
    （2）配置中心为分布式系统外部化配置提供了服务端(Config Server)和客户端(Config Client)支持
       1)Config Server是一个可横向扩展、集中式的配置服务器，用于集中管理应用程序各个环境
        下的配置，支持Git，本地存储，默认是Git存储
       2)Config Client是Config Server的客户端，用于操作存储在Config Server中的配置内容
        微服务启动时，会请求Config Server获取并加载配置文件的内容，再启动容器
     */


}
