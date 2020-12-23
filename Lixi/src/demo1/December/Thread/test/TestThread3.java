package demo1.December.Thread.test;


//创建线程方式2：实现runable接口，重写run方法，执行线程需要丢入runnable接口实现类，调用Start方法
public class TestThread3 implements Runnable {
    @Override
    public void run() {
        //run方法线程体
        for (int i = 0; i < 20; i++) {
            System.out.println("test---------------->"+i);
        }
    }

    public static void main(String[] args) {
        //创建一个线程
        TestThread3 testThread3 = new TestThread3();
        new Thread(testThread3).start();

        for (int i = 0; i < 4000; i++) {
            System.out.println("main---->"+i);
        }
    }
}
