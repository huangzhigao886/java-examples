package com.common;

import cn.hutool.core.date.DateUtil;

/**
 * @Auther: huangzhigao
 * @Date: 2020/10/15
 * @Description:
 */
public class TimeCompare {
    public static void main(String[] args) {
        String endTime = "2020-12-22 10:15:11";
        String now = DateUtil.now();
        System.out.println(endTime.compareTo(now));
    }
}
