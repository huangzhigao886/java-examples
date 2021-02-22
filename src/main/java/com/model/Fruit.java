package com.model;

import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2019/11/29
 * @Description:
 */
@Data
@AllArgsConstructor
public class Fruit {
    private String color;
    private String size;

    public static void main(String[] args) {
        Fruit fruit = new Fruit("aa","bb");
        Fruit frui1t = new Fruit("a1a","bb1");
        List<Fruit> list = new ArrayList<>();
        list.add(frui1t);
        list.add(fruit);
        String s = JSONUtil.toJsonStr(list);
        System.out.println(s);

    }
}
