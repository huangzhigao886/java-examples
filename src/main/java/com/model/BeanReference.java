package com.model;

/**
 * Bean引用描述类
 *
 * @auth: lidesheng
 * @since DataEngine 1.0
 */
public class BeanReference {
	/** 引用目标bean ID */
	public final String id;

	public BeanReference(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
