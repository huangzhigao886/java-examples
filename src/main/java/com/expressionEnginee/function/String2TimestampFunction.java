package com.expressionEnginee.function;

import com.googlecode.aviator.exception.ExpressionRuntimeException;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.function.system.DateFormatCache;
import com.googlecode.aviator.runtime.type.AviatorLong;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 将String转换为timestamp类型(long)
 *
 * @auth: lidesheng
 * @since DataEngine 1.0
 */

public class String2TimestampFunction extends AbstractFunction {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return "str_to_date";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
		String source = FunctionUtils.getStringValue(arg1, env);
		String format = FunctionUtils.getStringValue(arg2, env);
		SimpleDateFormat dateFormat = DateFormatCache.getOrCreateDateFormat(format);
		try {
			Date date = dateFormat.parse(source);
			return AviatorLong.valueOf(date.getTime());
		} catch (ParseException e) {
			throw new ExpressionRuntimeException("Cast string to date failed", e);
		}
	}
}
