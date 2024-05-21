package com.cc.consumer.service.impl;

import com.cc.consumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2022/12/9
 * Time: 10:45
 * Description:
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    // 服务与服务间的通信方式
    /*
    1、restful 基于http SpringCloud提供的通信对象：RestTemplate、HttpClient
    2、rpc 基于tcp
     */

    // 此处Bean注入需要在启动文件中配置添加RestTemplate的Bean对象到Spring容器
    @Autowired
    RestTemplate restTemplate;
    // DiscoveryClient对象用于获取服务，以动态拼接路径
    @Autowired
    DiscoveryClient discoveryClient;

    Boolean flag = false;
    Integer weight = 4;

    @Override
    public String getData(String name) {
        // 自定义负载均衡(1:1)

//        if(!flag){
//            flag = true;
//            return restTemplate.getForObject("http://localhost:8091/hi?name="+name,String.class);
//        }
//        flag = false;
//        return restTemplate.getForObject("http://localhost:8092/hi?name="+name,String.class);

        // 自定义负载均衡加权(1:3)

//        weight--;
//        if (weight == 0){
//            weight = 4;
//        }
//        if (weight <= 1){
//            return restTemplate.getForObject("http://localhost:8091/hi?name="+name,String.class);
//        }
//        return restTemplate.getForObject("http://localhost:8092/hi?name="+name,String.class);

        // 拉取微服务(生产者)列表

//        List<ServiceInstance> services = discoveryClient.getInstances("EurekaClient");
//        // 随机获取一个微服务(生产者)对象
//        ServiceInstance serviceInstance = services.get(new Random().nextInt(services.size()));
//        // 获取该服务对象的Ip+Port
//        String ipAndPort = serviceInstance.getHost() +":"+ serviceInstance.getPort();
//        // 动态拼接路径
//        return restTemplate.getForObject("http://"+ipAndPort+"/hi?name="+name,String.class);

        // 使用SpringCloud封装的负载均衡器Ribbon
        // 需要在启动类中RestTemplate的Bean对象注入同时使用注解@LoadBalanced
        // 开启负载均衡器服务ID识别，此时restTemplate对象就可以识别服务ID: EurekaClient
        // getForObject(url,返回值类型的class对象)
        return restTemplate.getForObject("http://EurekaClient/hi?name="+name,String.class);

    }

}
