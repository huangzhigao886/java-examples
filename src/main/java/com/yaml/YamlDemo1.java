package com.yaml;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * @Auther: huangzhigao
 * @Date: 2020/3/23
 * @Description:
 */
public class YamlDemo1 {
    public static void main(String[] args) throws FileNotFoundException {
        File ymlFile = new File("D://demo1.yaml");
        Yaml yaml = new Yaml();
        Map<String, Object> map;
        map = (Map) yaml.load(new FileInputStream(ymlFile));

        String dump = yaml.dump(map);
        System.out.println("aaa");
    }
}
