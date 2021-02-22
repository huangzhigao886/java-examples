package com.jsonparser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.internal.filter.ValueNode;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2020/2/9
 * @Description:
 */
public class JsonParse2 {
    public static void main(String[] args) throws IOException {
        JsonParse2 jsonParse2 = new JsonParse2();
        jsonParse2.parse();
    }
    public void parse() throws IOException {
        List<String> list = IOUtils.readLines(new FileInputStream("D://jc.json"));
        String jsonData = String.join("\n", list).trim();
        if (!jsonData.startsWith("[") && !jsonData.endsWith("]")) {
            jsonData = "[" + jsonData + "]";
        }
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(jsonData, JsonArray.class);
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonElement jsonElement = jsonArray.get(i);
            Object document = Configuration.defaultConfiguration().jsonProvider().parse(jsonElement.toString());
        }
    }


    public void parseField(Object document) {
        String checkpoint_num = JsonPath.read(document, "$.checkpoint_num").toString();
        String time = JsonPath.read(document, "$.time").toString();
        String flow = JsonPath.read(document, "$.data.*.flow");

    }
}
