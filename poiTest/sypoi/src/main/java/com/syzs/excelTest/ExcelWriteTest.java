package com.syzs.excelTest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWriteTest {

    //生成excel的路径
    String PATH = "C:\\Users\\Administrator\\Desktop\\";


    @Test
    public void testWrite03(){
        //创建一个工作簿
        Workbook workbook = new HSSFWorkbook();
        //创建一个工作表
        Sheet sheet = workbook.createSheet("syzs");
        //创建一个行
        Row row1 = sheet.createRow(0);
        //创建一个单元格
        Cell cell11 = row1.createCell(0);
        cell11.setCellValue("TEST");
        Cell cell12 = row1.createCell(1);
        cell12.setCellValue("TEST1");

        //第二行
        Row row2 = sheet.createRow(1);
        Cell cell2 = row2.createCell(0);
        cell2.setCellValue("ssss");

        //生成一张表
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(PATH + "TEST.xls");
            workbook.write(fileOutputStream);
            //关闭流
            fileOutputStream.close();
            System.out.println("creat excel finished");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWrite07(){
        //创建一个工作簿 07
        Workbook workbook = new XSSFWorkbook();
        //创建一个工作表
        Sheet sheet = workbook.createSheet("syzs");
        //创建一个行
        Row row1 = sheet.createRow(0);
        //创建一个单元格
        Cell cell11 = row1.createCell(0);
        cell11.setCellValue("TEST");
        Cell cell12 = row1.createCell(1);
        cell12.setCellValue("TEST1");

        //第二行
        Row row2 = sheet.createRow(1);
        Cell cell2 = row2.createCell(0);
        cell2.setCellValue("ssss");

        //生成一张表
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(PATH + "TEST.xlsx");
            workbook.write(fileOutputStream);
            //关闭流
            fileOutputStream.close();
            System.out.println("creat excel finished");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWrite03BigData(){

        //创建一个工作簿
        Workbook workbook = new HSSFWorkbook();
        //创建一个工作表
        Sheet sheet = workbook.createSheet("syzs");

        for (int rowNum = 0; rowNum < 65536; rowNum++) {
            Row row = sheet.createRow(rowNum);
            for (int cellNum = 0; cellNum < 10; cellNum++) {
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(cellNum);
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(PATH + "bigTest.xls");
            workbook.write(fileOutputStream);
            //关闭流
            fileOutputStream.close();
            System.out.println("creat excel finished");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWrite07BigData(){

        //创建一个工作簿
        Workbook workbook = new XSSFWorkbook();
        //创建一个工作表
        Sheet sheet = workbook.createSheet("syzs");

        for (int rowNum = 0; rowNum < 65536; rowNum++) {
            Row row = sheet.createRow(rowNum);
            for (int cellNum = 0; cellNum < 10; cellNum++) {
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(cellNum);
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(PATH + "bigTest2.xlsx");
            workbook.write(fileOutputStream);
            //关闭流
            fileOutputStream.close();
            System.out.println("creat excel finished");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWrite07BigDataSS(){

        //创建一个工作簿
        Workbook workbook = new SXSSFWorkbook();
        //创建一个工作表
        Sheet sheet = workbook.createSheet("syzs");

        for (int rowNum = 0; rowNum < 65536; rowNum++) {
            Row row = sheet.createRow(rowNum);
            for (int cellNum = 0; cellNum < 10; cellNum++) {
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(cellNum);
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(PATH + "bigTestsss.xls");
            workbook.write(fileOutputStream);
            //关闭流
            fileOutputStream.close();
            ((SXSSFWorkbook)workbook).dispose();
            System.out.println("creat excel finished");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
