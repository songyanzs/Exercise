package demo1.December.Thread.syn;

public class UnsafeBuyTicket {

    public static void main(String[] args) {

        BuyTicket buyTicket = new BuyTicket();
        new Thread(buyTicket,"a").start();
        new Thread(buyTicket,"b").start();
        new Thread(buyTicket,"v").start();
    }
}


class BuyTicket implements Runnable{

    private int ticketNums =10;
    boolean falg = true;

    @Override
    public void run() {
        while (falg){
            buy();
        }
    }

    //synchronized 同步方法，锁的是this
    private synchronized void buy(){
        if(ticketNums<=0){
            falg = false;
            return;
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" get "+ticketNums--);
    }
}
