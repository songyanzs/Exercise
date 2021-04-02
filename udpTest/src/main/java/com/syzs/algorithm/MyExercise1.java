package com.syzs.algorithm;

import java.io.File;

public class MyExercise1 {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Administrator\\Desktop\\保存算法.txt");
        file.renameTo(new File("C:\\Users\\Administrator\\Desktop\\保存算法1.txt"));
    }
}
