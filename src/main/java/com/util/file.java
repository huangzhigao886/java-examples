package com.util;

import org.apache.hadoop.io.IOUtils;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: huangzhigao
 * @Date: 2019/11/29
 * @Description:
 */
public class file {

    public static void main(String[] args) throws IOException {
//        FileOutputStream os = new FileOutputStream("D://aa.txt");
//        String ss = "100";
//        ByteArrayInputStream in = new ByteArrayInputStream(ss.getBytes());
//        IOUtils.copyBytes(in, os, 1024, true);
//        os.close();

        String writePath = "/encdata/model/tmpResult/newModel_edd62a9f-2ae1-4a57-8344-5dd657fbedf3/8207149a-ba52-4cb8-94f7-36259529d50b.csv";

        String targetFile = writePath.substring(0,writePath.lastIndexOf("/"));
        String substring = writePath.substring(writePath.lastIndexOf("/") + 1);
        String substring1 = substring.substring(0, substring.lastIndexOf(".csv"));

        String result = targetFile+"/count/"+substring1+".txt";
        System.out.println(result);


//        System.out.println(split[split.length-2]);

    }
}
