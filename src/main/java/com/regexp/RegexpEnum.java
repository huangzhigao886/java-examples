package com.regexp;

/**
 * 正则表达式匹配规则
 *
 * @Auther: huangzhigao
 * @Date: 2020/5/17
 * @Description:
 */
public enum RegexpEnum {

    MOBILE_PHONE_PATTERN("02","^[1](([3|5|8][\\d])|([4][5,6,7,8,9])|([6][5,6])|([7][3,4,5,6,7,8])|([9][8,9]))[\\d]{8}$");



    private String regexpNum;

    private String regexpContent;

    RegexpEnum(String regexpNum, String regexpContent) {
        this.regexpNum = regexpNum;
        this.regexpContent = regexpContent;
    }
}
