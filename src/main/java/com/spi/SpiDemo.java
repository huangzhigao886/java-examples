package com.spi;

import java.util.ServiceLoader;

/**
 * @Auther: huangzhigao
 * @Date: 2020/9/29
 * @Description:
 */
public class SpiDemo {
    public static void main(String[] args) {
        ServiceLoader<UploadUdf> load = ServiceLoader.load(UploadUdf.class);
        for (UploadUdf uploadUdf : load) {
            uploadUdf.update();
        }
    }
}
