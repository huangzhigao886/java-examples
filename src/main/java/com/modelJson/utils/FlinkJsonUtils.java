package com.modelJson.utils;

import com.modelJson.*;
import com.velocity.flinkJson.bean.HeaderBean;


import java.util.List;
import java.util.Map;

import static com.modelJson.enums.FlinkConstant.*;
import static com.modelJson.utils.ComponentUtils.buildSchema;
import static com.modelJson.utils.ComponentUtils.buildSourceParam;

/**
 * @Auther: huangzhigao
 * @Date: 2020/4/15
 * @Description:
 */
public class FlinkJsonUtils {


    /**
     * 构建Json头部
     *
     * @param modelBean
     * @return
     */
    public static HeaderBean buildHeader(ModelBean modelBean) {
        HeaderBean headerBean = new HeaderBean();
        headerBean.setJobName(modelBean.getModelName());
        if ("1".equals(modelBean.getRunType())) {
            headerBean.setJobType("BATCH");
        } else {
            headerBean.setJobType("STREAM");
        }
        headerBean.setOnErrorPolicy("SKIP");
        headerBean.setCheckPointInterval(0);
        return headerBean;
    }

    /**
     * 构建配置参数
     *
     * @param modelBean
     * @return
     */
    public static ConfigBean buildConfig(ModelBean modelBean) {
        ConfigBean configBean = new ConfigBean();
        //目前保存点都是记录在REDIS的
        configBean.setStoreType(REDIS);
        //缓存如果有规则转换，则选成REDIS
        configBean.setCacheType(HDFS);
        if (("3").equals(modelBean.getScheduleType())) {
            // 1：为任务为一次性或者周期全量
            configBean.setOverwrite(TRUE);
            configBean.setSourceSerial(FALSE);
        } else {
            //周期增量和流式
            configBean.setSourceSerial(TRUE);
            configBean.setOverwrite(FALSE);
        }
        return configBean;
    }

    /**
     * 生成Components的数据
     *
     * @return
     */
    public static ComponentDef buildComponent(ModelBean modelBean, Map<String, List<OperatorBean>> operatorMap) {
        ComponentDef componentDef = new ComponentDef();
        //构建字段信息
        buildSchema(modelBean.getOperators(), componentDef);
        //构建输入源的参数
        buildSourceParam(operatorMap.get("source"), componentDef);

        return componentDef;
    }


}
