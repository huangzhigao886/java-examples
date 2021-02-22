package com.java.json.dto;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.JsonArray;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.avro.data.Json;

import java.util.List;
import java.util.Map;

/**
 * @Auther: huangzhigao
 * @Date: 2019/11/27
 * @Description:
 */
@Data
@AllArgsConstructor
public class Apple {
    private String color;
    private String weight;
    private String aa;

    public static void main(String[] args) {
       String  ss = "[{\"key\":\"path\",\"value\":\"/mnt/data/deploy/daas-push-test/jsonzhy_1.txt\"}]";
        List<Map> list = JSONArray.parseArray(ss,Map.class);
        for(int i = 0 ;i<list.size();i++){
            System.out.println(list.get(i).get("key"));
            System.out.println(list.get(i).get("value"));
        }
    }
}
