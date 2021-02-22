package com.enums;

import java.nio.ByteBuffer;
import java.sql.Date;

/**
 * 数据类型枚举类<BR>
 * 当前框架支持的数据类型一共9种。FILE暂时不用，留待以后扩展
 * 
 * @auth: lidesheng
 * @since DataEngine 1.0
 */
public enum DataType {
    STRING(String.class),
    DATE(Date.class),
    TIMESTAMP(Long.class),
    INT(Integer.class),
    LONG(Long.class),
    DOUBLE(Double.class),
    BINARY(ByteBuffer.class),
    BOOLEAN(Boolean.class),
	FILE(String.class);

    private Class<?> typeClass;

    private DataType(Class<?> typeClass) {
        this.typeClass = typeClass;
    }

    public Class<?> getTypeClass() {
        return typeClass;
    }
}
