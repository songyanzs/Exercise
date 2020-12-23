package demo1.December.Thread.test;



//多个线程同时操作同一个对象

//问题;多个线程操作同一个资源时，线程不安全，数据紊乱
public class TestThread4 implements Runnable{

    //票数
    private int ticketNums = 10;


    @Override
    public void run() {
        while (true){
            if(ticketNums<=0){
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"-->get number"+ticketNums--+" ticket");
        }
    }

    public static void main(String[] args) {
        TestThread4 testThread4 = new TestThread4();
        new Thread(testThread4,"teacher").start();
        new Thread(testThread4,"student").start();
        new Thread(testThread4,"me").start();
    }
}
