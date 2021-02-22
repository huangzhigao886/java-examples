package com.enums;

/**
 * 数据操作类型枚举类
 * 
 * @auth: lidesheng
 * @since DataEngine 1.0
 */
public enum Operation {
	MAP,
	MAP_PARTITION,
	FLATMAP,
	JOIN,
	FILTER,
	SPLIT,
	UNION,
	SORT,
	UNIQUE,
	SELECT,
	GROUPBY,
	KEYBY,
	TIME_WINDOW,
	COUNT_WINDOW,
	REDUCE
}
