package demo1.December.Thread.status;

//模拟网络延时
public class TestSleep implements Runnable{

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
        TestSleep testThread4 = new TestSleep();
        new Thread(testThread4,"teacher").start();
        new Thread(testThread4,"student").start();
        new Thread(testThread4,"me").start();
    }
}
