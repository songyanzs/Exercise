package demo1.December.Thread.status;

public class TestStop implements Runnable{
    private boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while (flag){
            System.out.println("run......."+i++);
        }
    }

    public void stop(){
        this.flag = false;
    }

    public static void main(String[] args) {
        TestStop stop = new TestStop();
        new Thread(stop).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("MAIN"+i);
            if(i==600){
                stop.stop();
                System.out.println("STOP");
            }
        }
    }
}
