package com.linux;

import cn.hutool.json.JSONUtil;
import com.model.Apple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @Auther: huangzhigao
 * @Date: 2020/9/29
 * @Description:
 */
public class Demo1 {
    public static void main(String[] args) throws IOException {
        List<Apple> list= new ArrayList<>();
        list.add(new Apple("cc","aa"));
        list.add(new Apple("dd","ee"));
        list.forEach(a->{
            if(a.getColor().equals("cc")){
                a.setSex("hh");
            }else{
                a.setSex("yy");
            }
            a.setAge(String.valueOf(System.currentTimeMillis()));
        });

        System.out.println(JSONUtil.toJsonStr(list));
    }
}
