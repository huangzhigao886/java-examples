package com.expressionEnginee.function;


import com.googlecode.aviator.runtime.function.AbstractVariadicFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;


import java.util.Map;

public class ContanctFunction extends AbstractVariadicFunction {


    @Override
    public String getName() {
        return "concat";
    }

    @Override
    public AviatorObject variadicCall(Map<String, Object> env, AviatorObject... args) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0;i<args.length;i++){
            stringBuilder.append(FunctionUtils.getStringValue(args[i],env));
        }
        return new AviatorString(stringBuilder.toString());
    }
}
