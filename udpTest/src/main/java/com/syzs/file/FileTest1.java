package com.syzs.file;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileTest1 {

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

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = formatter.parse("2021-01-09 21:06:56");
        List<File> files = new ArrayList<>();
        files = getFiles("E:\\file",files);
        for(File file : files){
            Long lastModified = file.lastModified();
            Date date = new Date(lastModified);
            if(date.after(date1)){
                System.out.println(file.getAbsolutePath());
                System.out.println(date);
            }
        }
    }

}
