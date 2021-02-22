//package com.velocity.test;
//
//import com.velocity.BetterBean.*;
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
//public class BetterTest {
//    public static void main(String[] args) {
//        Map<String, Object> map = new HashMap<>();
//        JoinTaskInfo joinTaskInfo = new JoinTaskInfo();
//        joinTaskInfo.setJobName("mulTableJoin");
//        joinTaskInfo.setJobType("BATCH");
//
//
////        //componentMap
////        getSchemaList(joinTaskInfo);
////        getJdbcSourceParamList(joinTaskInfo);
////
//        //SOURCE MAP
//        getSourceInputFormatList(joinTaskInfo);
////        getImpalaSourceList(joinJobBean);
////
////
////        //OPERATOR MAP
////        getJoinOperator(joinTaskInfo);
////        getMapOperator(joinTaskInfo);
////
////
////        //sinkMap
//////        getSinkMap(joinJobBean);
////        getImpalaSinkMap(joinTaskInfo);
////
////
////        //getStream
////        getStreamMap(joinTaskInfo);
//
//        map.put("taskinfo", joinTaskInfo);
//        String flinkJson = Demo.getFlinkJson(map);
//        System.out.println(flinkJson);
//    }
//
//
//    public static void getSchemaList(JoinTaskInfo joinJobBean) {
//        List<JoinSchemaBean> schemaBeanList = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            JoinSchemaBean schemaBean = new JoinSchemaBean();
//            schemaBean.setId("sourceSchema" + i);
//            List<JoinDataField> dataFieldList = new ArrayList<>();
//            dataFieldList.add(new JoinDataField("name" + i, "STRING"));
//            dataFieldList.add(new JoinDataField("age" + i, "INT"));
//            schemaBean.setDataFieldList(dataFieldList);
//            schemaBeanList.add(schemaBean);
//        }
//        joinJobBean.setSchemaList(schemaBeanList);
//    }
//
//
//    //    public static void getJdbcSourceParamList(JoinTaskInfo joinJobBean) {
////        List<JdbcSourceParam> jdbcSourceParamList = new ArrayList<>();
////        for (int i = 0; i < 3; i++) {
////            JdbcSourceParam jdbcSourceParam = new JdbcSourceParam();
////            jdbcSourceParam.setId("sourceParam" + i);
////            List<Object> constructArgsList = new ArrayList<>();
////            constructArgsList.add("root");
////            constructArgsList.add("123456");
////            constructArgsList.add("MySql");
////            constructArgsList.add("jdbc:mysql://url:3306/enc_daas_access?useCursorFetch=true&defaultFetchSize=1000");
////            constructArgsList.add("table" + i);
////            constructArgsList.add("");
////            constructArgsList.add("");
////            constructArgsList.add("");
////            jdbcSourceParam.setConstructArgsList(constructArgsList);
////            jdbcSourceParamList.add(jdbcSourceParam);
////        }
////        joinJobBean.setJdbcSourceParamList(jdbcSourceParamList);
////    }
////
////
//    public static void getSourceInputFormatList(JoinTaskInfo joinJobBean) {
//        List<JoinBeanDef> list = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            JoinBeanDef joinBeanDef = new JoinBeanDef();
//            joinBeanDef.setClassName("jdbc");
//            joinBeanDef.setId("source" + i);
//            List<Object> constructorList = new ArrayList<>();
//            constructorList.add("aa");
//            constructorList.add("bb");
//            joinBeanDef.setConstructArgsList(constructorList);
//            JoinConstructMethodDef joinConstructMethodDef = new JoinConstructMethodDef();
//            joinConstructMethodDef.setTableName("tablesss");
//            joinConstructMethodDef.setRegion("region");
//            List<Object> PK = new ArrayList<>();
//            PK.add("11");
//            PK.add("22");
//            joinConstructMethodDef.setPkFields(PK);
////            joinBeanDef.setJoinConstructMethodDef(joinConstructMethodDef);
//            list.add(joinBeanDef);
//        }
//        joinJobBean.setSourceList(list);
//    }
////
////
////    private static void getImpalaSourceList(JoinJobBean joinJobBean) {
////        List<ImpalaSource> list = new ArrayList<>();
////        for (int i = 0; i < 3; i++) {
////            ImpalaSource impalaSource = new ImpalaSource();
////            impalaSource.setId("impalaSource" + i);
////            List<Object> constructList = new ArrayList<>();
////            constructList.add("hdfs:1000");
////            constructList.add("ref:schema" + i);
////            impalaSource.setConstructArgsList(constructList);
////            List<Object> methodList = new ArrayList<>();
////            impalaSource.setConfigMethodsList(methodList);
////            list.add(impalaSource);
////        }
////        joinJobBean.setImpalaSourceList(list);
////    }
////
////
////    private static void getJoinOperator(JoinTaskInfo joinJobBean) {
////        List<JoinOperatorBean> list = new ArrayList<>();
////        for (int i = 0; i < 3; i++) {
////            JoinOperatorBean joinOperatorBean = new JoinOperatorBean();
////            joinOperatorBean.setId("join" + i);
////            List<Object> conList = new ArrayList<>();
////            conList.add("LEFT_JOIN");
////            joinOperatorBean.setConstructArgsList(conList);
////            list.add(joinOperatorBean);
////        }
////        joinJobBean.setJoinOperatorList(list);
////    }
////
////
////    private static void getMapOperator(JoinTaskInfo joinJobBean) {
////        List<MapOperatorBean> list = new ArrayList<>();
////        for (int i = 0; i < 3; i++) {
////            MapOperatorBean mapOperatorBean = new MapOperatorBean();
////            mapOperatorBean.setId("map" + i);
////            Map<String, String> map = new HashMap<>();
////            map.put("name", "huag");
////            map.put("id", "11");
////            mapOperatorBean.setConstructArgsMap(map);
////            list.add(mapOperatorBean);
////        }
////        joinJobBean.setMapOperatorList(list);
////    }
////
////
////    private static void getSinkMap(JoinJobBean joinJobBean) {
////        JdbcSinkBean jdbcSinkBean = new JdbcSinkBean();
////        jdbcSinkBean.setId("jdbcSink");
////        List<Object> list = new ArrayList<>();
////        list.add("ref:sinkRef");
////        jdbcSinkBean.setConstructArgsList(list);
////        joinJobBean.setJdbcSinkBean(jdbcSinkBean);
////    }
////
////
////    private static void getImpalaSinkMap(JoinTaskInfo joinJobBean) {
////        ImpalaSinkBean impalaSinkBean = new ImpalaSinkBean();
////        impalaSinkBean.setId("impalaSink");
////        List<Object> list = new ArrayList<>();
////        list.add("hdfs");
////        impalaSinkBean.setConstructArgsList(list);
////        joinJobBean.setImpalaSinkBean(impalaSinkBean);
////    }
////
////    private static void getStreamMap(JoinTaskInfo joinJobBean) {
////        List<StreamDef> streamDefList = new ArrayList<>();
////        for (int i = 0; i < 6; i++) {
////            StreamDef streamDef = new StreamDef();
////            streamDef.setName("join" + i);
////            if (i == 0) {
////                streamDef.setFrom("source1");
////                streamDef.setTo("join1");
////                streamDef.setOperation("JOIN");
////                streamDef.setJoinSide("LEFT");
////                streamDef.setJoinKeys("id" + i);
////                streamDef.setTableName("table" + i);
////            } else if (i == 1) {
////                streamDef.setFrom("source2");
////                streamDef.setTo("join1");
////                streamDef.setJoinSide("RIGHT");
////                streamDef.setJoinKeys("id" + i);
////                streamDef.setOperation("JOIN");
////                streamDef.setTableName("table" + i);
////            } else if (i == 2) {
////                streamDef.setFrom("join1");
////                streamDef.setTo("join2");
////                streamDef.setJoinSide("LEFT");
////                streamDef.setOperation("JOIN");
////                streamDef.setJoinKeys("id" + i);
////            } else if (i == 3) {
////                streamDef.setFrom("souce3");
////                streamDef.setTo("join2");
////                streamDef.setJoinSide("RIGHT");
////                streamDef.setOperation("JOIN");
////                streamDef.setJoinKeys("id" + i);
////            } else if (i == 4) {
////                streamDef.setName("map");
////                streamDef.setFrom("join2");
////                streamDef.setTo("map");
////                streamDef.setOperation("MAP");
////            } else {
////                streamDef.setName("sink");
////                streamDef.setFrom("map");
////                streamDef.setTo("mysql");
////            }
////            streamDefList.add(streamDef);
////        }
////        joinJobBean.setStreamList(streamDefList);
////    }
//
//
//}
