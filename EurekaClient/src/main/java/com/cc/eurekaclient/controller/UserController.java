package com.cc.eurekaclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2022/12/9
 * Time: 9:45
 * Description:
 */
@RestController
public class UserController {

    // ${server.port}：获取当前服务的端口
    @Value("${server.port}")
    Integer port;

    @GetMapping("/hi")
    public Object getData(String name){
        return "我是"+name+"，我的端口是"+port;
    }

}
