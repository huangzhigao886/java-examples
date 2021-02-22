package com.expressionEnginee.function;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.AbstractVariadicFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.Map;

public class AddFunction extends AbstractVariadicFunction {
    @Override
    public AviatorObject variadicCall(Map<String, Object> env, AviatorObject... args) {
        double sum = 0;
        for(int i = 0;i<args.length;i++){
            sum+=FunctionUtils.getNumberValue(args[i],env).doubleValue();
        }
        return new AviatorDouble(sum);
    }

    @Override
    public String getName() {
        return "add";
    }

//    @Override
//    public String getName() {
//        return "add";
//    }
//
//    @Override
//    public AviatorObject call(Map<String, Object> env, AviatorObject arg1,AviatorObject arg2) {
//        Number leftValue = FunctionUtils.getNumberValue(arg1, env);
//        Number rightValue = FunctionUtils.getNumberValue(arg2, env);
//        return new AviatorDouble(leftValue.doubleValue()+rightValue.doubleValue());
//    }

}