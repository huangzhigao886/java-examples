package com.model;

/**
 * 图定点定义类，是SourceDef,OperatorDef,SinkDef的父类
 * 
 * @auth: lidesheng
 * @since DataEngine 1.0
 */
public abstract class VertexDef extends BeanDef {

	/** 执行并行度 */
	private int parallelism = 1;

	public int getParallelism() {
		return parallelism;
	}

	public void setParallelism(int parallelism) {
		this.parallelism = parallelism;
	}
}
