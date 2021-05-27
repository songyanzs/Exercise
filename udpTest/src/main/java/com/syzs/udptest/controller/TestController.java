package com.syzs.udptest.controller;


import com.syzs.udptest.utils.ZipUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
public class TestController {

  /*  public static void main(String[] args) {
        String path = "/export/product/20210107/20200107.png";
        System.out.println(path);
        String s = "/export/product/";
//        int index = path.indexOf(s);
//        String url = path.substring(index+s.length());
        path = path.replace(s,"");
        String url ="/"+  path;
        System.out.println(url);
    }*/


    /**
     * 下载数据包
     *
     * @param response
     * @date: 2021/3/25 15:36
     * @return: void
     */
    @GetMapping("downloadDataZip")
    public void downloadDataZip(HttpServletResponse response) {

        String title = "test.zip";
        File filePath = new File("C:\\Users\\Administrator\\Desktop\\test.zip");
        File[] dir = new File("E:\\市气象局无人机项目\\out\\CF_K6_COOD\\20200107").listFiles();
        ZipUtil.downloadZipFiles(response,dir,title);
        /*ZipUtil.zipFiles(dir,filePath);
        String filename = System.currentTimeMillis()+"_"+title;
        //设置文件路径
        if (filePath.exists()) {
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                response.addHeader("Access-Control-Allow-Origin", "*");
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/octet-stream");
                response.setHeader("Content-disposition", "attachment; filename=" + new String(filename.getBytes("utf-8"), "ISO8859-1"));
                byte[] buffer = new byte[4096];
                fis = new FileInputStream(filePath);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                        // 删除临时文件
                        filePath.delete();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }*/
    }

    @RequestMapping(value = "/downloadByStream",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    public void downloadTest(HttpServletResponse response) {
        String path = "C:\\Users\\Administrator\\Desktop\\广汉\\222.pdf";
        File file = new File(path);
        // 取得文件名。
        String filename = file.getName();
        // 取得文件的后缀名。
        String ext = filename.substring(filename.lastIndexOf(".") + 1);
        BufferedInputStream bis = null;
        OutputStream os = null;
        response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        try {
            bis = new BufferedInputStream(new FileInputStream(file));
            byte[] b = new byte[bis.available() + 1000];
            int i = 0;
            os = response.getOutputStream();   //直接下载导出
            while ((i = bis.read(b)) != -1) {
                os.write(b, 0, i);
            }
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(os!=null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

