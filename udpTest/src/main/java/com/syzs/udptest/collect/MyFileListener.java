package com.syzs.udptest.collect;

import org.apache.storm.shade.org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/**
 * 自定义文件监听器
 * @author sy
 *
 */

//@Component
public class MyFileListener extends FileAlterationListenerAdaptor {

    /*@Autowired
    private RabbitTemplate rabbitTemplate;*/


    @Override
    public void onFileCreate(File file) {
        System.out.println("新建: " + file.getAbsolutePath());
//        rabbitTemplate.convertAndSend("DirectExchange","UntructuredData",file.getAbsolutePath());
        //调用算法

    }

    @Override
    public void onFileChange(File file) {
        System.out.println("修改: " + file.getAbsolutePath());
    }

    @Override
    public void onFileDelete(File file) {
        System.out.println("删除: " + file.getAbsolutePath());
    }
}
