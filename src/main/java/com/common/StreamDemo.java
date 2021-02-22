package com.common;

import com.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: java8新特性 Stream测试
 * Stream的原理：将要处理的元素看做一种流，流在管道中传输，并且可以在管道的节点上处理，
 * 包括过滤筛选、去重、排序、聚合等。元素流在管道中经过中间操作的处理，最后由最终操作得到前面处理的结果。
 * @author: hzg
 * @create: 2019-08-30 10:29
 **/

public class StreamDemo {
    public static void main(String[] args) {
        Student s1 = new Student(1L, "肖战", 15, "浙江");
        Student s2 = new Student(2L, "王一博", 15, "湖北");
        Student s3 = new Student(3L, "杨紫", 17, "北京");
        Student s4 = new Student(4L, "李现", 17, "浙江");
        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);

        List<Student> streamStudents = testFilter(students);
        streamStudents.forEach(System.out::println);
    }

    private static List<Student> testFilter(List<Student> students) {
        //筛选年龄大于15岁的学生
//        return students.stream().filter(s -> s.getAge()>15).collect(Collectors.toList());
        //筛选住在浙江省的学生
        return students.stream().filter(s ->"浙江".equals(s.getAddress())).collect(Collectors.toList());
    }


}
