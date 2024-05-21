package com.cc.consumer.controller;

import com.cc.consumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2022/12/9
 * Time: 10:44
 * Description:
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/hi")
    public Object getData(String name){
        return userService.getData(name);
    }

}
