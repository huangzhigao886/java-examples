package com.compare;


import cn.hutool.core.util.RandomUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2020/8/23
 * @Description:
 */
public class AllTypeCompare {
    public static void main(String[] args) {
        Date date = new Date();
        String s = "aa";
        Timestamp timestamp = new Timestamp(100);
        System.out.println(timestamp instanceof Comparable);

        List<Record> values = new ArrayList<>();
        for (int h = 0; h < 10; h++) {
            Record record = new Record(4);
            Object[] o = new Object[4];
            for (int i = 0; i < 4; i++) {
                if (i == 0) {
                    o[i] = h;
                } else if (i == 1) {
                    o[i] = h - 4;
                } else if (i == 2) {
                    o[i] = RandomUtil.randomString(3);
                } else {
                    o[i] = 5;
                }
            }
            record.setValues(o);
            values.add(record);
        }
        Collections.sort(values);
        for(Record record:values){
            System.out.println(record);
        }
        System.out.println();
    }
}
