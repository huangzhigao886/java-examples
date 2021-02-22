package com.java.clazz;

import com.model.Apple;
import com.model.Fruit;

import java.io.File;
import java.nio.file.Path;

/**
 * @Auther: huangzhigao
 * @Date: 2019/11/29
 * @Description: 检车instance of方法
 */
public class InstanceTest {
    public static void main(String[] args) {

        String[] list=new File("D://Tmp").list();

        File file = new File("D://cc.txt");
        file.renameTo(new File("D://黄纸高.txt"));
    }
}
