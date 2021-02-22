package com.modelJson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2020/4/16
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchemaDef extends BeanDef {

    /**
     * 字段信息
     */
    private List<DataField> dataFieldList;
}
