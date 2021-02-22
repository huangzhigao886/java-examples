package com.model;

/**
 * 类属性定义类，暂不使用
 * 
 * @auth: lidesheng
 * @since DataEngine 1.0
 */
public class PropertyDef {
	/** 属性名称 */
    private String name;
    /** 属性值 */
    private Object value;
    /** 引用BEAN ID */
    private String ref;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    /**
     * Sets the value of this property. Throws IllegalArgumentException if a reference has
     * already been set.
     * @param value property value
     */
    public void setValue(Object value) {
        if (this.ref != null) {
            throw new IllegalStateException("A property can only have a value OR a reference, not both.");
        }
        this.value = value;
    }

    public String getRef() {
        return ref;
    }

    /**
     * Sets the value of this property to a reference. Throws IllegalArgumentException if a value has
     * already been set.
     * @param ref property reference
     */
    public void setRef(String ref) {
        if (this.value != null) {
            throw new IllegalStateException("A property can only have a value OR a reference, not both.");
        }
        this.ref = ref;
    }

    public boolean isReference() {
        return this.ref != null;
    }
}
