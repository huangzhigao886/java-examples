package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/16
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tiger implements Cloneable {
    private String name;
    private int age;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
