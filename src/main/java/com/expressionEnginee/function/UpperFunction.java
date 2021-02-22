package com.expressionEnginee.function;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;

import java.util.Map;

/**
 * 转小写
 */
public class UpperFunction extends AbstractFunction {

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1) {
        String value = FunctionUtils.getStringValue(arg1,env);
        if(value != null){
            return new AviatorString(value.toUpperCase());
        }
        return new AviatorString(null);
    }

    @Override
    public String getName() {
        return "upper";
    }
}
