package demo1.December.Thread.demo;


//静态代理模式
//真实对象和代理对象都要实现同一个接口
//代理对象要代理真实角色
public class StacticProxy {

    public static void main(String[] args) {
        WeddingCompany weddingCompany = new WeddingCompany(new You());
        weddingCompany.HappyMarry();
    }
}
interface Marry{
    void HappyMarry();
}

//真实对象
class You implements Marry{

    @Override
    public void HappyMarry() {
        System.out.println("1433223");
    }
}

//代理对象
class WeddingCompany implements Marry{

    private Marry taget;

    public WeddingCompany(Marry taget){
        this.taget = taget;
    }

    @Override
    public void HappyMarry() {
        before();
        this.taget.HappyMarry();
        after();
    }

    private void before(){
        System.out.println("before");
    }

    private void after(){
        System.out.println("after");
    }

}