package com.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2020/9/4
 * @Description:
 */
public class CsvUtils {
    public static void main(String[] args) throws UnsupportedEncodingException {
        CsvReader reader = CsvUtil.getReader();
//从文件中读取CSV数据
        CsvData data = reader.read(FileUtil.file("D://ajxq.csv"));
        List<CsvRow> rows = data.getRows();
//遍历行
        for (CsvRow csvRow : rows) {
            //getRawList返回一个List列表，列表的每一项为CSV中的一个单元格（既逗号分隔部分）
            String s = csvRow.get(0);
            byte[] b_iso =  s.getBytes("GBK");
            String after = new String(b_iso, "UTF-8");
            System.out.println(after);
        }
    }
}
