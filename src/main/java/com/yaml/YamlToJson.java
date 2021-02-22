package com.yaml;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONObject;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;

/**
 * @Auther: huangzhigao
 * @Date: 2020/3/23
 * @Description:
 */
public class YamlToJson {
    public static void main(String[] args) {
        String ymlStr = "name: \"JoinJob\"\n" +
                "jobType: BATCH\n" +
                "config:\n" +
                "      cache.type: \"PROPFILE\"\n" +
                "      cache.prop.path: \"hdfs://cdhmaster:8020/encdata/dataengine/propfile\"\n" +
                "      data.source.serial: \"false\"\n" +
                "components:\n" +
                "    - id: \"jdbcParam\"\n" +
                "      className: \"cn.encdata.cloud.dataengine.core.sources.batch.jdbc.JdbcSourceInputParam\"\n" +
                "      constructorArgs: [\"root\", \"MTIzNDU2\", MySql, \"jdbc:mysql://url.146.82:3306/zhouhy1028?useCursorFetch=true&defaultFetchSize=1000\", \"sex\",\"\",\"\",\"\"]\n" +
                "    - id: \"mysql\"\n" +
                "      className: \"cn.encdata.cloud.dataengine.schema.Schema\"\n" +
                "      configMethods:\n" +
                "        - name: \"addField\"\n" +
                "          args: [\"code\", STRING]\n" +
                "        - name: \"addField\"\n" +
                "          args: [\"value\", STRING]\n" +
                "    - id: \"output\"\n" +
                "      className: \"cn.encdata.cloud.dataengine.schema.Schema\"\n" +
                "      configMethods:\n" +
                "        - name: \"addField\"\n" +
                "          args: [\"code1\", STRING]\n" +
                "        - name: \"addField\"\n" +
                "          args: [\"value1\", STRING]        \n" +
                "\n" +
                "sources:\n" +
                "    - id: \"callData\"\n" +
                "      className: \"cn.encdata.cloud.dataengine.core.sources.batch.jdbc.JdbcSourceInputFormat\"\n" +
                "      constructorArgs: [\"ref:jdbcParam\", \"ref:mysql\"]\n" +
                "      parallelism: 1\n" +
                "\n" +
                "operators:\n" +
                "    - id: \"map\"\n" +
                "      className: \"cn.encdata.cloud.dataengine.core.operators.EncMapFunction\"\n" +
                "      constructorArgs:\n" +
                "        - code1: \"code\"\n" +
                "          value1: \"value\"\n" +
                "      configMethods:\n" +
                "        - name: \"setOutputSchema\"\n" +
                "          args: [\"ref:output\"]\n" +
                "      parallelism: 1    \n" +
                "sinks:\n" +
                "    - id: \"write2File\"\n" +
                "      className: \"cn.encdata.cloud.dataengine.core.sinks.batch.parquet.ParquetSinkOutPutFormat\"\n" +
                "      constructorArgs: [\"hdfs://ha-nameservice/encdata/impala/storage/originald/ysk_ddycs_sex4\"]\n" +
                "      parallelism: 1\n" +
                "\n" +
                "streams:\n" +
                "    - name: \"readCallData\"\n" +
                "      from: \"callData\"\n" +
                "      to: \"map\"\n" +
                "      operation: MAP\n" +
                "    - name: \"write to file\"\n" +
                "      from: \"map\"\n" +
                "      to: \"write2File\"\n";
        Yaml yaml = new Yaml();
        Map<String, Object> map = (Map<String, Object>) yaml.load(ymlStr);
        map.put("name","aaa");
        String dump = yaml.dump(map);
        JSONObject jsonObject = new JSONObject(map);
        System.out.println(jsonObject.toString());

    }
}
