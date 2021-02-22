//package com.velocity;
//
//import com.dto.*;
//import com.enums.DataBaseType;
//import org.apache.velocity.Template;
//import org.apache.velocity.VelocityContext;
//import org.apache.velocity.app.VelocityEngine;
//import org.apache.velocity.runtime.RuntimeConstants;
//import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
//
//import java.io.StringWriter;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @Auther: huangzhigao
// * @Date: 2019/12/17
// * @Description:
// */
//public class Demo {
//
//    public static String getFlinkJson(Map<String, Object> map) {
//        VelocityEngine engine = new VelocityEngine();
//        engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
//        engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
//        engine.init();
//        Template template = engine.getTemplate("/vm/joinTask.vm");
//        VelocityContext ctx = new VelocityContext();
//        for (Map.Entry<String, Object> entry : map.entrySet()) {
//            ctx.put(entry.getKey(), entry.getValue());
//        }
//        StringWriter stringWriter = new StringWriter();
//        template.merge(ctx, stringWriter);
//        return stringWriter.toString();
//    }
//
//
//    public static void main(String[] args) {
//        Map<String, Object> map = new HashMap<>();
//        TaskInfo taskInfo = new TaskInfo();
//        taskInfo.setJobName("test");
//        taskInfo.setJobType("BATCH");
//
//
//        //schema List
//        List<Schema> schemaList = new ArrayList<>();
//
//
//        Schema schema = new Schema();
//        schema.setId("test");
//        schema.setClassName("com.shcema");
//        List<DataField> dataFields = new ArrayList<>();
//        dataFields.add(new DataField("id", "INT"));
//        dataFields.add(new DataField("name", "STRING"));
//        schema.setDataFields(dataFields);
//        schemaList.add(schema);
//
//
//        Schema schema1 = new Schema();
//        schema1.setId("test1");
//        schema1.setClassName("com.shcema");
//        List<DataField> dataFields1 = new ArrayList<>();
//        dataFields1.add(new DataField("id1", "INT"));
//        dataFields1.add(new DataField("name1", "STRING"));
//        schema1.setDataFields(dataFields1);
//        schemaList.add(schema1);
//
//        taskInfo.setSchemaList(schemaList);
//
//
//
//
//        //sourceList
//        List<JdbcDto> soureParamList = new ArrayList<>();
//        JdbcDto jdbcDto = new JdbcDto();
//        jdbcDto.setId("sourceParma1");
//        jdbcDto.setClassName("com.soureparma");
//        soureParamList.add(jdbcDto);
//
//        JdbcDto jdbcDto1 = new JdbcDto();
//        jdbcDto1.setId("sourceParma1");
//        jdbcDto1.setClassName("com.soureparma");
//        soureParamList.add(jdbcDto1);
//
//        List<JdbcSourceDto> jdbcSourceDtoList = new ArrayList<>();
//        JdbcSourceDto jdbcSourceDto = new JdbcSourceDto();
//        jdbcSourceDto.setClassName("com.mysqlSourceFormat");
//        jdbcSourceDto.setId("mysql1");
//        List<String> configureArg = new ArrayList<>();
//        configureArg.add("ref:saa");
//        configureArg.add("ref:bbb");
//        jdbcSourceDto.setConstructorArgs(configureArg);
//        jdbcSourceDtoList.add(jdbcSourceDto);
//        taskInfo.setJdbcSourceList(jdbcSourceDtoList);
//
//
////        taskInfo.setJdbcSourParam(soureParamList);
//
//        map.put("taskinfo", taskInfo);
////        map.put("jobName","ds");
//        String flinkJson = getFlinkJson(map);
//        System.out.println(flinkJson);
//
////    }
//}
