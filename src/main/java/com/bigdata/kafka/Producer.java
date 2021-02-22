package com.bigdata.kafka;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * kafka客户端
 *
 * @Auther: huangzhigao
 * @Date: 2019/12/22
 * @Description:
 */
@Slf4j
public class Producer {
    public static void main(String[] args) throws IOException {
        //本地设置krb的文件，可以在启动命令时设置 VM options:-Djava.security.krb5.conf=F:///kafka/krb5.conf与下面的用法一致
        System.setProperty("java.security.krb5.conf", "D://141krb/krb5.conf");
        //获取客户端的认证信息
        String kafkaClient = getInfo("D://141krb/daas_jaas.conf", "KafkaClient");

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "url:9092,url:9092,url:9092");

        //kafka的kerberos认证
        properties.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
        properties.put(SaslConfigs.GSSAPI_MECHANISM, "GSSAPI");
        properties.put(SaslConfigs.SASL_KERBEROS_SERVICE_NAME, "kafka");
        //kafka客户端的认证信息
        properties.put(SaslConfigs.SASL_JAAS_CONFIG, kafkaClient);

        setProducerProp(properties);
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer(properties);
        List<PartitionInfo> kafkaDemo = kafkaProducer.partitionsFor("kafkaDemo2");
//        System.out.println(kafkaDemo);
        Map<String, Object> map = new HashMap<>();
        map.put("data_size", "1");
        map.put("owner", "1");
        map.put("access_datasource_id", "1");
        map.put("table_name", "1");
        map.put("table_chinese_name", "1");
        map.put("table_type", "1");
        map.put("create_time", "1");
        map.put("increment_data_size", "1");
        map.put("store_data_size", "1");
        map.put("increment_store_data_size", "1");
        map.put("source_comments", null);
        map.put("bussiness_label", "1");
//        map.put("id", "1");
//        map.put("remarks", "1");
//        map.put("user_id", "1");
        map.put("xxrksj", "1");
        map.put("bz", "1");
        map.put("zjlid", "1");
        map.put("is_collect", "1");
        map.put("is_able", "1");
        map.put("update_time", "1");
        String string = JSONObject.toJSONString(map, SerializerFeature.WriteMapNullValue);



        //生产者向topic生产数据
        for (int i = 1; i < 150; i++) {
            kafkaProducer.send(new ProducerRecord<String, String>("hzg123", string));
        }
        kafkaProducer.flush();
        kafkaProducer.close();

    }

    /**
     * 设置生产者属性
     *
     * @param properties
     */
    public static void setProducerProp(Properties properties) {
        //producer时需要序列化，key代表分区数，value是真实数据
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //设置ACK的规则，-1代表等待所有follower同步完数据后在返回ack
        properties.put(ProducerConfig.ACKS_CONFIG, "1");
    }


    /**
     * 获取kafkaClient信息
     *
     * @param filePath    jaas.conf路径
     * @param sectionName 获取的信息 KafkaClient
     * @return
     * @throws IOException
     */
    public static String getInfo(String filePath, String sectionName) throws IOException {
        File jaasFile = new File(filePath);
        if (!jaasFile.exists() || jaasFile.isDirectory()) {
            log.error("failed to open " + filePath);
        }

        String fileContent = IOUtils.toString(jaasFile.toURI());
        log.info("jaas config is \"{}\"", fileContent);
        if (StringUtils.isEmpty(fileContent)) {
            throw new IOException("jaas content is empty");
        }
        Pattern p = Pattern.compile(sectionName + "\\s*\\{(.*)\\};");
        Matcher m = p.matcher(fileContent.replaceAll("\\r\\n", "\n").replaceAll("\\n", "##CRLF##"));
        if (m.find()) {
            return m.group(1).replace("##CRLF##", "\n");
        }

        throw new IOException("Can not find " + sectionName + " in jaas config file " + filePath);
    }
}
