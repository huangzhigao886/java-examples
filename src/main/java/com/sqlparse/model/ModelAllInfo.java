package com.sqlparse.model;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ModelAllInfo {
    /**
     * 模型编号,申请的时候需要传入
     */
    private Long modelId;

    /**
     * 模型名称
     */
    private String modelName;

    /**
     * 模型UUID
     */
    private String modelUUID;
    /**
     * 模型标签
     */
    private String modelLable;
    /**
     * 模型描述
     */
    private String modelDesc;

    /**
     * 运行类型
     */
    private Integer runType;

    /**
     * 运行设置
     */
    private String runCorn;

    /**
     * 运行设置,前端需要反显
     */
    private String cornType;

    /**
     * 模型运行开始时间(暂时不落库)
     */
    private String startTime;

    /**
     * 模型运行结束时间(暂时不落库)
     */
    private String endTime;

    /**
     * 算子描述
     */
    private List<Operators> operators;


    public static ModelAllInfo fill(JSONObject jsonobj) {
        ModelAllInfo entity = new ModelAllInfo();
        if (jsonobj.containsKey("modelName")) {
            entity.setModelName(jsonobj.getStr("modelName"));
        }
        if (jsonobj.containsKey("modelLable")) {
            entity.setModelLable(jsonobj.getStr("modelLable"));
        }
        if (jsonobj.containsKey("modelDesc")) {
            entity.setModelDesc(jsonobj.getStr("modelDesc"));
        }
        if (jsonobj.containsKey("runType")) {
            entity.setRunType(jsonobj.getInt("runType"));
        }
        if (jsonobj.containsKey("runCorn")) {
            entity.setRunCorn(jsonobj.getStr("runCorn"));
        }
        if (jsonobj.containsKey("operators")) {
            entity.setOperators(jsonobj.getJSONArray("operators").toList(Operators.class));
        }
        return entity;
    }

    public static List<ModelAllInfo> fillList(JSONArray jsonarray) {
        if (jsonarray == null || jsonarray.size() == 0) {
            return null;
        }
        List<ModelAllInfo> olist = new ArrayList<ModelAllInfo>();
        for (int i = 0; i < jsonarray.size(); i++) {
            olist.add(fill(jsonarray.getJSONObject(i)));
        }
        return olist;
    }
}
