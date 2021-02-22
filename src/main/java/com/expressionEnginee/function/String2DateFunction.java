package com.expressionEnginee.function;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.*;
import java.util.Map;

/**
 * 时间格式的字符按串转成时间戳(long)
 */
public class String2DateFunction extends AbstractFunction {

    @Override
    public String getName() {
        return "str_to_date";
    }

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
        String dataString = FunctionUtils.getStringValue(arg1, env);
        String format = FunctionUtils.getStringValue(arg2, env);
        DateTime parse = DateUtil.parse(dataString, format);
        return AviatorLong.valueOf(parse.getTime());
    }
}
