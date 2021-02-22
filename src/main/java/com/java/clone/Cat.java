package com.java.clone;

import cn.hutool.core.clone.CloneRuntimeException;
import cn.hutool.core.clone.Cloneable;

public class Cat implements Cloneable<Cat> {
    private String name = "miaomiao";
    private int age = 2;
    @Override
    public Cat clone() {
        try {
            return (Cat)super.clone();
        } catch (CloneNotSupportedException e) {
            throw new CloneRuntimeException(e);
        }
    }
}
