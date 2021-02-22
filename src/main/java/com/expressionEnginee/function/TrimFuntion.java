package com.expressionEnginee.function;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;

import java.util.Map;

/**
 * 去首位空格
 */
public class TrimFuntion extends AbstractFunction {
    @Override
    public String getName() {
        return "trim";
    }

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1) {
        String value = FunctionUtils.getStringValue(arg1, env);
        if (value != null) {
            return new AviatorString(value.trim());
        }
        return new AviatorString(null);
    }
}
