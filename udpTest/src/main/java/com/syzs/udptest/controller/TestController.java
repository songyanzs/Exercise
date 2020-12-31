package com.syzs.udptest.controller;


import com.syzs.udptest.service.UdpSimpleClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private UdpSimpleClient udpSimpleClient;

    @RequestMapping("/test")
    public void senfMessage(@RequestParam String message){
        udpSimpleClient.sendMessage(message);
    }
}
