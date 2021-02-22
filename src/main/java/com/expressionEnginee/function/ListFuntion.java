package com.expressionEnginee.function;


import com.googlecode.aviator.runtime.function.AbstractVariadicFunction;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorRuntimeJavaType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListFuntion extends AbstractVariadicFunction {
    @Override
    public String getName() {
        return "list";
    }

    @Override
    public AviatorObject variadicCall(Map<String, Object> env, AviatorObject... args) {
        List<String> list = new ArrayList<>();

        for (AviatorObject obj : args) {
            list.add(obj.getValue(env).toString());
        }
        return new AviatorRuntimeJavaType(list);
    }
}
