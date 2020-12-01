package com.syzs.lianxi.work;


import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WorkCustomer {


    //一个消费者
    @RabbitListener(queuesToDeclare = @Queue("work"))
    protected void receive1(String message){
        System.out.println("message1 = " + message);
    }

    @RabbitListener(queuesToDeclare = @Queue("work"))
    protected void receive2(String message){
        System.out.println("message2 = " + message);
    }
}
