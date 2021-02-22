package com.collection;

import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/2
 * @Description:
 */
public class ListSplitDemo {

    private static final Integer MAX_NUMBER = 1000;


    public static void main(String[] args) {
        List list = new ArrayList<>();
        for(int i = 0;i<10000;i++){
            list.add(i);
        }

        List<List> partition = ListUtils.partition(list, MAX_NUMBER);
        for(List list1:partition){
            System.out.println(list1.size());
        }


    }
}
