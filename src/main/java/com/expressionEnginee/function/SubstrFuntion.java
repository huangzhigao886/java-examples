package com.expressionEnginee.function;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;

import java.util.Map;

/**
 * 切分字符串
 */
public class SubstrFuntion extends AbstractFunction {
    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3) {
        String target = FunctionUtils.getStringValue(arg1, env);
        Number begin = FunctionUtils.getNumberValue(arg2, env);
        Number end = FunctionUtils.getNumberValue(arg3, env);
        int length = target.length();
        if (begin.intValue() > length || end.intValue() > length || begin.intValue() < 0 || end.intValue() < 0 || begin.intValue() > end.intValue()) {
            return new AviatorString("截取索引不正确");
        }
        return new AviatorString(target.substring(begin.intValue(), end.intValue()));
    }

    @Override
    public String getName() {
        return "substr";
    }
}
