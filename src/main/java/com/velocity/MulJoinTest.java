//package com.velocity;
//
//import com.velocity.Bean.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @Auther: huangzhigao
// * @Date: 2019/12/18
// * @Description:
// */
//public class MulJoinTest {
//    public static void main(String[] args) {
//        Map<String, Object> map = new HashMap<>();
//        JoinJobBean joinJobBean = new JoinJobBean();
//        joinJobBean.setJobName("mulTableJoin");
//        joinJobBean.setJobType("BATCH");
//
//        //componentMap
//        getSchemaList(joinJobBean);
//        getJdbcSourceParamList(joinJobBean);
//
//        //SOURCE MAP
//        getSourceInputFormatList(joinJobBean);
////        getImpalaSourceList(joinJobBean);
//
//
//        //OPERATOR MAP
//        getJoinOperator(joinJobBean);
//        getMapOperator(joinJobBean);
//
//
//        //sinkMap
////        getSinkMap(joinJobBean);
//        getImpalaSinkMap(joinJobBean);
//
//
//        //getStream
//        getStreamMap(joinJobBean);
//
//        map.put("taskinfo", joinJobBean);
//        String flinkJson = Demo.getFlinkJson(map);
//        System.out.println(flinkJson);
//    }
//
//
//    public static void getSchemaList(JoinJobBean joinJobBean) {
//        List<SchemaBean> schemaBeanList = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            SchemaBean schemaBean = new SchemaBean();
//            schemaBean.setId("sourceSchema" + i);
//            List<DataField> dataFieldList = new ArrayList<>();
//            dataFieldList.add(new DataField("name" + i, "STRING"));
//            dataFieldList.add(new DataField("age" + i, "INT"));
//            schemaBean.setDataFieldList(dataFieldList);
//            schemaBeanList.add(schemaBean);
//        }
//        joinJobBean.setSchemaList(schemaBeanList);
//    }
//
//
//    public static void getJdbcSourceParamList(JoinJobBean joinJobBean) {
//        List<JdbcSourceParam> jdbcSourceParamList = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            JdbcSourceParam jdbcSourceParam = new JdbcSourceParam();
//            jdbcSourceParam.setId("sourceParam" + i);
//            List<Object> constructArgsList = new ArrayList<>();
//            constructArgsList.add("root");
//            constructArgsList.add("123456");
//            constructArgsList.add("MySql");
//            constructArgsList.add("jdbc:mysql://url:3306/enc_daas_access?useCursorFetch=true&defaultFetchSize=1000");
//            constructArgsList.add("table" + i);
//            constructArgsList.add("");
//            constructArgsList.add("");
//            constructArgsList.add("");
//            jdbcSourceParam.setConstructArgsList(constructArgsList);
//            jdbcSourceParamList.add(jdbcSourceParam);
//        }
//        joinJobBean.setJdbcSourceParamList(jdbcSourceParamList);
//    }
//
//
//    public static void getSourceInputFormatList(JoinJobBean joinJobBean) {
//        List<JdbcSourceInputFormat> list = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            JdbcSourceInputFormat jdbcSourceInputFormat = new JdbcSourceInputFormat();
//            jdbcSourceInputFormat.setId("mysqlSource" + i);
//            List<Object> constructList = new ArrayList<>();
//            constructList.add("ref:source" + i);
//            jdbcSourceInputFormat.setConstructArgsList(constructList);
//            List<Object> methodList = new ArrayList<>();
//            jdbcSourceInputFormat.setConfigMethodsList(methodList);
//            list.add(jdbcSourceInputFormat);
//        }
//        joinJobBean.setJdbcSourceInputFormatList(list);
//    }
//
//
//    private static void getImpalaSourceList(JoinJobBean joinJobBean) {
//        List<ImpalaSource> list = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            ImpalaSource impalaSource = new ImpalaSource();
//            impalaSource.setId("impalaSource" + i);
//            List<Object> constructList = new ArrayList<>();
//            constructList.add("hdfs:1000");
//            constructList.add("ref:schema" + i);
//            impalaSource.setConstructArgsList(constructList);
//            List<Object> methodList = new ArrayList<>();
//            impalaSource.setConfigMethodsList(methodList);
//            list.add(impalaSource);
//        }
//        joinJobBean.setImpalaSourceList(list);
//    }
//
//
//    private static void getJoinOperator(JoinJobBean joinJobBean) {
//        List<JoinOperatorBean> list = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            JoinOperatorBean joinOperatorBean = new JoinOperatorBean();
//            joinOperatorBean.setId("join" + i);
//            List<Object> conList = new ArrayList<>();
//            conList.add("LEFT_JOIN");
//            joinOperatorBean.setConstructArgsList(conList);
//            list.add(joinOperatorBean);
//        }
//        joinJobBean.setJoinOperatorList(list);
//    }
//
//
//    private static void getMapOperator(JoinJobBean joinJobBean) {
//        List<MapOperatorBean> list = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            MapOperatorBean mapOperatorBean = new MapOperatorBean();
//            mapOperatorBean.setId("map" + i);
//            Map<String, String> map = new HashMap<>();
//            map.put("name", "huag");
//            map.put("id", "11");
//            mapOperatorBean.setConstructArgsMap(map);
//            list.add(mapOperatorBean);
//        }
//        joinJobBean.setMapOperatorList(list);
//    }
//
//
//    private static void getSinkMap(JoinJobBean joinJobBean) {
//        JdbcSinkBean jdbcSinkBean = new JdbcSinkBean();
//        jdbcSinkBean.setId("jdbcSink");
//        List<Object> list = new ArrayList<>();
//        list.add("ref:sinkRef");
//        jdbcSinkBean.setConstructArgsList(list);
//        joinJobBean.setJdbcSinkBean(jdbcSinkBean);
//    }
//
//
//    private static void getImpalaSinkMap(JoinJobBean joinJobBean) {
//        ImpalaSinkBean impalaSinkBean = new ImpalaSinkBean();
//        impalaSinkBean.setId("impalaSink");
//        List<Object> list = new ArrayList<>();
//        list.add("hdfs");
//        impalaSinkBean.setConstructArgsList(list);
//        joinJobBean.setImpalaSinkBean(impalaSinkBean);
//    }
//
//    private static void getStreamMap(JoinJobBean joinJobBean) {
//        List<StreamDef> streamDefList = new ArrayList<>();
//        for (int i = 0; i < 6; i++) {
//            StreamDef streamDef = new StreamDef();
//            streamDef.setName("join" + i);
//            if (i == 0) {
//                streamDef.setFrom("source1");
//                streamDef.setTo("join1");
//                streamDef.setOperation("JOIN");
//                streamDef.setJoinSide("LEFT");
//                streamDef.setJoinKeys("id" + i);
//                streamDef.setTableName("table" + i);
//            } else if (i == 1) {
//                streamDef.setFrom("source2");
//                streamDef.setTo("join1");
//                streamDef.setJoinSide("RIGHT");
//                streamDef.setJoinKeys("id" + i);
//                streamDef.setOperation("JOIN");
//                streamDef.setTableName("table" + i);
//            } else if (i == 2) {
//                streamDef.setFrom("join1");
//                streamDef.setTo("join2");
//                streamDef.setJoinSide("LEFT");
//                streamDef.setOperation("JOIN");
//                streamDef.setJoinKeys("id" + i);
//            } else if (i == 3) {
//                streamDef.setFrom("souce3");
//                streamDef.setTo("join2");
//                streamDef.setJoinSide("RIGHT");
//                streamDef.setOperation("JOIN");
//                streamDef.setJoinKeys("id" + i);
//            } else if (i == 4) {
//                streamDef.setName("map");
//                streamDef.setFrom("join2");
//                streamDef.setTo("map");
//                streamDef.setOperation("MAP");
//            } else {
//                streamDef.setName("sink");
//                streamDef.setFrom("map");
//                streamDef.setTo("mysql");
//            }
//            streamDefList.add(streamDef);
//        }
//        joinJobBean.setStreamList(streamDefList);
//    }
//
//
//}
