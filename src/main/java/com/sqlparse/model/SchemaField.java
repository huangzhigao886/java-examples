package com.sqlparse.model;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SchemaField {
    private String fieldName;

    private String fieldType;

    private String fieldCnName;

    private String fieldLength;

    private String fieldFormat;
    /**
     * 前端源值,业务不使用，为了方便映射
     */
    private String sourceName;


    public static SchemaField fill(JSONObject jsonobj) {
        SchemaField entity = new SchemaField();
        if (jsonobj.containsKey("fieldName")) {
            entity.setFieldName(jsonobj.getStr("fieldName"));
        }
        if (jsonobj.containsKey("fieldType")) {
            entity.setFieldType(jsonobj.getStr("fieldType"));
        }
        if (jsonobj.containsKey("fieldCnName")) {
            entity.setFieldCnName(jsonobj.getStr("fieldCnName"));
        }
        if (jsonobj.containsKey("fieldLength")) {
            entity.setFieldLength(jsonobj.getStr("fieldLength"));
        }
        if (jsonobj.containsKey("fieldFormat")) {
            entity.setFieldFormat(jsonobj.getStr("fieldFormat"));
        }
        return entity;
    }

    public static List<SchemaField> fillList(JSONArray jsonarray) {
        if (jsonarray == null || jsonarray.size() == 0) {
            return null;
        }
        List<SchemaField> olist = new ArrayList<SchemaField>();
        for (int i = 0; i < jsonarray.size(); i++) {
            olist.add(fill(jsonarray.getJSONObject(i)));
        }
        return olist;
    }
}
