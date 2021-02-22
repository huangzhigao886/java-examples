package velocity;

import com.velocity.BeanDef;
import com.velocity.flinkJson.FlinkBean;
import com.velocity.flinkJson.VelocityUtils;
import com.velocity.flinkJson.bean.ComponentsBean;
import com.velocity.flinkJson.bean.ConfigBean;
import com.velocity.flinkJson.bean.ConfigMethodDef;
import com.velocity.flinkJson.bean.HeaderBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: huangzhigao
 * @Date: 2020/3/30
 * @Description:
 */
public class FlinkJsonTest {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        List<BeanDef> componentList = new ArrayList<>();
        FlinkBean flinkBean = new FlinkBean();
        flinkBean.setConfigBean(builderConfig());
        flinkBean.setHeaderBean(buildHeader());
//        componentList.add(builderComponent(""))
        flinkBean.setComponentsBeanList(componentList);
//        map.put("headers", headerBean);
//        map.put("configs", configBean);

        String flinkJson = VelocityUtils.fromTemplate("vm/flink.vm", map);
        System.out.println(flinkJson);
    }


    /**
     * 创建头部
     *
     * @return
     */
    public static HeaderBean buildHeader() {
        HeaderBean headerBean = new HeaderBean();
        headerBean.setCheckPointInterval(0);
        headerBean.setJobType("BATCH");
        headerBean.setJobName("test");
        headerBean.setOnErrorPolicy("SKIP");
        return headerBean;
    }


    /**
     * 创建config
     */
    public static ConfigBean builderConfig() {
        ConfigBean configBean = new ConfigBean();
        configBean.setStoretype("Redis");
        configBean.setCacheType("Redis");
        configBean.setOverwrite("true");
        configBean.setSourceSerial("true");
        configBean.setPropPath("/hdfs");
        return configBean;
    }

    public static BeanDef builderComponent(String id, String className, List<Object> constructArgs) {
        BeanDef beanDef = new BeanDef();
        beanDef.setId(id);
        beanDef.setClassName(className);

        beanDef.setConstructArgsList(null);
//        beanDef.setConfigMethodsList(addField());
        return beanDef;
    }


    public static List<ConfigMethodDef> addField() {
        List<ConfigMethodDef> methodArgs = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ConfigMethodDef configMethodDef = new ConfigMethodDef();
            configMethodDef.setName("addField");
            List<Object> args = new ArrayList<>();
            args.add("field" + i);
            args.add("STRING");
            configMethodDef.setArgs(args);
        }
        return methodArgs;
    }
}
