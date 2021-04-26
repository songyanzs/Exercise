package com.syzs.udptest.thread;

public class MyTest1 {
/*    public static void main(String[] args) {
        sellTickets th1 = new sellTickets("线程-A");
        sellTickets th2 = new sellTickets("线程-B");
        sellTickets th3 = new sellTickets("线程-C");
        th1.start();
        th2.start();
        th3.start();
    }*/


    public static void main(String[] args) {
        SellRunnable runnable = new SellRunnable();
        Thread th1 = new Thread(runnable,"线程-A");
        Thread th2 = new Thread(runnable,"线程-B");
        Thread th3 = new Thread(runnable,"线程-C");

        th1.start();
        th2.start();
        th3.start();
    }


}

