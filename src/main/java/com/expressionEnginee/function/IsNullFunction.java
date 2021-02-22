package com.expressionEnginee.function;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.io.Serializable;
import java.util.Map;

/**
 * 判断参数对象是否为空
 *
 * @auth: lidesheng
 * @since DataEngine 1.0
 */
public class IsNullFunction extends AbstractFunction  {



	/**
	 * {@inheritDoc}
	 */
	@Override
	public AviatorObject call(Map<String, Object> env, AviatorObject arg1) {
		Object obj = arg1.getValue(env);
		return AviatorBoolean.valueOf(obj == null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return "isnull";
	}

}