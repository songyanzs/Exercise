package com.syzs.testredis.rabbitMq;


import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;


@Component
public class rabbitCustomer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue("TestDirectQueue"),//创建临时队列
                    exchange = @Exchange(value = "TestDirectExchange",type = "direct"),
                    key = {"testDirect","error"}
            )
    })
    public void receive(String messsage){
        System.out.println("接收到的消息------------->"+messsage);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue("TestDirectQueue2"),//创建临时队列
                    exchange = @Exchange(value = "TestDirectExchange",type = "direct"),
                    key = {"testDirect2","error"}
            )
    })
    public void receive2(String messsage){
        System.out.println("接收到的消息------------->"+messsage);
    }
}
