package com.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.model.JsonBean;
import com.model.MetricBean;
import com.model.Student;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: hzg
 * @create: 2019-09-26 11:07
 **/

public class JsonTest {
//    public static void main(String[] args) {
////        String json ="{\"archive\":[{\"path\":\"/jobs/overview\",\"json\":[\"job\":\"id\"]}]}";
////        String json = "{\"arrchive\":\"12\"}";
////        String json = "{\"archive\":[{\"path\":\"/jobs/overview\",\"json\":{\"jobs\":[{\"status\":\"14\",\"start-time\":\"huang\",\"end-time\":\"12\"}]}},{\"id\":\"12\"}]}";
//        String json = "{\n" +
//                "\t\"archive\": [\n" +
//                "\t\t{\n" +
//                "\t\t\t\"path\": \"/jobs/overview\",\n" +
//                "\t\t\t\"json\": \"{\\\"jobs\\\":[{\\\"jid\\\":\\\"b119f74d5bb22100a6c3402ce550cacf\\\",\\\"name\\\":\\\"CSV\\\",\\\"state\\\":\\\"FINISHED\\\",\\\"start-time\\\":1568722465031,\\\"end-time\\\":1568722465654,\\\"duration\\\":623,\\\"last-modification\\\":1568722465654,\\\"tasks\\\":{\\\"total\\\":2,\\\"created\\\":0,\\\"scheduled\\\":0,\\\"deploying\\\":0,\\\"running\\\":0,\\\"finished\\\":2,\\\"canceling\\\":0,\\\"canceled\\\":0,\\\"failed\\\":0,\\\"reconciling\\\":0}}]}\"\n" +
//                "\t\t},\n" +
//                "\t\t{\n" +
//                "\t\t\t\"path\": \"/jobs/b119f74d5bb22100a6c3402ce550cacf/config\",\n" +
//                "\t\t\t\"json\": \"{\\\"jid\\\":\\\"b119f74d5bb22100a6c3402ce550cacf\\\",\\\"name\\\":\\\"CSV\\\",\\\"execution-config\\\":{\\\"execution-mode\\\":\\\"PIPELINED\\\",\\\"restart-strategy\\\":\\\"Cluster level default restart strategy\\\",\\\"job-parallelism\\\":1,\\\"object-reuse-mode\\\":false,\\\"user-config\\\":{}}}\"\n" +
//                "\t\t}]}";
//        JSONObject jsonObject = JSON.parseObject(json);
//
////        //测试特例版本
////        String str = jsonObject.getJSONArray("archive").get(0).toString();
////        JsonBean jsonBean = JSON.parseObject(str, JsonBean.class);
////        String tmp1 = jsonBean.getJson();
////        String jobs = JSON.parseObject(tmp1).getJSONArray("jobs").get(0).toString();
////        Map<String,String> map = JSON.parseObject(jobs,Map.class);
////        System.out.println(jobs);
//
//        //随机测试
//        JSONArray archive = jsonObject.getJSONArray("archive");
//        int index;
//        JsonBean jsonBean = null;
//        for (int i = 0; i < archive.size(); i++) {
//            jsonBean = JSON.parseObject(archive.get(i).toString(), JsonBean.class);
//            if("/jobs/overview".equals(jsonBean.getPath())){
//                break;
//            }
//        }
//        String jobStatus = JSON.parseObject(jsonBean.getJson()).getJSONArray("jobs").get(0).toString();
//        Map<String,Object> jobStatusMap = JSON.parseObject(jobStatus,Map.class);
//        String state = jobStatusMap.get("state").toString();
//        Long startTime = Long.valueOf(String.valueOf(jobStatusMap.get("start-time")));
//        Long endTime = Long.valueOf(String.valueOf(jobStatusMap.get("end-time")));
//
//        System.out.println("状态："+state);
//        System.out.println("开始时间："+startTime);
//        System.out.println("结束时间："+endTime);
//    }

    public static String getStatus(String json){
        JSONObject jsonObject = JSON.parseObject(json);
        JSONArray archive = jsonObject.getJSONArray("archive");
        int index;
        JsonBean jsonBean = null;
        for (int i = 0; i < archive.size(); i++) {
            jsonBean = JSON.parseObject(archive.get(i).toString(), JsonBean.class);
            if("/jobs/overview".equals(jsonBean.getPath())){
                break;
            }
        }
        String jobStatus = JSON.parseObject(jsonBean.getJson()).getJSONArray("jobs").get(0).toString();
        Map<String,Object> jobStatusMap = JSON.parseObject(jobStatus,Map.class);
        String state = jobStatusMap.get("state").toString();
        Long startTime = Long.valueOf(String.valueOf(jobStatusMap.get("start-time")));
        Long endTime = Long.valueOf(String.valueOf(jobStatusMap.get("end-time")));
        return state+","+startTime+","+endTime;
    }

    public static String testReadLines() throws IOException {
        String filename="F:"+File.separator+"metric.json";
        File f=new File(filename);
        Reader input=new FileReader(f);
        List readLines = IOUtils.readLines(input);
        StringBuilder stringBuilder = new StringBuilder();
        for (Object object: readLines) {
            stringBuilder.append(object.toString());
        }
        IOUtils.closeQuietly(input);
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws IOException {
//        String res = testReadLines();
//        System.out.println(getStatus(res));
        getMetricDate();
//        getJson();
//        File file = new File("1.txt");


    }

    public static Map getMetricDate() throws IOException {
        String res = testReadLines();
        HashMap<String,Object> map =  (HashMap<String,Object> )JSON.parseObject(res,Map.class);
        Map<String,String> metric = (Map<String,String>)map.get("metrics");

        return null;

    }


    public static String getJson(){
        Gson gson = new Gson();
        Student s = new Student(11L,"name",15,"address");
        String s1 = gson.toJson(s);
        System.out.println(s1);
        return null;

    }
}
