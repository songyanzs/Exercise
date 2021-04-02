package com.syzs.udptest.config;


import com.syzs.udptest.service.BusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.ip.dsl.Udp;
import org.springframework.integration.ip.udp.UnicastReceivingChannelAdapter;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Map;

/**
 * UDP消息接收服务
 * @author syzs
 * @date 2020/12/31
 */
@Configuration
public class UdpServer {

    private static final Logger logger = LoggerFactory.getLogger(UdpServer.class);

    @Value("${udp.port}")
    private Integer udpPort;

    @Autowired
    private BusinessService businessService;

    /**
     * UDP消息接收服务写法一
     * https://docs.spring.io/spring-integration/reference/html/ip.html#inbound-udp-adapters-java-configuration
     */
   /* @Bean
    public UnicastReceivingChannelAdapter unicastReceivingChannelAdapter() throws SocketException {
        // 实例化一个UDP消息接收服务
        UnicastReceivingChannelAdapter unicastReceivingChannelAdapter = new UnicastReceivingChannelAdapter(udpPort);
        // unicastReceivingChannelAdapter.setOutputChannel(new DirectChannel());
        unicastReceivingChannelAdapter.setOutputChannelName("udpChannel");
        logger.info("UDP服务启动成功，端口号为: {}", udpPort);
        return unicastReceivingChannelAdapter;
    }*/

    /**
     * UDP消息接收服务写法二
     * https://docs.spring.io/spring-integration/reference/html/ip.html#inbound-udp-adapters-java-dsl-configuration
     */
    @Bean
    public IntegrationFlow integrationFlow() {
        logger.info("UDP服务启动成功，端口号为: {}", udpPort);
        return IntegrationFlows.from(Udp.inboundAdapter(udpPort)).channel("udpChannel").get();
    }

    /**
     * 转换器
     *
     */
    @Transformer(inputChannel = "udpChannel", outputChannel = "udpFilter")
    public String transformer(@Payload byte[] payload, @Headers Map<String, Object> headers) {
        String message = new String(payload);
        // 转换为大写
//        message = message.toUpperCase();
        return message;
    }

    /**
     * 过滤器
     * 对udp传输过来的信息进行过滤操作
     */
    @Filter(inputChannel = "udpFilter", outputChannel = "udpRouter")
    public boolean filter(String message, @Headers Map<String, Object> headers) {
        // 获取来源Id
        String id = headers.get("id").toString();
        // 获取来源IP，可以进行IP过滤
        String ip = headers.get("ip_address").toString();
        // 获取来源Port
        String port = headers.get("ip_port").toString();
        // 信息数据过滤
        if (message.equals("sss")) {
            // 没有-的数据会被过滤
            logger.info("传入数据为sss,不允许通过");
            return false;
        }
        return true;
    }

    /**
     * 路由分发处理器
     * 如果有多个处理器，根据条件进行帅选走哪些处理器
     */
    @Router(inputChannel = "udpRouter")
    public String router(String message, @Headers Map<String, Object> headers) {
        // 获取来源Id
        String id = headers.get("id").toString();
        // 获取来源IP，可以进行IP过滤
        String ip = headers.get("ip_address").toString();
        // 获取来源Port
        String port = headers.get("ip_port").toString();
        // 筛选，走那个处理器
        if (false) {
            return "udpHandle2";
        }
        return "udpHandle1";
    }

    /**
     * 最终处理器1
     *
     * @param message
     * @return void
     */
    @ServiceActivator(inputChannel = "udpHandle1")
    public void udpMessageHandle(String message) throws Exception {
        // 可以进行异步处理
        businessService.udpHandleMethod(message);
        logger.info("UDP1:" + message);
    }

    /**
     * 最终处理器2
     *
     * @param message
     * @return void
     */
    @ServiceActivator(inputChannel = "udpHandle2")
    public void udpMessageHandle2(String message) throws Exception {
        logger.info("UDP2:" + message);
    }

}

