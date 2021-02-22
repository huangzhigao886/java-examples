package com.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @description: map里面改放数组
 * @author: hzg
 * @create: 2019-09-12 15:31
 **/

public class MapContainsList {
    public static void main(String[] args) {
        LinkedList<ArrayList> list = new LinkedList();
        ArrayList arr = new ArrayList();
        arr.add("Aaa");
        arr.add("bbbb");
        arr.add(123);
        list.add(arr);
        arr.clear();
        arr.add("aaa");
        System.out.println(arr.get(0));

    }

}
