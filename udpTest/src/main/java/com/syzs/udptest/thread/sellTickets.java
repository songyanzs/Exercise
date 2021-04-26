package com.syzs.udptest.thread;

/*
*未使用同步代码块锁，争抢了主内存的共享变量
public class sellTickets extends Thread {
    private static int tickets = 100;

    public sellTickets(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true) {
            if (tickets >= 1) {
                try {
						*//*售票时网络是不能实时传输的，
						总是存在延迟的情况，所以，在出售一张票以后，
						需要一点时间的延迟，
						这里增加的这行代码是为了更好的演示多线程的随机性与模拟网络延迟*//*
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "卖出一张票，还剩：" + (tickets--) + "张票");
            }
        }
    }
}*/

//自定义卖票类
public class sellTickets extends Thread {
    private static int tickets = 100;
    //创建一个三个线程共同持有的一把锁对象
    private static Object obj = new Object();

    public sellTickets(String name) {
        super(name);
    }

    /**
      synchronized(对象){
     //不能在括号了直接 new 对象 new 了就没效果要被同步的代码 ;
     //这个对象可以使用任意的Java对象代替;
     }
     使用同步代码块包裹住可能出现数据安全问题的代码，保证在某一时刻只有一个线程可以进来这段代码并持有锁，它一旦持有了这把锁，等到执行完这段逻辑，才会释放锁，让线程们再次争抢CPU资源，来持有这把锁；
     三个线程必须使用同一个锁对象，因为只有这样，才能保证某一时间段内只有一个线程可以持有这把锁，其中一个线程抢到了CPU执行权，锁住了门，其他线程只能等在门外，如果三个对象各自使用一把锁，就没有任何意义了；
     1、什么是synchronized?
     synchronized关键字可以实现一个简单的策略来防止线程干扰和内存一致性错误，如果一个对象是对多个线程可见的，那么对该对象的所有读写都将通过同步的方式来进行；
     2、synchronized包括哪两个JVM重要的指令？
     monitor enter和 monitor exit
     3、synchronized锁的是什么?
     普通同步方法 ---------------> 锁的是当前实例对象；
     静态同步方法---------------> 锁的是当前类的Class对象；
     同步方法块 ---------------> 锁的是synchonized括号里配置的对象；
     */
    @Override
    public void run() {
        while (true) {
            synchronized (obj) {
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
    }
}
