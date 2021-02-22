package com.velocity.flinkJson;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.util.Map;
import java.util.Map.Entry;

public class VelocityUtils {

	public static String fromTemplate(String vmPath, Map<String, Object> params) {
		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		velocityEngine.init();

		// 获取模板文件
		Template template = velocityEngine.getTemplate(vmPath);

		// 设置变量，velocityContext是一个类似map的结构
		VelocityContext velocityContext = new VelocityContext();

		if (null != params) {
			for (Entry<String, Object> entry : params.entrySet()) {
				velocityContext.put(entry.getKey(), entry.getValue());
			}
		}

		// 输出渲染后的结果
		StringWriter stringWriter = new StringWriter();
		template.merge(velocityContext, stringWriter);
		return stringWriter.toString();
	}
}
