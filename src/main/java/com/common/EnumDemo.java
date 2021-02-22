package com.common;

import com.enums.OperaType;

/**
 * @description: 枚举类测试
 * @author: hzg
 * @create: 2019-09-03 18:47
 **/

public class EnumDemo {
    private OperaType operaType;

    EnumDemo(OperaType operat) {
        this.operaType = operat;
    }


    public OperaType getOperaType() {
        return operaType;
    }

    public void setOperaType(OperaType operaType) {
        this.operaType = operaType;
    }

    public static void main(String[] args) {
        EnumDemo enumDemo = new EnumDemo(OperaType.DELETE);
        System.out.println(enumDemo.operaType == OperaType.DELETE);
    }
}
