package com.sqlparse.model;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class Operators {
    private String operatorName;

    private String operatorType;

    private String operatorGroup;

    private String displayName;

    private JSONObject schema;

    private BaseInfo baseInfo;

    private Location location;

    private List<FromProps> props;

    private Map<String, String> hideValue;


    public static Operators fill(JSONObject jsonobj) {
        Operators entity = new Operators();
        if (jsonobj.containsKey("operatorName")) {
            entity.setOperatorName(jsonobj.getStr("operatorName"));
        }
        if (jsonobj.containsKey("operatorType")) {
            entity.setOperatorType(jsonobj.getStr("operatorType"));
        }
        if (jsonobj.containsKey("operatorGroup")) {
            entity.setOperatorGroup(jsonobj.getStr("operatorGroup"));
        }
        if (jsonobj.containsKey("displayName")) {
            entity.setDisplayName(jsonobj.getStr("displayName"));
        }
        if (jsonobj.containsKey("schema")) {
            entity.setSchema(jsonobj.getJSONObject("schema"));
        }
        if (jsonobj.containsKey("baseInfo")) {
            entity.setBaseInfo(jsonobj.getJSONObject("baseInfo").toBean(BaseInfo.class));
        }
        if (jsonobj.containsKey("location")) {
            entity.setLocation(jsonobj.getJSONObject("location").toBean(Location.class));
        }
        if (jsonobj.containsKey("props")) {
            entity.setProps(jsonobj.getJSONArray("props").toList(FromProps.class));
        }
        if (jsonobj.containsKey("hideValue")) {
            entity.setHideValue(jsonobj.getJSONObject("hideValue").toBean(Map.class));
        }
        return entity;
    }

    public static List<Operators> fillList(JSONArray jsonarray) {
        if (jsonarray == null || jsonarray.size() == 0) {
            return null;
        }
        List<Operators> olist = new ArrayList<Operators>();
        for (int i = 0; i < jsonarray.size(); i++) {
            olist.add(fill(jsonarray.getJSONObject(i)));
        }
        return olist;
    }
}