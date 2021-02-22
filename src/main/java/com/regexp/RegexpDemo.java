package com.regexp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: huangzhigao
 * @Date: 2020/5/17
 * @Description:
 */
public class RegexpDemo {


    /**
     * 校验手机号码
     */
    public static void validatePhone() {
        Pattern MOBILE_PHONE_PATTERN = Pattern.compile("^[1](([3|5|8][\\d])|([4][5,6,7,8,9])|([6][5,6])|([7][3,4,5,6,7,8])|([9][8,9]))[\\d]{8}$");
        Matcher matcher = MOBILE_PHONE_PATTERN.matcher("134996210911");
        System.out.println(matcher.find());
    }


    public static void validateID() {
        Pattern ID = Pattern.compile("^((25[0-5]|2[0-4]\\d|[1]{1}\\d{1}\\d{1}|[1-9]{1}\\d{1}|\\d{1})($|(?!\\.$)\\.)){4}$");
        Matcher matcher = ID.matcher("10.11.21.212");
        System.out.println(matcher.find());
    }

    @Test
    public void testRegexGroup() {
        Pattern compile = Pattern.compile("(\\d{18}).+(\\d{18})");
        Matcher matcher = compile.matcher("XXX再那个地方，依法网吧进行检查，检查发现黄XX（身份证号码：3199333213112332189），MM319933321311233218等提取身份证号码");
        if (matcher.find()) {
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
        }
    }


    @Test
    public void test11() {
        String a = "13213123";
        List<String> digitList = new ArrayList<String>();
        Pattern p = Pattern.compile("[0-9]");
        Matcher m = p.matcher(a);
        boolean b = m.find();

        String result = m.replaceAll("");
        for (int i = 0; i < result.length(); i++) {
            digitList.add(result.substring(i, i + 1));

        }
    }
    public static void main(String[] args) {
        TreeSet treeSet = new TreeSet();
    }
}
