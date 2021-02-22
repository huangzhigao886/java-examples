package com.util;

import com.model.Student;

import java.io.File;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: hzg
 * @create: 2019-10-01 17:54
 **/

public class TimeUtil {
    public static void main(String[] args) throws ParseException, MalformedURLException {
        String str = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(str);
//        Date date = sdf.parse("2014-12-11 13:22:11");
//        System.out.println();

        String str1 = "2007-1-18";
        Date parse = sdf.parse(str1);
        System.out.println(parse);

        List<String> list = null;
        Student student = new Student();
        student.setList(list);
        System.out.println(student);

        File file = new File("E//bb.json");
        System.out.println(file.getAbsoluteFile());
        System.out.println();

        String startTime = "1568722465031";
        String endTime = "1568722465654";
        String start = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(Long.valueOf(startTime) / 1000).getTime() * 1000);
        String end = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(Long.valueOf(endTime) / 1000).getTime() * 1000);
        System.out.println(start);
        System.out.println(end);
        Long accessTime = Long.parseLong(endTime) - Long.parseLong(startTime);;
        System.out.println(accessTime/1000);

    }
}
