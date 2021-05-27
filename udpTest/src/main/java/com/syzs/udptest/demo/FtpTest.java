package com.syzs.udptest.demo;

import com.syzs.udptest.utils.FtpUtil;

import java.io.File;

public class FtpTest {

    public static void main(String[] args) {
        String hostname = "192.168.5.117";
        String username = "ftptest";
        String password = "123456";
        int port = 21;
        String path = "/home/vsftpd/ftptest/";
        File file = new File("C:\\Users\\Administrator\\Desktop\\气体数据.txt");
        FtpUtil.uploadFtp(hostname,username,password,port,path,file);
    }
}
