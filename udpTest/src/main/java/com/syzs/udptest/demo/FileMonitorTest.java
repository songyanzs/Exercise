package com.syzs.udptest.demo;

import org.apache.storm.shade.org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.storm.shade.org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.storm.shade.org.apache.commons.io.monitor.FileAlterationObserver;

import java.util.concurrent.TimeUnit;

/**
 * 文件监控测试
 * @author
 * @date    2010-11-16
 * @file    org.demo.file.FileMonitor.java
 */
public class FileMonitorTest {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // 监控目录
        String rootDir = "E:\\代码";
        // 轮询间隔 5 秒
        long interval = TimeUnit.SECONDS.toMillis(5);
        //
        FileAlterationObserver observer = new FileAlterationObserver(rootDir);
        observer.addListener(new MyFileListener());
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval,observer);
        // 开始监控
        monitor.start();
    }
}
