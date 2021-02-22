package com.velocity.flinkJson;

import com.velocity.BeanDef;
import com.velocity.flinkJson.bean.ComponentsBean;
import com.velocity.flinkJson.bean.ConfigBean;
import com.velocity.flinkJson.bean.HeaderBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2020/3/30
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlinkBean {


    private String name;
    /**
     * 头部参数
     */
    private HeaderBean headerBean;

    private ConfigBean configBean;

    private List<BeanDef> componentsBeanList;

}
