package aviatorFunction;

import cn.hutool.core.map.CaseInsensitiveMap;
import com.expressionEnginee.function.*;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import org.junit.Test;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionTest {

    /**
     * 简单使用Avitator
     */
    @Test
    public void test() {
        //多表达式解析,结果只展示最后一个表达式，如果需要得到中间的结果可以用print
        Map<String, Object> env = new HashMap<>();
        env.put("aa", null);
        System.out.println(AviatorEvaluator.execute("aa + 'b'", env));
//        System.out.println(AviatorEvaluator.execute("print('hello word ');5+6+7;100-1"));
    }


    @Test
    public void test1() {
        String expression = "\\d+\\.?\\d*";
        char[] chars = expression.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars1 = "\\".toCharArray();
        for (char value : chars) {
            if (chars1[0] == value) {
                stringBuilder.append("\\\\");
            } else {
                stringBuilder.append(value);
            }
        }
        System.out.println(stringBuilder.toString());
    }

    @Test
    public void test2() {
//        Map<String,Object> env = new HashMap<>();
//        env.put("aa",null);
//        System.out.println(AviatorEvaluator.execute("aa < 3",env));
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("aa").append("bb");
//        stringBuilder.append("cc");
//        System.out.println(stringBuilder.toString());
        String s = "abc&&cde&&ee";
    }


    @Test
    public void add() {
//        AviatorEvaluator.addFunction(new AddFunction());
//        System.out.println(AviatorEvaluator.execute(""));
        Pattern pattern = Pattern.compile("(?:(\\d+))?\\s?([a-zA-Z]+)?.+");
        String source = "2133 fdsdee4333";
        Matcher matcher = pattern.matcher(source);
        if (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.println("group " + i + ":" + matcher.group(i));
            }
        }

//            System.out.println(matcher.groupCount());


    }


    @Test
    public void decode() {
        AviatorEvaluator.addFunction(new DecodeFunction());
//        System.out.println(AviatorEvaluator.execute("string.length('5')==1"));
//        System.out.println(AviatorEvaluator.execute("5==5"));
        Map<String, Object> env = new HashMap<>();
        env.put("hello", "aaac");
        System.out.println(AviatorEvaluator.execute("decode(string.length(hello)==3,'hello',string.length(hello)==5,'word','0')", env));
//        System.out.println(AviatorEvaluator.execute("decode(false,1,false,2,false,3,4)"));
    }


    @Test
    public void contact() {
        Map<String,Object> env = new HashMap<>();
        env.put("key",1);
        System.out.println(AviatorEvaluator.execute("key == '1'"));

//        AviatorEvaluator.addFunction(new ContanctFunction());
//        System.out.println(AviatorEvaluator.execute("concat('a','b')"));
    }

    @Test
    public void regex() {
        AviatorEvaluator.addFunction(new RegexFunction());
        System.out.println(AviatorEvaluator.execute("regex('a','\\d+\\.?\\d*')"));
    }

    @Test
    public void regex1() {
        Map<String, Object> aa = new HashMap<>();
        aa.put("name", 'a');
        System.out.println(AviatorEvaluator.execute("aa + '男'", aa));
    }

    @Test
    public void testLower() {
        AviatorEvaluator.addFunction(new LowerFuction());
        System.out.println(AviatorEvaluator.execute("low('NAME')"));
    }

    @Test
    public void testUpper() {
        AviatorEvaluator.addFunction(new UpperFunction());
        Map<String, Object> env = new HashMap<>();
        env.put("name", "zhanglIang");
        System.out.println(AviatorEvaluator.execute("upper(name1)", env));
    }

    @Test
    public void testSubstr() {
        AviatorEvaluator.addFunction(new SubstrFuntion());
        System.out.println(AviatorEvaluator.execute("substr('l love you',121,20)"));
    }

    @Test
    public void testLength() {
        AviatorEvaluator.addFunction(new LengthFuntion());
        Map<String, Object> env = new HashMap<>();
        env.put("name", "zhanglIang");
        System.out.println(AviatorEvaluator.execute("length(name1)", env));
    }

    @Test
    public void testTrim() {
        AviatorEvaluator.addFunction(new TrimFuntion());
        Map<String, Object> env = new HashMap<>();
        env.put("name", " zhanglIang  ");
        String value = AviatorEvaluator.execute("trim(name)", env).toString();
        System.out.println(value.length());

    }


    @Test
    public void testStrToDate() {
//        AviatorEvaluator.addFunction(new String2TimestampFunction());
        System.out.println(AviatorEvaluator.execute("string_to_date('2017-01-06 10:20:30','%Y-%m-%d %H:%i:%s')"));
    }


    @Test
    public void testRound() {
        AviatorEvaluator.addFunction(new RoundFunction());
        System.out.println(AviatorEvaluator.execute("round(3.1445,10)"));
        System.out.println(AviatorEvaluator.execute("1>0 && 1<4"));
    }

    @Test
    public void testList() {
        AviatorEvaluator.addFunction(new ListFuntion());
//        System.out.println(AviatorEvaluator.execute("list('1','2','3','4')"));
        List list = (List) AviatorEvaluator.execute("list('1','2','3','4'))");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }


    @Test
    public void testInclude() {
        Map<String, Object> env = new HashMap<>();
        env.put("id", 1);
        AviatorEvaluator.addFunction(new ListFuntion());
//        AviatorEvaluator.addFunction(new IsIncludeFuntion());
        System.out.println(AviatorEvaluator.execute("in(id,list('1','2','3','4'))", env));
    }

    @Test
    public void testLike() {
        CaseInsensitiveMap<String, Object> env = new CaseInsensitiveMap<>();
        env.put("Rname", "李四");
        System.out.println(AviatorEvaluator.execute("RName = ~/'.*张三.*'/", env));

    }

    @Test
    public void test_string_to_date() {
        AviatorEvaluator.addFunction(new String2DateFunction());
        System.out.println(AviatorEvaluator.execute("str_to_date('03/02/1995','dd-MM-yyyy')"));
    }


    @Test
    public void test_isnull() {

        Map<String,List<String>>  map = new HashMap<>();
        if(null == map.get("1")){
            map.put("1", Stream.of("1").collect(Collectors.toList()));
        }
        map.get("1").add("2");
        System.out.println(map);
//        AviatorEvaluator.addFunction(new IsNullFunction());
//        System.out.println(AviatorEvaluator.execute("isnull('data/id')"));

    }

    @Test
    public void test_is_in() {
        AviatorEvaluator.addFunction(new IsInFunction());
        System.out.println(AviatorEvaluator.execute("in('1','1','2','3','4','5')"));
    }

    @Test
    public void test_is_not_in() {
        AviatorEvaluator.addFunction(new IsNotInFunction());
        System.out.println(AviatorEvaluator.execute("notIn('4','1','2','3','4')"));
    }


    @Test
    public void test_between() {
        AviatorEvaluator.addFunction(new BetweenFunciton());
        System.out.println(AviatorEvaluator.execute("between(10,9.0,12)"));
    }


    @Test
    public void test_equal() {
        Map<String, Object> env = new HashMap<>();
        env.put("ID", "1");
        System.out.println(AviatorEvaluator.execute("ID == 1", env));
    }


}
