package com.expressionEnginee.function;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.Map;

public class BetweenFunciton extends AbstractFunction {

    @Override
    public String getName() {
        return "between";
    }

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3) {
        Double value = FunctionUtils.getNumberValue(arg1, env).doubleValue();
        Double beginValue = FunctionUtils.getNumberValue(arg2, env).doubleValue();
        Double endValue = FunctionUtils.getNumberValue(arg3, env).doubleValue();
        if(value>beginValue&&value<endValue){
            return AviatorBoolean.TRUE;
        }else{
            return AviatorBoolean.FALSE;
        }
    }
}
