package com.syzs.udptest.collect;

import org.apache.storm.shade.org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.storm.shade.org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

import java.util.concurrent.TimeUnit;


/**
 * 文件监听
 * @author sy
 *
 */
//@Component
public class FileMonitor implements CommandLineRunner {


    @Autowired
    private MyFileListener myFileListener;

    @Value("${file.monitorPath}")
    private String monitorPath;


    public void fileMonitor(String path) throws Exception {
        System.out.println("文件监控run......");
        // 轮询
        long interval = TimeUnit.SECONDS.toMillis(3);
        FileAlterationObserver observer = new FileAlterationObserver(path);
        observer.addListener(myFileListener);
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval,observer);
        // 开始监控
        monitor.start();
    }


    @Override
    public void run(String... args) throws Exception {
        fileMonitor(monitorPath);
    }
}
