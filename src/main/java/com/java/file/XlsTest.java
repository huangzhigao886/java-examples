package com.java.file;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.Iterator;


public class XlsTest {
    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream("D://aa.xlsx");
        XSSFWorkbook sheets = new XSSFWorkbook(file);
        XSSFSheet sheetAt = sheets.getSheetAt(0);
        Iterator<Row> iterator = sheetAt.iterator();
        iterator.forEachRemaining(a-> System.out.println(a.getCell(9)));
        System.out.println(sheetAt.getPhysicalNumberOfRows());
    }
}
