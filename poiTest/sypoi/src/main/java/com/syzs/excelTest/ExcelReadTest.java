package com.syzs.excelTest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelReadTest {

    String PATH = "C:\\Users\\Administrator\\Desktop\\";


    @Test
    public void testWrite03() throws IOException {

        //获取文件流
        FileInputStream fileInputStream = new FileInputStream(PATH+"TEST.xls");
        //创建一个工作簿
        Workbook workbook = new HSSFWorkbook(fileInputStream);
        //得到表
        Sheet sheet = workbook.getSheetAt(0);
        //得到行
        Row row = sheet.getRow(1);
        //得到列
        Cell cell = row.getCell(0);
        System.out.println(cell.getStringCellValue());
        fileInputStream.close();
    }
}
