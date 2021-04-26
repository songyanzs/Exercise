package com.syzs.udptest.demo;

import org.springframework.beans.factory.annotation.Value;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpSender {

    public static void main(String[] args) {
        DatagramSocket ds = null;
        DatagramPacket dpSend = null;
        int port = 11322;//发送消息到此端口
        try {
            ds = new DatagramSocket();
            for (int i = 0; i < 1; i++) {
//                byte[] data = ("python").getBytes();
                byte[] data = ("{\n" +
                        "    \"airData\": {\n" +
                        "        \"CO(ppm)\": 0.3856,\n" +
                        "        \"NO2(ppm)\": 0.0295,\n" +
                        "        \"Ox(ppm)\": 0.0397,\n" +
                        "        \"PM1.0(ug/m3)\": 7,\n" +
                        "        \"PM10(ug/m3)\": 26,\n" +
                        "        \"PM2.5(ug/m3)\": 16,\n" +
                        "        \"SO2(ppm)\": 0.0019\n" +
                        "    },\n" +
                        "    \"altitude\": 487.8397,\n" +
                        "    \"hdop\": 99.98,\n" +
                        "    \"humidity\": 26.8627,\n" +
                        "    \"latitude\": -90,\n" +
                        "    \"longitude\": -180,\n" +
                        "    \"pressure\": 95627.5156,\n" +
                        "    \"sateNum\": 0,\n" +
                        "    \"sequence\": 206,\n" +
                        "    \"serial\": \"f8218974\",\n" +
                        "    \"temperature\": 16.8627,\n" +
                        "    \"utcTime\": \"2021-04-16-06-33-37\"\n" +
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
