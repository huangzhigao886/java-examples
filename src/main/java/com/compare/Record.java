package com.compare;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 通用行记录结构。<BR>
 * 在通用计算框架中，所有节点直接传递的数据都是Record类型。<BR>
 * 存储方式为对象数组，每列为数组的一个元素。通过Schema来描述每个下标对应的元素的列名称和类型。
 *
 * @auth: lidesheng
 * @since DataEngine 1.0
 */
public class Record implements Serializable, Comparable<Record> {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7996716162758361410L;

	/** 列数 */
	private int arity;
	/** 存储数据的对象数组 */
	private Object[] values;

	public Record() {
		this(0);
	}
	
	/**
	 * 构造器，构造指定列数的行记录
	 * 
	 * @param arity 列数
	 */
	public Record(int arity, Record record) {
		if ((null == record && arity < 1) || (null != record && arity < record.arity)) {
			return;
		}
		
		this.arity = arity;
		values = new Object[arity];
		
		if (null != record) {
			System.arraycopy(record.values, 0, values, 0, record.arity);
		}
	}

	/**
	 * 构造器，构造指定列数的行记录
	 * 
	 * @param arity 列数
	 */
	public Record(int arity) {
		if (arity < 1) {
			return;
		}
		this.arity = arity;
		values = new Object[arity];
	}

	/**
	 * 构造器，直接指定行数据进行构造
	 * 
	 * @param values 行数据对象数组
	 */
	public Record(Object[] values) {
		if (null == values || values.length == 0) {
			throw new IllegalArgumentException("null data is not allowed");
		}

		arity = values.length;
		this.values = new Object[arity];
		System.arraycopy(values, 0, this.values, 0, values.length);
	}

	/**
	 * 获取行的数据
	 * 
	 * @return Object[] 各列的数据
	 */
	public Object[] getValues() {
		return values;
	}

	/**
	 * 获取指定列序号的数据
	 * 
	 * @param pos 列序号
	 * @return 列序号对应的列值
	 */
	public Object getField(int pos) {
		return values[pos];
	}

	/**
	 * 设置某列的值
	 * 
	 * @param pos    列序号
	 * @param object 设置的目标值
	 */
	public void setField(int pos, Object object) {
		values[pos] = object;
	}

	/**
	 * 获取列数
	 * 
	 * @return
	 */
	public int getArity() {
		return arity;
	}

	/**
	 * 设置行数据值
	 * 
	 * @param values 各列的数据
	 */
	public void setValues(Object[] values) {
		if (null == values || values.length == 0) {
			throw new IllegalArgumentException("values can not be empty");
		}
		this.arity = values.length;
		this.values = values;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return StringUtils.join(values, ",");
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object other) {
		// 此方法目前主要用于去重判断、聚合和按Key分片时，用于比较两条数据的key也使用Record存储，基于equals判断是否需要相同
		if (null == other) {
			return false;
		}
		if (!(other instanceof Record)) {
			return false;
		}

		Record otherRecord = (Record) other;
		if (arity != otherRecord.getArity()) {
			return false;
		}

		for (int i = 0; i < arity; i++) {
			if (compare(values[i], otherRecord.getField(i)) != 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(Record o) {
		// 此方法目前主要用于排序
		if (null == o) {
			return 1;
		}

		int minSize = Math.min(arity, o.getArity());
		for (int i = 0; i < minSize; i++) {
			int compareResult = compare(getField(i), o.getField(i));
			if (compareResult != 0) {
				return compareResult;
			}
		}
		if (arity > o.arity) {
			return 1;
		} else if (arity == o.arity) {
			return 0;
		}
		return -1;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private int compare(Object obj1, Object obj2) {
		if (null == obj1) {
			if (null == obj2) {
				return 0;
			}
			return -1;
		}

		if (null == obj2) {
			return 1;
		}

		if (obj1 instanceof Number && obj2 instanceof Number) {
			return compareNumber((Number) obj1, (Number) obj2);
		}

		if ((obj1 instanceof Date && obj2 instanceof Long)) {
			return compareLong(((Date) obj1).getTime(), (Long) obj2);
		}

		if ((obj1 instanceof Long && obj2 instanceof Date)) {
			return compareLong((Long) obj1, ((Date) obj2).getTime());
		}

		if (obj1 instanceof Number && obj2 instanceof String) {
			return String.valueOf(obj1).compareTo((String) obj2);
		}

		if (obj1 instanceof String && obj2 instanceof Number) {
			return ((String) obj1).compareTo(String.valueOf(obj2));
		}

		if (obj1.getClass() != obj2.getClass()) {
			return 1;
		}

		if (!(obj1 instanceof Comparable) || !(obj2 instanceof Comparable)) {
			return 1;
		}

		return ((Comparable) obj1).compareTo(obj2);
	}

	private int compareNumber(Number obj1, Number obj2) {
		// Record中只存在3种数字：int, long和double, 暂不考虑其他情况
		if (obj1 instanceof Integer) {
			if (obj2 instanceof Integer) {
				return (Integer) obj1 - (Integer) obj2;
			} else if (obj2 instanceof Long) {
				return compareLong(obj1.longValue(), (Long) obj2);
			} else {
				return compareLong(obj1.longValue(), ((Double) obj2).longValue());
			}
		} else if (obj1 instanceof Long) {
			if (obj2 instanceof Integer) {
				return compareLong((Long) obj1, ((Integer) obj2).longValue());
			} else if (obj2 instanceof Long) {
				return compareLong((Long) obj1, (Long) obj2);
			} else {
				return compareLong((Long) obj1, ((Double) obj2).longValue());
			}
		} else {
			if (obj2 instanceof Integer) {
				return compareLong(((Double) obj1).longValue(), ((Integer) obj2).longValue());
			} else if (obj2 instanceof Long) {
				return compareLong(((Double) obj1).longValue(), (Long) obj2);
			} else {
				return ((Double) obj1).compareTo((Double) obj2);
			}
		}
	}

	private int compareLong(long obj1, long obj2) {
		if (obj1 > obj2) {
			return 1;
		} else if (obj1 == obj2) {
			return 0;
		}
		return -1;
	}

	public void setArity(int arity) {
		this.arity = arity;
	}
}
