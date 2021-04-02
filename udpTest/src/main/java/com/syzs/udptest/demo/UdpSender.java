package com.syzs.udptest.demo;

import org.springframework.beans.factory.annotation.Value;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpSender {

    public static void main(String[] args) {
        DatagramSocket ds = null;
        DatagramPacket dpSend = null;
        int port = 9999;//发送消息到此端口
        try {
            ds = new DatagramSocket();
            for (int i = 0; i < 1; i++) {
//                byte[] data = ("python").getBytes();
                byte[] data = ("{\"DATA_TYPE\":\"1\",\n" +
                        "\"DATA_TIME\":\"2020/10/09 10:52:03\",\n" +
                        "\"UAV_ID\":\"ssaxasxcas\",\n" +
                        "\"EQUIPMENT_ID\":\"csdcasdc\",\n" +
                        "\"PM10\":\"CDS\"\n" +
                        "}").getBytes();

                dpSend = new DatagramPacket(data, data.length, InetAddress.getByName("localhost"), port);
                ds.send(dpSend);
                Thread.sleep(   1000);
            }
            ds.close();
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
}
