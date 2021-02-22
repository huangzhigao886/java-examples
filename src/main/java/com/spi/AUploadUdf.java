package com.spi;

/**
 * @Auther: huangzhigao
 * @Date: 2020/9/29
 * @Description:
 */
public class AUploadUdf implements UploadUdf {
    @Override
    public void update() {

        System.out.println("aaa");
    }

    public static void main(String[] args) {
        System.out.println(10093%6);
    }
}
