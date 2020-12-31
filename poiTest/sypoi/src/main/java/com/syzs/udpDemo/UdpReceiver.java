package com.syzs.udpDemo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpReceiver {

    public static void main(String[] args) throws SocketException {
        int port=1515;//接收此端口消息
        DatagramSocket socket= new DatagramSocket(port);
        System.out.println("UDP服务器已启动.....");
        byte[] buffer = new byte[1024];
        while (true) {
            if(socket.isClosed()){
                System.out.println("UDP已关闭");
            }
            DatagramPacket incoming = new DatagramPacket(buffer,
                    buffer.length);
            try {
                socket.receive(incoming);
                String msg = new String(incoming.getData(),0,incoming.getLength());
                System.out.println("发送端发送的内容是：" + msg);
                System.out.println("发送端IP：" + incoming.getAddress());
                System.out.println("发送端端口：" + incoming.getPort());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
