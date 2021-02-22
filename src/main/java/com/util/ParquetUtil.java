package com.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.column.ParquetProperties;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.example.data.simple.SimpleGroupFactory;
import org.apache.parquet.hadoop.ParquetFileWriter;
import org.apache.parquet.hadoop.ParquetReader;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.example.ExampleParquetWriter;
import org.apache.parquet.hadoop.example.GroupReadSupport;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.MessageTypeParser;


import java.io.IOException;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/2
 * @Description:
 */
@Slf4j
public class ParquetUtil {
    private static String schemaStr = "message schema {"
            + "optional int64 user_id;"
            + "optional binary account (UTF8);"
            + "optional int32 age;"
            + "optional int32 sex;"
            + "optional binary description (UTF8);"
            + "optional boolean deleted;"
            + "optional binary create_time (UTF8);}";

    private static MessageType schema = MessageTypeParser.parseMessageType(schemaStr);

    private static String inputPath = "F://5704_ysk_xzrzcslcp_test_mysql_1-1575253811690.parquet";


    /**
     * 读取parquet文件
     *
     * @throws IOException
     */
    private static void parquetReader(String inputPath) throws IOException {
        GroupReadSupport readSupport = new GroupReadSupport();
        ParquetReader<Group> reader = new ParquetReader<>(new Path(inputPath), readSupport);
        Group line;
        while ((line = reader.read()) != null) {
            System.out.println(line.toString());
        }
        reader.close();
    }
//
//    private static void parquetWriter() throws IOException {
//        ExampleParquetWriter.Builder builder = ExampleParquetWriter
//                .builder(new Path(outputPath))
//                .withWriteMode(ParquetFileWriter.Mode.CREATE)
//                .withWriterVersion(ParquetProperties.WriterVersion.PARQUET_1_0)
//                .withCompressionCodec(CompressionCodecName.SNAPPY)
//                //.withConf(configuration)
//                .withType(schema);
//
//        ParquetWriter<Group> writer = builder.build();
//        SimpleGroupFactory groupFactory = new SimpleGroupFactory(schema);
//        String[] userLog = {"1001", "james", "6265548", "18", "1", "good man", "false", "2019-02-06 00:00:00"};
//
//        for (int i = 0; i < 1000; i++) {
//            writer.write(groupFactory.newGroup()
//                    .append("userId", Long.parseLong(userLog[0]))
//                    .append("account", userLog[1] + i)
//                    .append("password", userLog[2])
//                    .append("age", Integer.parseInt(userLog[3]))
//                    .append("sex", Integer.parseInt(userLog[4]))
//                    .append("description", userLog[5])
//                    .append("deleted", Boolean.parseBoolean(userLog[6]))
//                    .append("createTime", userLog[7]));
//        }
//        writer.close();
//    }


    public static void main(String[] args) throws IOException {
//        parquetWriter();
        parquetReader(inputPath);
    }

}