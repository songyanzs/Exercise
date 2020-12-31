package com.syzs.udpDemo.Service;

import org.springframework.scheduling.annotation.Async;

public interface BusinessService {

    void udpHandleMethod(String message) throws Exception;
}
