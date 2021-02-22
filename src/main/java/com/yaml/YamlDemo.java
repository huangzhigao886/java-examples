package com.yaml;

import cn.hutool.core.io.file.FileReader;
import com.alibaba.fastjson.JSON;
import com.model.Apple;
import com.util.HashMapValue;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/5
 * @Description:
 */
public class YamlDemo {
    public static void main(String[] args) {
//        Yaml yaml = new Yaml();
//        Apple apple = new Apple();
//        Map<String,String> map = new HashMap();
//        map.put("aa","bb");
//        map.put("name","zhangsan");
//        apple.setMap(map);
//        String dump = yaml.dump(apple);
//        System.out.println(dump);
//
//
//        System.out.println(Long.parseLong("10000"));


        FileReader fileReader = new FileReader(new File("D://8063.json"));
        List<String> list = fileReader.readLines();
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : list) {
            stringBuilder.append(str);
        }
        String s = stringBuilder.toString();
        Map map = (Map) JSON.parse(s);
        map.put("name", "zhangsan");
        System.out.println(JSON.toJSONString(map));
        System.out.println(args);
    }
}
