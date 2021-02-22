package com.dto;

import com.modelJson.DataField;
import lombok.Data;

import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/17
 * @Description:
 */
@Data

public class Schema {
    private String id;
    private String className = "com.encdata.schema";
    private List<DataField> dataFields;

}
