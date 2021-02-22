package com.util;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @description: String类的format使用
 * @author: hzg
 * @create: 2019-09-22 14:50
 **/

public class String_Format_Method {
    public static void main(String[] args) throws IOException {
//        String str1 = "/job/%t";


//        System.out.println(String.format(str1,3767));
        List<String> list = IOUtils.readLines(new FileReader(new File("D://测试工厂不合格数据/11.json")));
        String res = list.get(0).replaceAll("\\\\","");
        Gson gson = new Gson() ;
        Map<String,String> map = gson.fromJson(res, Map.class);
        System.out.println(map.get("path"));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("D://tmp/2.json")));
//        bufferedWriter.write(res);
//        bufferedWriter.close();
    }
}
