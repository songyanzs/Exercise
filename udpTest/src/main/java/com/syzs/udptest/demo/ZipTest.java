package com.syzs.udptest.demo;

import com.syzs.udptest.utils.ZipUtil;

import java.io.File;
import java.util.List;

public class ZipTest {

    public static void main(String[] args) {
        File[] files = new File("E:\\市气象局无人机项目\\out\\CF_K6_COOD\\20200107").listFiles();
        File zipFiles = new File("C:\\Users\\Administrator\\Desktop\\test.zip");
        ZipUtil.zipFiles(files,zipFiles);
        System.out.println("true");
    }


    /**
     * 遍历文件夹获取所有文件
     * @param realpath
     * @param files
     * @return
     */
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
}
