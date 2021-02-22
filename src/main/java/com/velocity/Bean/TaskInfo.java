package com.velocity.Bean;

import com.velocity.BeanDef;
import lombok.Data;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/18
 * @Description:
 */
@Data
public class TaskInfo {

    private String jobName;

    private String jobType;

    private List<SchemaBean> schemaList;

    private List<BeanDef> jdbcSourceParamList;

    private List<BeanDef> sourceList;

    private List<BeanDef> joinOperatorList;

    private List<MapOperatorBean> mapOperatorList;

    private List<BeanDef> sinkList;

    private List<StreamDef> streamList;

    public static void main(String[] args) {
        String pattern="2015.*";
        System.out.println(Pattern.matches(pattern,"2015_123.txt"));
        System.out.println(Pattern.matches(pattern,"201501.txt"));
    }

}
