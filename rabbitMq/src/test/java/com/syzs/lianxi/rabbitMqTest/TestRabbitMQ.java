package com.syzs.lianxi.rabbitMqTest;


import com.syzs.lianxi.LianxiApplication;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest(classes = LianxiApplication.class)
@RunWith(SpringRunner.class)
public class TestRabbitMQ {
    //注入rabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //ROUTE 路由模式
    @Test
    public void testRoute(){
        /**
         * 参数
         * 1，交换机名称
         * 2，routingkey
         * 3,消息内容
         */
        rabbitTemplate.convertAndSend("directs","info","发送info路由key的信息");
        rabbitTemplate.convertAndSend("directs","error","发送error路由key的信息");
    }


    //fanout
    @Test
    public void testFanout(){
        rabbitTemplate.convertAndSend("logs","","fanout模型发送的消息");
    }

    //work
    @Test
    public void  testWork(){
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work","work模型"+i);
        }
    }
    //hello world 模型
    @Test
    public void  test(){
        rabbitTemplate.convertAndSend("hello","hello world");
    }
}
