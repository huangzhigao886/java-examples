package com.expressionEnginee.function;


import com.googlecode.aviator.runtime.function.AbstractVariadicFunction;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;

import java.util.Map;

/**
 * 参数是（true,res1,false,res2,false,res3,other）的字符串
 */
public class DecodeFunction extends AbstractVariadicFunction {
    @Override
        public AviatorObject variadicCall(Map<String, Object> env, AviatorObject... args) {
        for (int i = 0; i < args.length - 1; i = i + 2) {
            Boolean flag = (Boolean) args[i].getValue(env);
            if (flag) {
                String res = args[i + 1].getValue(env).toString();
                return new AviatorString(res);
            }
        }
        return new AviatorString(args[args.length - 1].getValue(env).toString());
    }

    @Override
    public String getName() {
        return "decode";
    }


}
