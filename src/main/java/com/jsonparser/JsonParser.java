package com.jsonparser;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2020/2/6
 * @Description:
 */
public class JsonParser {
    public static void main(String[] args) throws IOException {
        List<String> list = IOUtils.readLines(new FileInputStream("D://jc.json"));
        String json = String.join("\n", list);
        json = "[" + json + "]";
        Gson gson = new Gson();

        JsonArray jsonArray = gson.fromJson(json, JsonArray.class);
        for(int i = 0;i<jsonArray.size();i++){
            Object document = Configuration.defaultConfiguration().jsonProvider().parse(jsonArray.get(i).toString());
            String moudleId = JsonPath.read(document, "$.moudleId").toString();
            String moudleName = JsonPath.read(document,"$.moudleName").toString();
            String time = JsonPath.read(document, "$.subAreaId").toString();
            Object flow = JsonPath.read(document, "$.data.*.flow");
            System.out.println(flow instanceof JSONArray);
            List<JSONArray> jsonArrays = Arrays.asList((JSONArray) flow);
            Object obj= ((JSONArray) flow).get(0);
            Object pressure = JsonPath.read(document,"$.data.*.pressure").toString();
        }


//        System.out.println(checkpoint_num);
//        System.out.println(time);
//        System.out.println(flow);
//        List<String> list3 = new ArrayList<>();
//        if (flow.startsWith("[") && flow.endsWith("]")) {
//            String[] split = flow.substring(1, flow.length() - 1).split(",");
//            list3 = Arrays.asList(split);
//        }
//        List list2 = Arrays.asList(time);
//        List list1 = Arrays.asList(checkpoint_num);
//        List<List> record = new ArrayList<>();
//        record.add(list1);
//        record.add(list2);
//        record.add(list3);


    }
}
