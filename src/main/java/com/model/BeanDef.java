package com.model;

import com.util.GsonUtils;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 通用Bean定义，id为Job内唯一标识
 *
 * @auth: lidesheng
 * @since DataEngine 1.0
 */
@NoArgsConstructor
public class BeanDef extends ObjectDef {
	/** bean ID */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static void main(String[] args) {
//        List<String>  list = new ArrayList<>();
//        list.add("aaa");
//        list.add("bb");
//        Iterator<String> iterator = list.iterator();
//        Iterator<String> iterator1 = list.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
//        while (iterator1.hasNext()){
//            System.out.println(iterator1.next());
//        }
        BeanDef beanDef = new BeanDef();
        beanDef.setId("aa");
        System.out.println(GsonUtils.toJson(beanDef));
    }
}
