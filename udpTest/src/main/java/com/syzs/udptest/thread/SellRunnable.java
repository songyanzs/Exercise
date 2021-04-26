package com.syzs.udptest.thread;


/*public class SellRunnable implements Runnable{
    private static int tickets = 100;

    @Override
    public synchronized void run() {
        while (true) {
            if (tickets >= 1) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "卖出一张票，还剩：" + (tickets--) + "张票");
            }
        }
    }
}*/


/**
 * 静态同步方法
 */
public class SellRunnable implements Runnable{
    //这个票让三个线程共享
    static int piao = 100; //共享数据

    @Override
    public void run() {
        while (true) {
            //th1 th2 th3
            maiPiao();
            //th1 执行完了，出了同步代码块，就会释放锁。释放锁了之后，多个线程再去争抢CPU的时间片
        }
    }

    //静态同步方法：默认用的锁对对象，用的是当前类的字节码对象
    public static synchronized void maiPiao() {
        if (piao >= 1) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "正在出售第：" + (piao--) + " 张票");
        }
    }
    
}


