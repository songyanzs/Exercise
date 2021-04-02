package com.syzs.udptest.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.Timer;
import java.util.TimerTask;

public class FileScan {

    private static String path = "E:\\代码\\python";

    public static void getFile() throws FileNotFoundException, IOException {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                WatchKey key;
                try {
                    WatchService watchService = FileSystems.getDefault().newWatchService();
                    Paths.get(path).register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
                    while (true) {
                        File file = new File(path);//path为监听文件夹
                        File[] files = file.listFiles();
                        System.out.println("等待图片加载！");
                        key = watchService.take();//没有文件增加时，阻塞在这里
                        for (WatchEvent<?> event : key.pollEvents()) {
                            String fileName = path+"\\"+event.context();
                            System.out.println("增加文件的文件夹路径"+fileName);
                        }if (!key.reset()) {
                            break; //中断循环
                        }
                    }
                }catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, 2000 , 3000);//第一个数字2000表示，2000ms以后开启定时器,第二个数字3000，表示3000ms后运行一次run
    }


    public static void main(String[] args) {
        try {
            getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
