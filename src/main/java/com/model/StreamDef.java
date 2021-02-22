package com.model;
import com.enums.Operation;

import java.util.List;

/**
 * 数据流定义类
 * 
 * @auth: lidesheng
 * @since DataEngine 1.0
 */
public class StreamDef {
	/** 流名称 */
	private String name;
	/** 起始节点ID */
	private String from;
	/** 结束节点ID */
	private String to;
	/** 操作类型，对应Operation枚举中的值 */
	private Operation operation;
	/** Operation为JOIN时，表示此流是Join的左侧还是右侧 */
	private JoinSide joinSide;
	/** 起始节点输出的流为SplitStream时，用于选择数据的Key */
	private String selectRoute;
	/** 关联时使用的字段列表 */
	private List<String> joinKeys;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public JoinSide getJoinSide() {
		return joinSide;
	}

	public void setJoinSide(JoinSide joinSide) {
		this.joinSide = joinSide;
	}

	public String getSelectRoute() {
		return selectRoute;
	}

	public void setSelectRoute(String selectRoute) {
		this.selectRoute = selectRoute;
	}

	public List<String> getJoinKeys() {
		return joinKeys;
	}

	public void setJoinKeys(List<String> joinKeys) {
		this.joinKeys = joinKeys;
	}
}
