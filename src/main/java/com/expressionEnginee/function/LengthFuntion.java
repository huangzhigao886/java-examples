package com.expressionEnginee.function;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBigInt;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.Map;

/**
 * 获取字符串长度
 */
public class LengthFuntion extends AbstractFunction {

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1) {
        String value = FunctionUtils.getStringValue(arg1, env);
        if(value !=null){
            return new AviatorBigInt(value.length());
        }
        return new AviatorBigInt(0);
    }

    @Override
    public String getName() {
        return "length";
    }

}
