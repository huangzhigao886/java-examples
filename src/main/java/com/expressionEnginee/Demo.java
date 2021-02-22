package com.expressionEnginee;

import com.googlecode.aviator.AviatorEvaluator;

/**
 * 表达式引擎
 */
public class Demo {
    public static void main(String[] args) {
        Long result = (Long) AviatorEvaluator.execute("1+2+3");
        System.out.println(result);
    }
}
