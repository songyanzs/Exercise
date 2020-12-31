package com.syzs.udpDemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpSender {
    public static void main(String[] args) {
        DatagramSocket ds = null;
        DatagramPacket dpSend = null;
        int port = 9000;//发送消息到此端口
        try {
            ds = new DatagramSocket();
            for (int i = 0; i < 5; i++) {
                byte[] data = ("UDP 客户端" + i).getBytes();
                dpSend = new DatagramPacket(data, data.length, InetAddress.getByName("127.0.0.1"), port);
                ds.send(dpSend);
                Thread.sleep(1000);
            }
            ds.close();
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
}
