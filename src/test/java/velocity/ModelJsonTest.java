package velocity;

import com.modelJson.*;
import com.modelJson.utils.FlinkJsonUtils;
import com.velocity.flinkJson.VelocityUtils;
import com.velocity.flinkJson.bean.HeaderBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: huangzhigao
 * @Date: 2020/4/15
 * @Description:
 */
public class ModelJsonTest {
    public static void main(String[] args) {
        ModelBean modelBean = createFakeModel();
        HeaderBean headerBean = FlinkJsonUtils.buildHeader(modelBean);
        ConfigBean configBean = FlinkJsonUtils.buildConfig(modelBean);
//        ComponentDef componentDef = FlinkJsonUtils.buildComponent(modelBean);
        Map<String, Object> params = new HashMap<>();
        params.put("headers", headerBean);
        params.put("configs", configBean);
//        params.put("components",componentDef);

//        FlinkJsonUtils.

        String flinkJson = VelocityUtils.fromTemplate("/vm/model.vm", params);
        System.out.println(flinkJson);
    }


    /**
     * 构建假的model
     *
     * @return
     */
    public static ModelBean createFakeModel() {
        ModelBean modelBean = new ModelBean();
        modelBean.setModelName("model1");
        modelBean.setRunType("1");
        modelBean.setScheduleType("2");
        List<OperatorBean> operatorBeanList = new ArrayList<>();
        createSchema(operatorBeanList);
        modelBean.setOperators(operatorBeanList);
        return modelBean;
    }


    /**
     * 构建输入源
     *
     * @param operatorBeanList
     */
    public static void createSchema(List<OperatorBean> operatorBeanList) {

        //构建字段信息
        List<DataField> dataFields = new ArrayList<>();
        dataFields.add(new DataField("name", "STRING"));
        dataFields.add(new DataField("time", "TIMESTAMP", "2012-23-12"));
        dataFields.add(new DataField("n1", "DOUBLE", 2));
        for (int i = 0; i < 2; i++) {
            OperatorBean operatorBean = new OperatorBean();
            operatorBean.setOperatorName("source" + i);
            operatorBean.setOperatorType("source");
            operatorBean.setOutputSchema(dataFields);
            operatorBeanList.add(operatorBean);
        }


        //构建算子
        OperatorBean operatorBean = new OperatorBean();
        operatorBean.setOperatorName("operator" + 1);
        operatorBean.setOperatorType("operator_filter");
        operatorBean.setOutputSchema(dataFields);
        operatorBeanList.add(operatorBean);

        OperatorBean operatorBean1 = new OperatorBean();
        operatorBean1.setOperatorName("operator" + 2);
        operatorBean1.setOperatorType("operator_add");
        operatorBean1.setOutputSchema(dataFields);
        operatorBeanList.add(operatorBean1);
    }
}
