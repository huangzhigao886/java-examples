package com.model;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @Auther: huangzhigao
 * @Date: 2019/11/29
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apple {
    private String color;
    private String sex;
    private String age;

    public Apple(String color, String sex) {
        this.color = color;
        this.sex = sex;
    }

}
