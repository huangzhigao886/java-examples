package com.enums;

/**
 * 时间类型枚举类
 * 
 * @auth: lidesheng
 * @since DataEngine 1.0
 */
public enum TimeType {
	PROCESSING_TIME("ProcessingTime"),
	INGESTION_TIME("IngestionTime"),
	EVENT_TIME("EventTime");
	
	private String timeCharateristic;
	
	private TimeType(String timeCharateristic) {
		this.timeCharateristic = timeCharateristic;
	}
	
	public String getTimeCharateristic() {
		return timeCharateristic;
	}
}
