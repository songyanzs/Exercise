package com.syzs.udptest.demo;

import org.apache.storm.shade.org.apache.commons.io.monitor.FileAlterationListenerAdaptor;

import java.io.File;

/**
 * 自定义文件监听器
 * @author
 * @date    2010-11-16
 * @file    org.demo.file.MyFileListener.java
 */
public class MyFileListener extends FileAlterationListenerAdaptor {
    @Override
    public void onFileCreate(File file) {
        System.out.println("[新建]:" + file.getAbsolutePath());
        System.out.println("新增文件名为："+file.getName());
    }
    @Override
    public void onFileChange(File file) {
        System.out.println("[修改]:" + file.getAbsolutePath());
    }
    @Override
    public void onFileDelete(File file) {
        System.out.println("[删除]:" + file.getAbsolutePath());
    }
}
