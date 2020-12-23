package demo1.December.Thread.status;

import demo1.November.questions.Merge;

//测试礼让线程
//礼让不一定成功，看cpu心情
public class TestYield {
}

class MyYield implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"START");
        Thread.yield();//礼让
        System.out.println(Thread.currentThread().getName()+"STOP");
    }

    public static void main(String[] args) {

        MyYield myYield = new MyYield();

        new Thread(myYield,"a").start();
        new Thread(myYield,"b").start();
    }
}
