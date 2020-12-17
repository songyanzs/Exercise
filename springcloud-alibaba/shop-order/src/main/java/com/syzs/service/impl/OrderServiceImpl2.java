package com.syzs.service.impl;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl2 {

    //定义资源，value指定资源名称
    @SentinelResource("message")
    public String message(){
        return "message";
    }
}
