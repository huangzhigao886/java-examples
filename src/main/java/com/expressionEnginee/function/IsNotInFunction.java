package com.expressionEnginee.function;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.AbstractVariadicFunction;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IsNotInFunction extends AbstractVariadicFunction {
    @Override
    public String getName() {
        return "notIn";
    }

    public AviatorObject variadicCall(Map<String, Object> env, AviatorObject... args) {
        List<String> list = new ArrayList<>();
        for (int i = 1;i<args.length;i++) {
            list.add(args[i].getValue(env).toString());
        }
        //原值
        String value = args[0].getValue(env).toString();
        return list.contains(value) ? AviatorBoolean.FALSE : AviatorBoolean.TRUE;
    }
}
