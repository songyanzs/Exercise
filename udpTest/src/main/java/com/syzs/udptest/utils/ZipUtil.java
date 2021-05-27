package com.syzs.udptest.utils;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.utils.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
    /**
     * 把文件打成压缩包并保存在本地硬盘
     */
    public static void saveZipFiles(List<String> srcfiles, String zipFilePath, String zipFileName) {
        try {
            // 创建文件夹
            File file = new File(zipFilePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            // 创建zip输出流
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFilePath + File.separator + zipFileName));
            // 循环将源文件列表添加到zip文件中
            zipFile(srcfiles, zos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把文件打成压缩包并输出到客户端浏览器中
     */
    public static void downloadZipFiles(HttpServletResponse response, List<String> srcFiles, String zipFileName) {
        try {
            response.reset(); // 重点突出
            response.setCharacterEncoding("UTF-8"); // 重点突出
            response.setContentType("application/x-msdownload"); // 不同类型的文件对应不同的MIME类型 // 重点突出
            // 对文件名进行编码处理中文问题
            zipFileName = new String(zipFileName.getBytes(), StandardCharsets.UTF_8);
            // inline在浏览器中直接显示，不提示用户下载
            // attachment弹出对话框，提示用户进行下载保存本地
            // 默认为inline方式
            response.setHeader("Content-Disposition", "inline;filename=" + new String(zipFileName.getBytes("GBK"), "ISO-8859-1"));
            // --设置成这样可以不用保存在本地，再输出， 通过response流输出,直接输出到客户端浏览器中。
            ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
            zipFile(srcFiles, zos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把文件打成压缩包并输出到客户端浏览器中
     */
    public static void downloadZipFiles(HttpServletResponse response, File[] srcFiles, String zipFileName) {
        try {
            response.reset(); // 重点突出
            response.setCharacterEncoding("UTF-8"); // 重点突出
            response.setContentType("application/x-msdownload"); // 不同类型的文件对应不同的MIME类型 // 重点突出
            // 对文件名进行编码处理中文问题
            zipFileName = new String(zipFileName.getBytes(), StandardCharsets.UTF_8);
            // inline在浏览器中直接显示，不提示用户下载
            // attachment弹出对话框，提示用户进行下载保存本地
            // 默认为inline方式
            response.setHeader("Content-Disposition", "inline;filename=" + new String(zipFileName.getBytes("GBK"), "ISO-8859-1"));
            // --设置成这样可以不用保存在本地，再输出， 通过response流输出,直接输出到客户端浏览器中。
            ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
            zipFile(srcFiles, zos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void zipFiles(File[] srcFiles, File zipFile) {
                // 判断压缩后的文件存在不，不存在则创建
                if (!zipFile.exists()) {
                        try {
                                zipFile.createNewFile();
                           } catch (IOException e) {
                               e.printStackTrace();
                            }
                   }
               // 创建 FileOutputStream 对象
                FileOutputStream fileOutputStream = null;
                 // 创建 ZipOutputStream
               ZipOutputStream zipOutputStream = null;
                 // 创建 FileInputStream 对象
                FileInputStream fileInputStream = null;

                try {
                       // 实例化 FileOutputStream 对象
                         fileOutputStream = new FileOutputStream(zipFile);
                        // 实例化 ZipOutputStream 对象
                        zipOutputStream = new ZipOutputStream(fileOutputStream);
                        // 创建 ZipEntry 对象
                        ZipEntry zipEntry = null;
                        // 遍历源文件数组
                        for (int i = 0; i < srcFiles.length; i++) {
                                // 将源文件数组中的当前文件读入 FileInputStream 流中
                               fileInputStream = new FileInputStream(srcFiles[i]);
                                 // 实例化 ZipEntry 对象，源文件数组中的当前文件
                                 zipEntry = new ZipEntry(srcFiles[i].getName());
                                zipOutputStream.putNextEntry(zipEntry);
                                // 该变量记录每次真正读的字节个数
                                int len;
                                // 定义每次读取的字节数组
                                byte[] buffer = new byte[1024];
                                while ((len = fileInputStream.read(buffer)) > 0) {
                                        zipOutputStream.write(buffer, 0, len);
                                    }
                            }
                        zipOutputStream.closeEntry();
                         zipOutputStream.close();
                       fileInputStream.close();
                        fileOutputStream.close();
                     } catch (IOException e) {
                        e.printStackTrace();
                    }

             }
 /**
     * 压缩文件
     *
     * @param filePaths 需要压缩的文件路径集合
     * @throws IOException
     */
    private static void zipFile(List<String> filePaths, ZipOutputStream zos) {
        //设置读取数据缓存大小
        byte[] buffer = new byte[4096];
        try {
            //循环读取文件路径集合，获取每一个文件的路径
            for (String filePath : filePaths) {
                File inputFile = new File(filePath);
                //判断文件是否存在
                if (inputFile.exists()) {
                    //判断是否属于文件，还是文件夹
                    if (inputFile.isFile()) {
                        //创建输入流读取文件
                        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile));
                        //将文件写入zip内，即将文件进行打包
                        zos.putNextEntry(new ZipEntry(inputFile.getName()));
                        //写入文件的方法，同上
                        int size = 0;
                        //设置读取数据缓存大小
                        while ((size = bis.read(buffer)) > 0) {
                            zos.write(buffer, 0, size);
                        }
                        //关闭输入输出流
                        zos.closeEntry();
                        bis.close();
                    } else {  //如果是文件夹，则使用穷举的方法获取文件，写入zip
                        File[] files = inputFile.listFiles();
                        List<String> filePathsTem = new ArrayList<String>();
                        for (File fileTem : files) {
                            filePathsTem.add(fileTem.toString());
                        }
                        zipFile(filePathsTem, zos);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != zos) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private static void zipFile(File[] filePaths, ZipOutputStream zos) {
        //设置读取数据缓存大小
        byte[] buffer = new byte[4096];
        try {
            //循环读取文件路径集合，获取每一个文件的路径
            for (int i=0;i<filePaths.length;i++) {
                File inputFile = filePaths[i];
                //判断文件是否存在
                if (inputFile.exists()) {
                    //判断是否属于文件，还是文件夹
                    if (inputFile.isFile()) {
                        //创建输入流读取文件
                        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile));
                        //将文件写入zip内，即将文件进行打包
                        zos.putNextEntry(new ZipEntry(inputFile.getName()));
                        //写入文件的方法，同上
                        int size = 0;
                        //设置读取数据缓存大小
                        while ((size = bis.read(buffer)) > 0) {
                            zos.write(buffer, 0, size);
                        }
                        //关闭输入输出流
                        zos.closeEntry();
                        bis.close();
                    } else {  //如果是文件夹，则使用穷举的方法获取文件，写入zip
                        File[] files = inputFile.listFiles();
                        List<String> filePathsTem = new ArrayList<String>();
                        for (File fileTem : files) {
                            filePathsTem.add(fileTem.toString());
                        }
                        zipFile(filePathsTem, zos);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != zos) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 删除指定文件或文件夹下所有文件
     *
     * @param path 文件夹完整绝对路径
     * @return
     */
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return false;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (file.isDirectory()) {
            String[] fileList = file.list();
            String tempFilePath = null;
            for (String tempFileName : fileList) {
                if (path.endsWith(File.separator)) {
                    tempFilePath = path + tempFileName;
                } else {
                    tempFilePath = path + File.separator + tempFileName;
                }
                delAllFile(tempFilePath);
            }
            file.delete();
        }
        return true;
    }

    /**
     * 解压zip文件到指定文件夹
     *
     * @param zipFileName 源zip文件路径
     * @param destDir 解压后输出路径
     * @throws IOException IO异常，抛出给调用者处理
     */
    public static void unzip(String zipFileName, String destDir) throws IOException {
        try (
                InputStream inputStream = new FileInputStream(zipFileName);
        ) {
            unzip(inputStream, destDir);
        }

    }

    /**
     * 从输入流中获取zip文件，并解压到指定文件夹
     *
     * @param inputStream zip文件输入流，可以是本地文件输入流，也可以是web请求上传流
     * @param destDir 解压后输出路径
     * @throws IOException IO异常，抛出给调用者处理
     */
    public static void unzip(InputStream inputStream, String destDir) throws IOException {
        try (
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                ArchiveInputStream in = new ZipArchiveInputStream(bufferedInputStream);
        ) {
            ArchiveEntry entry = null;
            while (Objects.nonNull(entry = in.getNextEntry())) {
                if (in.canReadEntryData(entry)) {
                    File file = Paths.get(destDir, entry.getName()).toFile();
                    if (entry.isDirectory()) {
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                    } else {
                        try (
                                OutputStream out = new FileOutputStream(file);
                        ) {
                            IOUtils.copy(in, out);
                        }
                    }
                } else {
                    System.out.println(entry.getName());
                }
            }
        }

    }
}
