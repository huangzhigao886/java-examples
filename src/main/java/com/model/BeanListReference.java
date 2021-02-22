package com.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Bean引用列表类，当前暂无使用场景
 *
 * @auth: lidesheng
 * @since DataEngine 1.0
 */
public class BeanListReference {
	/** 引用的bean ID列表 */
	public final List<String> ids;

	public BeanListReference(List<String> ids) {
		this.ids = ids;
	}

	public Collection<String> getIds() {
		return Collections.unmodifiableCollection(ids);
	}
}
