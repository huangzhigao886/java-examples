package com.sqlparse.model;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BaseInfo {
    private String incoLogo;

    private int input;

    private int output;


    public static BaseInfo fill(JSONObject jsonobj) {
        BaseInfo entity = new BaseInfo();
        if (jsonobj.containsKey("incoLogo")) {
            entity.setIncoLogo(jsonobj.getStr("incoLogo"));
        }
        if (jsonobj.containsKey("input")) {
            entity.setInput(jsonobj.getInt("input"));
        }
        if (jsonobj.containsKey("output")) {
            entity.setOutput(jsonobj.getInt("output"));
        }
        return entity;
    }

    public static List<BaseInfo> fillList(JSONArray jsonarray) {
        if (jsonarray == null || jsonarray.size() == 0) {
            return null;
        }
        List<BaseInfo> olist = new ArrayList<BaseInfo>();
        for (int i = 0; i < jsonarray.size(); i++) {
            olist.add(fill(jsonarray.getJSONObject(i)));
        }
        return olist;
    }
}
