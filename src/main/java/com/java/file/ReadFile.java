package com.java.file;

import cn.hutool.core.io.file.FileReader;

/**
 * @Auther: huangzhigao
 * @Date: 2019/11/29
 * @Description:
 */
public class ReadFile {
    public static void main(String[] args) {
//        FileReader fileReader = new FileReader("D://测试工厂不合格数据/5677.json");
//        String flinkJson = fileReader.readString();
//        System.out.println(flinkJson);


        String str = "LOCAL-ABCCDA";
        if(str.startsWith("LOCAL")){
            str =str.substring(5);
        }
        System.out.println(str);
    }
}
