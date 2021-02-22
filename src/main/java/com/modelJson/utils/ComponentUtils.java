package com.modelJson.utils;

import com.modelJson.*;
import com.modelJson.enums.FlinkConstant;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.modelJson.enums.FlinkConstant.*;

/**
 * @Auther: huangzhigao
 * @Date: 2020/4/16
 * @Description:
 */
public class ComponentUtils {

    /**
     * 构建schema
     *
     * @param operatorBeans
     * @param componentDef
     */
    public static void buildSchema(List<OperatorBean> operatorBeans, ComponentDef componentDef) {
        List<SchemaDef> schemaDefList = new ArrayList<>();
        for (OperatorBean operatorBean : operatorBeans) {
            String operatorType = operatorBean.getOperatorType();
            if (SOURCE.equalsIgnoreCase(operatorBean.getOperatorType()) || FlinkConstant.changeStructOperatorList.contains(operatorType)) {
                //针对source和改变数据结构的算子，需要将字段信息添加到Components中
                SchemaDef schemaDef = new SchemaDef();
                schemaDef.setId(operatorBean.getOperatorName() + "Ref");
                String partition = operatorBean.getConnectionsInfo().get("partition").toString();
                if (SOURCE.equalsIgnoreCase(operatorBean.getOperatorType()) &&
                        StringUtils.isNotEmpty(partition)) {
                    //针对分区，需要将分区字段放在最后
                    String operateType = operatorBean.getOperatorType();

                    List<DataField> outputSchema = operatorBean.getOutputSchema();
                    int partitionIndex = outputSchema.indexOf(operateType);
                    List<DataField> targetSchema = new ArrayList<>();
                    for (int i = 0; i < outputSchema.size(); i++) {
                        if (i == partitionIndex) {
                            continue;
                        }
                        targetSchema.add(outputSchema.get(i));
                    }
                    //将分区字段加入末尾
                    targetSchema.add(outputSchema.get(partitionIndex));
                    schemaDef.setDataFieldList(targetSchema);
                } else {
                    //非分区字段直接加入
                    schemaDef.setDataFieldList(operatorBean.getOutputSchema());
                }
                schemaDefList.add(schemaDef);
            }
        }
        componentDef.setSchemaList(schemaDefList);
    }

    /**
     * 构建通用组件参数信息
     *
     * @param sourceOperators
     * @param componentDef
     */
    public static void buildSourceParam(List<OperatorBean> sourceOperators, ComponentDef componentDef) {
        List<CommonParamDef> commonParamList = new ArrayList();
        for (OperatorBean operatorBean : sourceOperators) {
            //连接信息
            Map<String, Object> connectInfo = operatorBean.getConnectionsInfo();
            String operatorName = operatorBean.getOperatorName();
            String dbType = connectInfo.get("dbType").toString();
            if (JDBC_TYPE.contains(dbType)) {
                //JDBC的连接参数
                CommonParamDef commonParamDef = new CommonParamDef();
                commonParamDef.setJdbcArgs(buildJdbcParam(connectInfo));
                commonParamDef.setId(operatorName + "Ref");
                commonParamDef.setClassName(JDBCPARAM_CLASS);
                commonParamList.add(commonParamDef);
            } else if (MONGODB.equalsIgnoreCase(dbType)) {
                //FTP的连接参数
                CommonParamDef commonParamDef = new CommonParamDef();
                commonParamDef.setJdbcArgs(buildFtpParam(connectInfo));
                commonParamDef.setId(operatorName + "Ref");
                commonParamDef.setClassName(FTPPARAM_CLASS);
                commonParamList.add(commonParamDef);
            } else {
                //待更新
            }
        }
        componentDef.setCommonParamList(commonParamList);
    }

    /**
     * 构建Jdbc参数
     *
     * @param connectInfo 连接信息
     */
    public static List<Object> buildJdbcParam(Map<String, Object> connectInfo) {
        List<Object> list = new ArrayList<>();
        list.add(connectInfo.get("username"));
        list.add(connectInfo.get("password"));
        list.add(connectInfo.get("dbType"));
        list.add(connectInfo.get("dbURL"));
        list.add(connectInfo.get("tables"));
        list.add(connectInfo.get("schema"));
        list.add(connectInfo.get("incFiled"));
        list.add(connectInfo.get("incFiledValue"));
        if (StringUtils.isNotEmpty(connectInfo.get("querySql").toString())) {
            list.add("SQL");
            list.add(connectInfo.get("querySql").toString());
        }
        return list;
    }

    /**
     * 拼接FPT参数
     */
    public static List<Object> buildFtpParam(Map<String, Object> connectInfo) {
        List<Object> list = new ArrayList<>();
        list.add(connectInfo.get("remotePath"));
        list.add(connectInfo.get("ip").toString());
        list.add(connectInfo.get("port").toString());
        list.add(connectInfo.get("username").toString());
        list.add(connectInfo.get("password").toString());
        list.add(connectInfo.get("filter").toString());
        return list;
    }


}
