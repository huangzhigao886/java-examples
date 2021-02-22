package demo;

import cn.hutool.json.JSONUtil;
import org.codehaus.jackson.annotate.JsonUnwrapped;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 * @Auther: huangzhigao
 * @Date: 2021/1/6
 * @Description:
 */
public class ArrayTest {
    @Test
    public void test1(){
        String[] strings = new String[]{};
        Arrays.sort(strings);
        for(String string:strings){
            System.out.println(string);
        }
    }

    @Test
    public void test3(){
        String properties = System.getProperty("java.io.tmpdir");
        System.out.println(properties);
    }


    @Test
    public void test2(){
        Map<String, String> orderMap = new TreeMap<>();
        orderMap.put("bdd","ccc");
        orderMap.put("dasw","ee");
        orderMap.put("aeesd","scss");
        for(Map.Entry<String,String> entry:orderMap.entrySet()){
            System.out.println(entry.getKey());
        }
        System.out.println(JSONUtil.toJsonStr(orderMap));
    }

    @Test
    public void testStringToNumber(){
        String s = "123";
        Integer.parseInt(s);
    }
}
