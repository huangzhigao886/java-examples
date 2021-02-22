package com.util;

/**
 * @description: 字符串拼接
 * @author: hzg
 * @create: 2019-09-10 19:22
 **/

public class StringUtil {
    private StringBuilder str  ;

    public String creatNewString() {
        str = new StringBuilder();
        for (int i = 0 ;i<3;i++){
            str.append(i).append(",");
        }
        return str.deleteCharAt(str.length()-1).toString();
    }

    public static void main(String[] args) {
        StringUtil ss = new StringUtil() ;
        System.out.println(ss.creatNewString());


    }

}