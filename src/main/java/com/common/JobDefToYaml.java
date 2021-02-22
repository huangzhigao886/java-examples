package com.common;

import com.enums.DataBaseType;
import com.enums.DataType;
import com.enums.JobType;
import com.enums.Operation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 生成Flink任务图的Json文件
 * @author: hzg
 * @create: 2019-10-11 10:08
 **/

public class JobDefToYaml {
    public static void main(String[] args) {
        JobDef jobDef = new JobDef();
        jobDef.setName("SimpleJob");
        jobDef.setJobType(JobType.BATCH);
        List<BeanDef> components = new ArrayList<>();
        //第一个compnet
        BeanDef beanDef = new BeanDef();
        beanDef.setId("jdbcParam");
        beanDef.setClassName("cn.encdata.cloud.dataengine.core.sources.batch.jdbc.ebrJdbcSourceInputParam");
        List<Object> constructorArgs1 = new ArrayList<>();
        constructorArgs1.add("root");
        constructorArgs1.add("Qm9jb21fMTIz");
        constructorArgs1.add(DataBaseType.MySql);
        constructorArgs1.add("jdbc:mysql://url/test_pc_dam");
        constructorArgs1.add("test");
        constructorArgs1.add("");
        constructorArgs1.add("");
        constructorArgs1.add("");
        beanDef.setConstructorArgs(constructorArgs1);
        components.add(beanDef);
        jobDef.setComponents(components);
        //第二个Compent
        BeanDef beanDef2 = new BeanDef();
        beanDef2.setId("jdbcParamOut");
        beanDef2.setClassName("cn.encdata.cloud.dataengine.core.sinks.batch.jdbc.JdbcSinkOutputParam");
        List<Object> constructorArgs2 = new ArrayList<>();
        constructorArgs2.add("root");
        constructorArgs2.add("MTIzNDU2");
        constructorArgs2.add(DataBaseType.MySql);
        constructorArgs2.add("jdbc:mysql://url.50.242:3306/flink");
        constructorArgs2.add("test");
        constructorArgs2.add("");
        beanDef2.setConstructorArgs(constructorArgs2);
        components.add(beanDef2);
        jobDef.setComponents(components);


        //第三个Compent
        BeanDef beanDef3 = new BeanDef();
        beanDef3.setId("input");
        beanDef3.setClassName("cn.encdata.cloud.dataengine.schema.Schema");
        List<ConfigMethodDef> configMethods1 = new ArrayList<>();
        ConfigMethodDef configMethodDef = new ConfigMethodDef();
        configMethodDef.setName("addField");
        List<Object> methodArgs1 = new ArrayList<>();
        methodArgs1.add("NAME");
        methodArgs1.add(DataType.STRING);
        configMethodDef.setArgs(methodArgs1);
        configMethods1.add(configMethodDef);
        beanDef3.setConfigMethods(configMethods1);
        components.add(beanDef3);
        jobDef.setComponents(components);
        //第四个Compent
        BeanDef beanDef4 = new BeanDef();
        beanDef4.setId("output");
        beanDef4.setClassName("cn.encdata.cloud.dataengine.schema.Schema");
        ConfigMethodDef configMethodDef1 = new ConfigMethodDef();
        List<ConfigMethodDef> configMethods2 = new ArrayList<>();
        List<Object> methodArgs2 = new ArrayList<>();
        configMethodDef1.setName("addField");
        methodArgs2.add("NAME");
        methodArgs2.add(DataType.STRING);
        configMethodDef1.setArgs(methodArgs2);
        configMethods2.add(configMethodDef1);
        beanDef4.setConfigMethods(configMethods2);
        components.add(beanDef4);
        jobDef.setComponents(components);

        /**
         * source
         */
        List<SourceDef> sources = new ArrayList<>();
        SourceDef sourceDef = new SourceDef();
        sourceDef.setId("callData");
        sourceDef.setClassName("cn.encdata.cloud.dataengine.core.sources.batch.jdbc.JdbcSourceInputFormat");
        List<Object> conArgs = new ArrayList<>();
        conArgs.add("ref:jdbcParam");
        conArgs.add("ref:input");
        sourceDef.setConstructorArgs(conArgs);
        sourceDef.setParallelism(1);
        sources.add(sourceDef);
        jobDef.setSources(sources);


        /**
         * operators
         */
        List<OperatorDef> operators = new ArrayList<>();
        OperatorDef operatorDef = new OperatorDef();
        operatorDef.setId("filter");
        operatorDef.setClassName("cn.encdata.cloud.dataengine.core.operators.EncFilterFunction");
        List<Object> arg = new ArrayList<>();
        arg.add("NAME == huang");
        operatorDef.setConstructorArgs(arg);
        operatorDef.setParallelism(1);
        operators.add(operatorDef);
        jobDef.setOperators(operators);

        /**
         *sink
         */
        List<SinkDef> sinks = new ArrayList<>();
        SinkDef sinkDef = new SinkDef();
        sinkDef.setId("writeToMysql");
        sinkDef.setClassName("cn.encdata.cloud.dataengine.core.sinks.batch.jdbc.JdbcSinkOutputFormat");
        List<Object> list = new ArrayList<>();
        list.add("ref:jdbcParamOut");
        sinkDef.setConstructorArgs(list);
        sinkDef.setParallelism(1);
        sinks.add(sinkDef);
        jobDef.setSinks(sinks);


        /**
         * stream
         */
        List<StreamDef> streams = new ArrayList<>();
        StreamDef streamDef1 = new StreamDef();
        streamDef1.setName("sourceToFilter");
        streamDef1.setFrom("callData");
        streamDef1.setTo("filter");
        streamDef1.setOperation(Operation.FILTER);
        streams.add(streamDef1);
        StreamDef streamDef2 = new StreamDef();
        streamDef2.setName("filterToMysql");
        streamDef2.setFrom("filter");
        streamDef2.setTo("writeToMysql");
        streams.add(streamDef2);
        jobDef.setStreams(streams);


        Gson gs = new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();

        String s = gs.toJson(jobDef);
        System.out.println(s);

    }

}
