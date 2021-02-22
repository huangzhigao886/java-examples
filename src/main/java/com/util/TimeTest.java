package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTest {
    public static void main(String[] args) throws ParseException {
        String ss ="2017/01/02 10:32:11";
        String time = "yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(time);
        Date parse = sdf.parse(ss);
        System.out.println(parse);

        System.out.println(System.currentTimeMillis());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("aa").append(";\n").append("bb");
        System.out.println(stringBuilder.toString());

    }
}
