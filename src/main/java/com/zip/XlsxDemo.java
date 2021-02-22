package com.zip;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2020/6/12
 * @Description:
 */
public class XlsxDemo {
    public static void main(String[] args) {
        ExcelReader reader = ExcelUtil.getReader("D:/aa.xlsx");
        List<List<Object>> readAll = reader.read();
        System.out.println(readAll);
    }
}
