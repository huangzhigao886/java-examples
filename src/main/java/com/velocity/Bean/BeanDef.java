package com.velocity.Bean;

import cn.encdata.cloud.dateengine.bean.ModelBean;
import cn.hutool.core.io.file.FileReader;
import com.google.gson.Gson;
import lombok.Data;

import static cn.encdata.cloud.dateengine.CreateFlinkJson.generateFlinkJson;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/18
 * @Description:
 */
@Data
public class BeanDef {
    private String id;
    private String className;

    public static void main(String[] args) {
        FileReader fileReader = new FileReader("D://123.json");
        String result = fileReader.readString();
        Gson gson = new Gson();
        ModelBean modelBean = gson.fromJson(result, ModelBean.class);
//        String s = generateFlinkJson(modelBean, false);
//        System.out.println(s);

    }
}
