package com.sqlparse.model;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FromProps {
    private String propName;

    private Object propValue;

    private JSONObject desc;

    private String inputType;

    private String propDef;




    public static FromProps fill(JSONObject jsonobj) {
        FromProps entity = new FromProps();
        if (jsonobj.containsKey("propName")) {
            entity.setPropName(jsonobj.getStr("propName"));
        }
        if (jsonobj.containsKey("propValue")) {
            entity.setPropValue(jsonobj.getObj("propValue"));
        }
        if (jsonobj.containsKey("propDef")) {
            entity.setPropDef(jsonobj.getStr("propDef"));
        }
        if (jsonobj.containsKey("inputType")) {
            entity.setInputType(jsonobj.getStr("inputType"));
        }
        if (jsonobj.containsKey("desc")) {
            entity.setDesc(jsonobj.getJSONObject("desc"));
        }
        return entity;
    }

    public static List<FromProps> fillList(JSONArray jsonarray) {
        if (jsonarray == null || jsonarray.size() == 0) {
            return null;
        }
        List<FromProps> olist = new ArrayList<FromProps>();
        for (int i = 0; i < jsonarray.size(); i++) {
            olist.add(fill(jsonarray.getJSONObject(i)));
        }
        return olist;
    }
}