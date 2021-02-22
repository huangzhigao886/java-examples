package com.velocity.Bean;

import com.modelJson.DataField;
import lombok.Data;

import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/18
 * @Description:
 */
@Data
public class SchemaBean {
    private String id;
    private String className;
    private List<DataField> dataFieldList;
}
