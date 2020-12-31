package com.syzs.udptest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * BusinessServiceImpl
 *
 * @author wliduo[i@dolyw.com]
 * @date 2020/5/20 11:59
 */
@Service
public class BusinessServiceImpl implements BusinessService {

    private static final Logger logger = LoggerFactory.getLogger(BusinessServiceImpl.class);

    @Override
    public void udpHandleMethod(String message) throws Exception {
        logger.info("业务开始处理");
        Thread.sleep(3000);
        logger.info("业务处理完成");
    }
}
