package com.expressionEnginee.function;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * aviator的入门级使用
 */
public class SimpleExpression {

    //关于字符串的内置函数的支持
    public static void getLength() {
        System.out.println("字符串的长度为:" + AviatorEvaluator.execute("string.length('hello')"));
//        //测试内联函数
        System.out.println("测试内置函数的计算方式");
//        //待测试，目前由缺陷
//        System.out.println(AviatorEvaluator.execute("string.length(string.length('hello'))"));

        System.out.println("判断字串包含问题："+AviatorEvaluator.execute("string.contains(\"test\",string.substring('hello',1,2))"));
        System.out.println("切分字符串:"+AviatorEvaluator.execute("string.substring('hello',0,2)"));

    }

    /**
     * 进行预编译提升效率
     */
    public static Expression compileFunction(){
        String express = "a-(b+c)>100";
        Expression expression = AviatorEvaluator.compile(express);
        return expression;
    }

    /**
     * 访问数组与集合
     */
    public static void accessArr_Map(){
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("word");
        int[] array = new int[3];
        array[0] = 1;
        array[1] = 2;
        array[2] = 3;
        Map<String, Date> map = new HashMap<>();
        map.put("date",new Date());
        Map<String,Object> env = new HashMap<>();
        env.put("list",list);
        env.put("arr",array);
        env.put("mapp",map);
        System.out.println(AviatorEvaluator.execute("list[0]+list[1]",env));
        System.out.println(AviatorEvaluator.execute("arr[0]+arr[1]+arr[2]",env));
        System.out.println(AviatorEvaluator.execute("'today is '+ mapp.date",env));
    }


    /**
     * 正则匹配
     */
    public static void regex(){
        String email = "huangzhigao@126.com";
        Map<String,Object> env = new HashMap<>();
        env.put("email",email);
        //$0代表整个匹配的字符串，$1代表第一个分组
        System.out.println("正则匹配："+AviatorEvaluator.execute("email=~/([\\w0-8]+)@\\w+[\\.\\w+]+/ ? $0:'unknow'",env));

    }

    /**
     * 时间比较
     */
    public static void dateCompare(){
        Map<String, Object> env = new HashMap<String, Object>();
        final Date date = new Date();
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS").format(date);
        env.put("date", date);
        env.put("dateStr", dateStr);
        Boolean result = (Boolean) AviatorEvaluator.execute("date==dateStr", env);
        System.out.println(result);  // true
        result = (Boolean) AviatorEvaluator.execute("date > '2010-12-20 00:00:00:00' ", env);
        System.out.println(result);  // true
        result = (Boolean) AviatorEvaluator.execute("date < '2200-12-20 00:00:00:00' ", env);
        System.out.println(result);  // true
        result = (Boolean) AviatorEvaluator.execute("date==date ", env);
        System.out.println(result);
    }


    /**
     * Seq
     */
    public static void seqTest(){
        Map<String, Object> env = new HashMap<String, Object>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(3);
        list.add(20);
        list.add(10);
        env.put("list", list);
        Object result = AviatorEvaluator.execute("count(list)", env);
        System.out.println(result);  // 3
        result = AviatorEvaluator.execute("reduce(list,+,0)", env);
        System.out.println(result);  // 33
        result = AviatorEvaluator.execute("filter(list,seq.gt(9))", env);
        System.out.println(result);  // [10, 20]
        result = AviatorEvaluator.execute("include(list,10)", env);
        System.out.println(result);  // true
        result = AviatorEvaluator.execute("sort(list)", env);
        System.out.println(result);  // [3, 10, 20]
        System.out.println(AviatorEvaluator.execute("map(list,println)", env));

    }


    public static void joinFuntion(){
        System.out.println(AviatorEvaluator.execute("string.join('a','b')"));
    }


    public static void main(String[] args) {
        //表达式计算
        System.out.println(AviatorEvaluator.execute("1.3+2+3"));
        //字符串的拼接
        String yourName = "Tom";
        String sayHello = "how are you";
        //执行表达式时，其中的变量由env去提供，如果无，则为空，变量不用单引号
        Map<String, Object> env = new HashMap<>();
        env.put("yourName", yourName);
        env.put("sayHello", sayHello);
        System.out.println((String) AviatorEvaluator.execute("'hello '+yourName+'\n'+sayHello", env));
        //无变量的测试,如果需要加字符串则需要加上''代表这是个字符串
        System.out.println(AviatorEvaluator.execute("hello"));
        //测试内置函数中传递的参数是变量的情况(测试结果为支持)
        System.out.println("参数为变量的情况："+AviatorEvaluator.execute("string.length(sayHello)",env));
        getLength();

        //事先预编译了表达式
        Expression expression = compileFunction();
        Map<String,Object > envMap = new HashMap<>();
        envMap.put("a",1000);
        envMap.put("b",2);
        envMap.put("c",3);
        System.out.println("预编译后进行计算："+expression.execute(envMap));

        System.out.println("访问List和Array");
        accessArr_Map();

        //三目运算符的运用
        System.out.println("三目运算符："+AviatorEvaluator.execute("2>1?'yes':'no'"));

        System.out.println("正则表达式测试");
        regex();

//        System.out.println("seq测试");
//        seqTest();

//
//        System.out.println(AviatorEvaluator.execute("seq.add(1,2)"));


        joinFuntion();
    }
}
