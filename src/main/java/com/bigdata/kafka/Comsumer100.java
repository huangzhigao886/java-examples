package com.bigdata.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/24
 * @Description:
 */
@Slf4j
public class Comsumer100 {

    public static void main(String[] args) throws IOException {
        System.setProperty("java.security.krb5.conf", "D://100krb/krb5.conf");
        //获取客户端的认证信息
        String kafkaClient = getInfo("D://100krb/daas_jaas.conf", "KafkaClient");

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "url:9092,url:9092,url:9092,url.146.121:9092");
        //kafka的kerberos认证
        properties.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
        properties.put(SaslConfigs.GSSAPI_MECHANISM, "GSSAPI");
        properties.put(SaslConfigs.SASL_KERBEROS_SERVICE_NAME, "kafka");
        //kafka客户端的认证信息
        properties.put(SaslConfigs.SASL_JAAS_CONFIG, kafkaClient);


        setConsumerProp(properties);
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer(properties);
        kafkaConsumer.partitionsFor("topic-search-label-to-es");
//        //消费者订阅topic
//        kafkaConsumer.subscribe(Arrays.asList("topic-label01"));
//
//        //消费数据
//        while (true) {
//            ConsumerRecords<String, String> poll = kafkaConsumer.poll(1000);
//            for (ConsumerRecord<String, String> record : poll)
//                System.out.println("Partition: " + record.partition() + " Offset: " + record.offset() + " Value: " + record.value() + " ThreadID: " + Thread.currentThread().getId());
//        }
    }


    /**
     * 设置消费者属性
     *
     * @param prop
     */
    public static void setConsumerProp(Properties prop) {
        //consumer的key，value需要反序列化，key代表分区数，value是真实数据
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        //设置消费ID，ID从zookeeper下的kafka/broker/ids中抓取
        prop.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "ccccc");
        //从头开始消费
        prop.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
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
