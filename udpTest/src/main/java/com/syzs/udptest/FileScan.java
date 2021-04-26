package com.syzs.udptest;

import org.apache.commons.io.FileUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Component
public class FileScan  implements CommandLineRunner{

    //递归获取文件夹中的所有文件
    public static List<File> getFiles(String realpath, List<File> files){
        File realFile = new File(realpath);
        if (realFile.isDirectory()){
            File[] subfiles = realFile.listFiles();
            for (File file : subfiles){
                if (file.isDirectory()){
                    getFiles(file.getAbsolutePath(),files);
                }else {
                    files.add(file);
                }
            }
        }
        return files;
    }

    public void start(String path){
        List<File> files = new ArrayList<>();
        files = getFiles(path,files);
        if(files.size()>0){
            for(File file : files){
                System.out.println(file.getAbsolutePath());
                File dest = new File("E:\\代码\\"+file.getName());
                if(dest.exists()){
                    dest.delete();
                }
                try {
                    FileUtils.moveFile(file,dest);
                    System.out.println("文件搬运成功...");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public void run(String... args) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println("文件轮询开始");
                    start("E:\\monitorDir");
                    try {
                        Thread.sleep(TimeUnit.MINUTES.toMillis(90));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


}
