package com.sqlparse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/11
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableColumn {
    //表的原始字段
    private String originalName;

    //表的别名字段，若无别名，则与原字段名相对应
    private String columnAlias;
}
