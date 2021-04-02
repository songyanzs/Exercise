package com.syzs.http;



import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class httpTest {

//    public static void main(String[] args) throws Exception {
//        // WeatherAPI.json("北京");
//        System.out.println(getHttpRequestData("http://192.168.2.62:9090/data/structuredData/getMeteorologyData"));
//    }

    public static String getHttpRequestData(String urlPath) {

        // 首先抓取异常并处理
        String returnString = "1";
        try{
            // 代码实现以GET请求方式为主,POST跳过
            /** 1 GET方式请求数据 start*/

            // 1  创建URL对象,接收用户传递访问地址对象链接
            URL url = new URL(urlPath);

            // 2 打开用户传递URL参数地址
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();

            // 3 设置HTTP请求的一些参数信息
            connect.setRequestMethod("GET"); // 参数必须大写
            connect.connect();

            // 4 获取URL请求到的数据，并创建数据流接收
            InputStream isString = connect.getInputStream();

            // 5 构建一个字符流缓冲对象,承载URL读取到的数据
            BufferedReader isRead = new BufferedReader(new InputStreamReader(isString));

            // 6 输出打印获取到的文件流
            String str = "";
            while ((str = isRead.readLine()) != null) {
                str = new String(str.getBytes(),"UTF-8"); //解决中文乱码问题
//              System.out.println("文件解析打印：");
//              System.out.println(str);
                returnString = str;
            }

            // 7 关闭流
            isString.close();
            connect.disconnect();

            // 8 JSON转List对象
            // do somthings


        }catch(Exception e){
            e.printStackTrace();
        }

        return returnString;
    }


    public static void main(String[] args) {
        String res = HttpUtils.sendGet("http://192.168.5.109:8080/data/structuredData/getAirData","");
        System.out.println(res);
    }


}
