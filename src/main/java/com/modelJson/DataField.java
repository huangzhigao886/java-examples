package com.modelJson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/17
 * @Description: 字段信息表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataField {
    /**
     * 字段名
     */
    private String fieldName;
    /**
     * 字段类型
     */
    private String dataType;
    /**
     * 格式转化规则
     */
    private String format;
    /**
     * 精度
     */
    private int precision;

    /**
     * 构造函数
     *
     * @param fieldName
     * @param dataType
     * @param format
     */
    public DataField(String fieldName, String dataType, String format) {
        this.fieldName = fieldName;
        this.dataType = dataType;
        this.format = format;
    }

    /**
     * 构造函数
     *
     * @param fieldName
     * @param dataType
     * @param precision
     */
    public DataField(String fieldName, String dataType, int precision) {
        this.fieldName = fieldName;
        this.dataType = dataType;
        this.precision = precision;
    }

    /**
     * 构造函数
     *
     * @param fieldName
     * @param dataType
     */
    public DataField(String fieldName, String dataType) {
        this.fieldName = fieldName;
        this.dataType = dataType;
    }
}
