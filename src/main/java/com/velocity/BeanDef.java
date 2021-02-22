package com.velocity;

import lombok.Data;

import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/18
 * @Description:
 */
@Data
public class BeanDef {
    private String id;
    private String className;
    private List<Object> constructArgsList;
    private List<Object> configMethodsList;

    public static void main(String[] args) {
        String parquet = "hdfs://ha-nameservice/encdata/impala/storage/originald/ysk_gccsyz_user_detail01";
        String[] split = parquet.split("/");
        System.out.println(split[6]);
    }
}
