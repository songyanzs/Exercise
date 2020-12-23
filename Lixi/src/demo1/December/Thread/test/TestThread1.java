package demo1.December.Thread.test;



//创建多线程方法1：继承Thread1类，重写run()方法，调用start开启线程
public class TestThread1 extends Thread {
    @Override
    public void run() {
        //run方法线程体
        for (int i = 0; i < 20; i++) {
            System.out.println("test---------------->"+i);
        }
    }

    public static void main(String[] args) {
        //创建一个线程
        TestThread1 testThread1 = new TestThread1();
        //调用start方法开启线程
        testThread1.start();

        for (int i = 0; i < 2000; i++) {
            System.out.println("main---->"+i);
        }
    }
}
