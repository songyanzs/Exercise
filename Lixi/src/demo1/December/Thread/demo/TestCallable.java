package demo1.December.Thread.demo;


import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

//线程创建方式3：实现callable接口
public class TestCallable implements Callable<Boolean>{

    private String url;
    private String name;

    public TestCallable(String url,String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public Boolean call() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.download(url,name);
        System.out.println("下载"+url+"完成"+name);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        TestCallable t1 = new TestCallable("https://assets.segmentfault.com/v-5fdaf840/global/img/static/touch-icon.png","C:\\Users\\Administrator\\Desktop\\1.png");
        TestCallable t2 = new TestCallable("https://assets.segmentfault.com/v-5fdaf840/global/img/static/touch-icon.png","C:\\Users\\Administrator\\Desktop\\2.png");
        TestCallable t3 = new TestCallable("https://assets.segmentfault.com/v-5fdaf840/global/img/static/touch-icon.png","C:\\Users\\Administrator\\Desktop\\3.png");

        //创建执行服务
        ExecutorService service = Executors.newFixedThreadPool(3);
        //提交执行
        Future<Boolean> R1 = service.submit(t1);
        Future<Boolean> R2 = service.submit(t2);
        Future<Boolean> R3 = service.submit(t3);

        //获取结果
        boolean rs1 = R1.get();
        boolean rs2 = R2.get();
        boolean rs3 = R3.get();

        //关闭服务
        service.shutdownNow();
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