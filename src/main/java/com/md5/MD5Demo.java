package com.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import static io.netty.util.internal.StringUtil.byteToHexString;

/**
 * @Auther: huangzhigao
 * @Date: 2020/3/10
 * @Description:
 */
public class MD5Demo {
    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        try {
//            MessageDigest messageDigest= MessageDigest.getInstance("MD5");
//            System.out.println(byteArrayToHexString(messageDigest.digest("baidu.com".getBytes())));
//        } catch (NoSuchAlgorithmException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }




    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }
}
