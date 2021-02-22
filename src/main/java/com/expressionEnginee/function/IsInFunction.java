package com.expressionEnginee.function;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.AbstractVariadicFunction;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorRuntimeJavaType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * in(value,arg2,arg3.....) 判断value是否与后面的参数相等
 */
public class IsInFunction extends AbstractVariadicFunction {
    @Override
    public String getName() {
        return "in";
    }

    public AviatorObject variadicCall(Map<String, Object> env, AviatorObject... args) {
        List<String> list = new ArrayList<>();
        for (int i = 1;i<args.length;i++) {
            list.add(args[i].getValue(env).toString());
        }
        //原值
        String value = args[0].getValue(env).toString();
        return list.contains(value) ? AviatorBoolean.TRUE : AviatorBoolean.FALSE;
    }
}
