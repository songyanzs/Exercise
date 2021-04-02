package com.syzs.udptest.controller;


import com.syzs.udptest.domain.User;
import com.syzs.udptest.service.UdpSimpleClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class TestController {

    @Autowired
    private UdpSimpleClient udpSimpleClient;

    @RequestMapping("/test")
    public void sendMessage(@RequestParam String message){
        udpSimpleClient.sendMessage(message);
    }

    @RequestMapping("/test2")
    public void sendMessage2(@RequestBody User user){
        udpSimpleClient.sendMessage(user.toString());
    }


    public static void main(String[] args) {
        String path = "/export/product/20210107/20200107.png";
        System.out.println(path);
        String s = "/export/product/";
//        int index = path.indexOf(s);
//        String url = path.substring(index+s.length());
        path = path.replace(s,"");
        String url ="/"+  path;
        System.out.println(url);
    }
}
