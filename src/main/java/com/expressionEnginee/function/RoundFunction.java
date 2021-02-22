package com.expressionEnginee.function;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.Map;


/**
 * 保留小数点后几位，暂时不考虑保留小数点前几位的情况
 */
public class RoundFunction extends AbstractFunction {

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
        Number leftNumber = FunctionUtils.getNumberValue(arg1, env);
        Number rightNumber = FunctionUtils.getNumberValue(arg2,env);
        double value = leftNumber.doubleValue();
        int subNum = rightNumber.intValue();
        String format = String.format("%." + subNum + "f", value);
        return new AviatorDouble(Double.valueOf(format));
    }

    @Override
    public String getName() {
        return "round";
    }

}
