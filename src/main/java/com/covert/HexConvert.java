package com.covert;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.CharsetUtil;

/**
 * @Auther: huangzhigao
 * @Date: 2019/11/25
 * @Description:
 */
public class HexConvert {
    public static void main(String[] args) {
        String str = "我是一直乃哦";
        String hexStr = Convert.toHex(str, CharsetUtil.CHARSET_UTF_8);
        System.out.println(hexStr);
        String regionValue = Convert.hexToStr(hexStr, CharsetUtil.CHARSET_UTF_8);
        System.out.println(regionValue);
    }
}
