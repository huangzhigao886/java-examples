package com.velocity.Bean;

import lombok.Data;

import java.io.File;
import java.io.FileReader;
import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/18
 * @Description:
 */
@Data
public class ImpalaSinkBean {
    private String id;
    private String className;
    private List<Object> constructArgsList;
    private List<Object> configMethodsList;

    public static void main(String[] args) {
//        FileReader fileReader = new FileReader(new File("D://"));
    }
}
