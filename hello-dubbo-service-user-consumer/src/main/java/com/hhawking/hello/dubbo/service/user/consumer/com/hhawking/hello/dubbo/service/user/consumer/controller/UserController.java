package com.hhawking.hello.dubbo.service.user.consumer.com.hhawking.hello.dubbo.service.user.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hhawking.hello.dubbo.service.user.api.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Reference(version = "${user.service.version}")
    private UserService userService;

    @GetMapping(value = "hi")
    @HystrixCommand(fallbackMethod = "testFallbackMethod")
    public String test(){
        return userService.sayHi();
    }

    private String testFallbackMethod(){
        return "Test FallbackMethod !";
    }
}
