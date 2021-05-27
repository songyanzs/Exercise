package com.syzs.udptest.utils;

import org.apache.commons.net.ftp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FtpUtil {
    private static Logger logger = LoggerFactory.getLogger(FtpUtil.class);
    /** 本地字符编码 */
    private static String LOCAL_CHARSET = "GBK";

    // FTP协议里面，规定文件名编码为iso-8859-1
    private static String SERVER_CHARSET = "ISO-8859-1";

    public static Boolean uploadFtp(String hostname, String username, String password, int port, String path, File file){
        //创建客户端对象
        FTPClient ftp = new FTPClient();
        InputStream local=null;
        try {
            //连接ftp服务器
            ftp.connect(hostname, port);
            //登录
            ftp.login(username, password);
            boolean flag = ftp.changeWorkingDirectory(path);
            if(!flag){
                //创建上传的路径  该方法每次只能创建一级目录，在这里如果/home/ftpuser存在则可创建image
                ftp.makeDirectory(path);
            }
            //指定上传路径
            ftp.changeWorkingDirectory(path);
            //指定上传文件的类型  二进制文件
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            local = new FileInputStream(file);
            //第一个参数是文件名
            ftp.storeFile(new String(file.getName().getBytes("UTF-8"),"iso-8859-1"), local);
        } catch (Exception e) {
            logger.info("-----------上传FTP失败-----------");
            e.printStackTrace();
            return false;
        } finally {
            try {
                //关闭文件流
                local.close();
                //退出
                ftp.logout();
                //断开连接
                ftp.disconnect();
            } catch (IOException e) {
                logger.info("-----------上传FTP失败-----------");
                e.printStackTrace();
                return false;
            }
        }
        logger.info("-----------上传FTP成功-----------");
        return true;
    }
    public static void downloadFtpFile(String hostname, String username, String password, int port, FTPFileFilter ftpFileFilter, String ftpPath, String localPath) {
        FTPClient ftp = new FTPClient();
        try {
            //连接ftp服务器
            ftp.connect(hostname, port);
            //登录
            ftp.login(username, password);
            // 设置上传文件的类型为二进制类型
            if (FTPReply.isPositiveCompletion(ftp.sendCommand("OPTS UTF8", "ON"))) {// 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
                LOCAL_CHARSET = "UTF-8";
            }
            ftp.setControlEncoding(LOCAL_CHARSET);
            ftp.enterLocalPassiveMode();// 设置被动模式
            ftp.setFileType(FTP.BINARY_FILE_TYPE);// 设置传输的模式
            //创建日期路径
            Date currentDate = new Date();
            String dateStr=new SimpleDateFormat("yyyyMMdd").format(currentDate);
            //创建本地日期路径
            localPath=localPath+dateStr+"/";
            File dirlocalPath=new File(localPath);
            if(!dirlocalPath.exists()){
                dirlocalPath.mkdirs();
            }
            InputStream retrieveFileStream=null;
            ftp.changeWorkingDirectory(ftpPath);
            FTPFile[] files=ftp.listFiles(ftpPath, ftpFileFilter);
            for (FTPFile file : files) {
                if (file.isFile()) {
                    //对中文文件名进行转码，否则中文名称的文件下载失败
                    String fileNameTemp = new String(file.getName().getBytes(LOCAL_CHARSET), SERVER_CHARSET);
                    retrieveFileStream = ftp.retrieveFileStream(fileNameTemp);
                    // 下载：将输入流转成字节，再生成文件，这种方式方便将字节数组直接返回给前台jsp页面
                    byte[] input2byte = input2byte(retrieveFileStream);
                    byte2File(input2byte, localPath, file.getName());
                    if(null != retrieveFileStream){
                        retrieveFileStream.close();
                        ftp.completePendingCommand();
                    }
                    //删除
                    deleteFile(hostname,username,password,port,ftpPath,file.getName());
                }
            }

        } catch (FileNotFoundException e) {
            logger.info("-----------下载FTP失败-----------");
            e.printStackTrace();
        } catch (SocketException e) {
            logger.info("-----------下载FTP失败-----------");
            e.printStackTrace();
        } catch (IOException e) {
            logger.info("-----------下载FTP失败-----------");
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    //退出登录
                    ftp.logout();
                    //关闭连接
                    ftp.disconnect();
                } catch (IOException e) {
                    logger.info("-----------下载FTP失败-----------");
                    e.printStackTrace();
                }
            }
        }
        logger.info("-----------下载FTP成功-----------");
    }

    public static void downloadFtpFile(String hostname, String username, String password, int port, String ftpPath, String localPath) {
        FTPClient ftp = new FTPClient();
        try {
            //连接ftp服务器
            ftp.connect(hostname, port);
            //登录
            ftp.login(username, password);
            // 设置上传文件的类型为二进制类型
            if (FTPReply.isPositiveCompletion(ftp.sendCommand("OPTS UTF8", "ON"))) {// 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
                LOCAL_CHARSET = "UTF-8";
            }
            ftp.setControlEncoding(LOCAL_CHARSET);
            ftp.enterLocalPassiveMode();// 设置被动模式
            ftp.setFileType(FTP.BINARY_FILE_TYPE);// 设置传输的模式
            File dirlocalPath=new File(localPath);
            if(!dirlocalPath.exists()){
                dirlocalPath.mkdirs();
            }
            InputStream retrieveFileStream=null;
            ftp.changeWorkingDirectory(ftpPath);
            FTPFile[] files=ftp.listFiles(ftpPath);
            for (FTPFile file : files) {
                if (file.isFile()) {
                    //对中文文件名进行转码，否则中文名称的文件下载失败
                    String fileNameTemp = new String(file.getName().getBytes(LOCAL_CHARSET), SERVER_CHARSET);
                    retrieveFileStream = ftp.retrieveFileStream(fileNameTemp);
                    // 下载：将输入流转成字节，再生成文件，这种方式方便将字节数组直接返回给前台jsp页面
                    byte[] input2byte = input2byte(retrieveFileStream);
                    byte2File(input2byte, localPath, file.getName());
                    if(null != retrieveFileStream){
                        retrieveFileStream.close();
                        ftp.completePendingCommand();
                    }
                    //删除
                    deleteFile(hostname,username,password,port,ftpPath,file.getName());
                }
            }

        } catch (FileNotFoundException e) {
            logger.info("-----------下载FTP失败-----------");
            e.printStackTrace();
        } catch (SocketException e) {
            logger.info("-----------下载FTP失败-----------");
            e.printStackTrace();
        } catch (IOException e) {
            logger.info("-----------下载FTP失败-----------");
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    //退出登录
                    ftp.logout();
                    //关闭连接
                    ftp.disconnect();
                } catch (IOException e) {
                    logger.info("-----------下载FTP失败-----------");
                    e.printStackTrace();
                }
            }
        }
        logger.info("-----------下载FTP成功-----------");
    }

    public static boolean deleteFile(String hostname, String username, String password, int port, String path, String FileName) {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        ftp.setControlEncoding("GBK");
        try {
            int reply;
            ftp.connect(hostname, port);// 连接FTP服务器
            ftp.login(username, password);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftp.makeDirectory(path);
            ftp.changeWorkingDirectory(path);
            success = ftp.deleteFile(FileName);
            ftp.logout();
        } catch (IOException e) {
            logger.info("-----------删除FTP失败-----------");
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    logger.info("-----------删除FTP失败-----------");
                    ioe.printStackTrace();
                }
            }
        }
        logger.info("-----------删除FTP成功-----------");
        return success;
    }

    // 将字节数组转换为输入流
    public static final InputStream byte2Input(byte[] buf) {
        return new ByteArrayInputStream(buf);
    }

    // 将输入流转为byte[]
    public static final byte[] input2byte(InputStream inStream) throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }
    // 将byte[]转为文件
    public static void byte2File(byte[] buf, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {
                dir.mkdirs();
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buf);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        FtpUtil ft=new FtpUtil();
        File file =new File("E:\\tex\\input_new.xml");
       // ft.uploadFtp("192.168.5.36","admin","admin",21,file);
    }
}
