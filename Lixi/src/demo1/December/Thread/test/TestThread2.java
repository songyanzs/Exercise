package demo1.December.Thread.test;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

//练习Thread，实现多线程同步下载图片
public class TestThread2 extends Thread {

    private String url;
    private String name;

    public TestThread2(String url,String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.download(url,name);
        System.out.println("下载"+url+"完成"+name);
    }

    public static void main(String[] args) {

        TestThread2 t1 = new TestThread2("https://assets.segmentfault.com/v-5fdaf840/global/img/static/touch-icon.png","C:\\Users\\Administrator\\Desktop\\1.png");
        TestThread2 t2 = new TestThread2("https://assets.segmentfault.com/v-5fdaf840/global/img/static/touch-icon.png","C:\\Users\\Administrator\\Desktop\\2.png");
        TestThread2 t3 = new TestThread2("https://assets.segmentfault.com/v-5fdaf840/global/img/static/touch-icon.png","C:\\Users\\Administrator\\Desktop\\3.png");
        t1.start();
        t2.start();
        t3.start();

    }
}



class WebDownloader{
    //下载方法
    public void download(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}