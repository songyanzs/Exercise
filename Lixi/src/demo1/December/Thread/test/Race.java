package demo1.December.Thread.test;


//多线程example:龟兔赛跑
public class Race implements Runnable{

    private static String winnner;

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            // let rabbit have a rest
            if(Thread.currentThread().getName().equals("rabbit")&& i/10==3){
                try {
                    Thread.sleep(1 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(gameOver(i)){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"----> haven runed "+i+" meters");
        }
    }

    // is gameOver ? ? ?
    public boolean gameOver(int Steps){
        if(winnner!=null){
            return true;
        }else{
            if (Steps>=100){
                winnner = Thread.currentThread().getName();
                System.out.println("winner is " + winnner);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Race race = new Race();

        new Thread(race,"rabbit").start();
        new Thread(race,"turtle").start();

    }
}
