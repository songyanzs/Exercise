package com.syzs.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.syzs.service.impl.OrderServiceImpl2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Slf4j
public class OrderController2 {

    @Autowired
    private OrderServiceImpl2 orderServiceImpl2;

    @RequestMapping("/order/message1")
    public String message1() {
        orderServiceImpl2.message();
        return "message1";
    }

    @RequestMapping("/order/message2")
    public String message2() {
        orderServiceImpl2.message();
        return "message2";
    }

    @RequestMapping("/order/message3")
    @SentinelResource("message3")
    //注意这里必须使用这个注解标识,热点规则不生效
    public String message3(String name, Integer age) {
        return name + age;
    }
}
