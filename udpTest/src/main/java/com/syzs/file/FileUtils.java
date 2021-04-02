package com.syzs.file;

import java.io.*;

public class FileUtils {

    // 读取指定路径文本文件
    public static String read(String filePath) {
        StringBuilder str = new StringBuilder();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(filePath));
            String s;
            try {
                while ((s = in.readLine()) != null)
                    str.append(s + '\n');
            } finally {
                in.close();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return str.toString();
    }

    // 写入指定的文本文件，append为true表示追加，false表示重头开始写，
    //text是要写入的文本字符串，text为null时直接返回
    public static void write(String filePath, boolean append, String text) {
        if (text == null)
            return;
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filePath,
                    append));
            try {
                out.write(text);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String text = read("E:\\市气象局无人机项目\\code\\NDVI_test.py");
        System.out.println(text);
        write("C:\\Users\\Administrator\\Desktop\\3.py",false,text);
    }
}

