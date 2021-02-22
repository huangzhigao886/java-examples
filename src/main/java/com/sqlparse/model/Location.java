package com.sqlparse.model;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Location {
    private int x;

    private int y;

    private List<String> from;

    private List<String> to;

    public static Location fill(JSONObject jsonobj) {
        Location entity = new Location();
        if (jsonobj.containsKey("x")) {
            entity.setX(jsonobj.getInt("x"));
        }
        if (jsonobj.containsKey("y")) {
            entity.setY(jsonobj.getInt("y"));
        }
        if (jsonobj.containsKey("from")) {
            entity.setFrom(jsonobj.getJSONArray("from").toList(String.class));
        }
        if (jsonobj.containsKey("to")) {
            entity.setTo(jsonobj.getJSONArray("to").toList(String.class));
        }
        return entity;
    }

    public static List<Location> fillList(JSONArray jsonarray) {
        if (jsonarray == null || jsonarray.size() == 0) {
            return null;
        }
        List<Location> olist = new ArrayList<>();
        for (int i = 0; i < jsonarray.size(); i++) {
            olist.add(fill(jsonarray.getJSONObject(i)));
        }
        return olist;
    }
}