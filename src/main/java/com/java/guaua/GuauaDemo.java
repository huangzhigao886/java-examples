package com.java.guaua;

import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/23
 * @Description:
 */
public class GuauaDemo {

    /**
     * java原生自带的不可变集合
     */
    public static void testUnmodifiableSet() {
        Set<String> set = new HashSet<String>(Arrays.asList(new String[]{"green", "yellow"}));

        Set<String> unmodifiableSet = Collections.unmodifiableSet(set);
        set.add("oracle");
        Iterator<String> iterator = unmodifiableSet.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        //集合不可变
//        unmodifiableSet.add("aa");

    }


    public static void main(String[] args) {
        testUnmodifiableSet();
    }
}
