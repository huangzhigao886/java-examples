package com.bigdata.hbase;

import cn.hutool.core.util.RandomUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.Pair;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.apache.hadoop.hbase.filter.FilterList.Operator.MUST_PASS_ALL;

/**
 * @Auther: huangzhigao
 * @Date: 2020/3/9
 * @Description:
 */
public class HbaseDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "cluster-node-1,cluster-node-2,cluster-node-3");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.addResource("D://141/hbase-site.xml");
//        conf.addResource("D://hbase/core-site.xml");
//        conf.addResource("D://hbase/hdfs-site.xml");
        Connection conn = ConnectionFactory.createConnection(conf);
        HTable table = (HTable) conn.getTable(TableName.valueOf("lzptest"));

        System.out.println(table);
        Scan scan = new Scan();
////        List<Filter> list = new ArrayList<>();
//        Filter filter1 = new SingleColumnValueFilter(Bytes.toBytes("info"), Bytes.toBytes("lzp003"), CompareFilter.CompareOp.EQUAL, new BinaryPrefixComparator(Bytes.toBytes("lzp003")));
////        list.add(filter1);
//        FilterList list2 =new FilterList(MUST_PASS_ALL);
//        list2.addFilter(filter1);
//        scan.setFilter(list2);
//        Iterator<Result> iterator = table.getScanner(scan).iterator();
//        while (iterator.hasNext()) {
//            Result next = iterator.next();
//            System.out.println(Bytes.toString(next.getRow()));
//            System.out.println(Bytes.toString(next.getValue(Bytes.toBytes("lzp"), Bytes.toBytes("lzp003"))));
//        }


        scan.withStartRow(Bytes.toBytes("00011584432420571"), false);
        scan.withStopRow(Bytes.toBytes("00019223372036854775807"));
        Iterator<Result> iterator = table.getScanner(scan).iterator();
        boolean b = iterator.hasNext();
        while (iterator.hasNext()){
            Result next = iterator.next();
            System.out.println(next.getValue(Bytes.toBytes("info"),Bytes.toBytes("age")));
        }
//        scan.addColumn(Bytes.toBytes("info"), Bytes.toBytes("age"));
//        scan.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"));
//        System.out.println(table.getEndKeys());
//        byte[][] endKeys = table.getEndKeys();
////        System.out.println;
//        endKeys[1][2] = 1;
//        for (int i = 0; i < endKeys.length; i++) {
//
////            System.out.println(Bytes.toString(endKeys[i][0]));
////            System.out.println(Bytes.toInt(endKeys[i]));
////            System.out.println(Bytes.toLong(endKeys[i]));
////            System.out.println(Bytes.toDouble(endKeys[i]));
//        }
////        Pair<byte[][], byte[][]> startEndKeys = table.getRegionLocator().getStartEndKeys();
////        System.out.println(Bytes.toString((startEndKeys.getFirst()));
//        System.out.println("aaa");

//        Scan scan = new Scan();
////        TimeRange timeRange = scan.getTimeRange();
////        System.out.println(timeRange);
//
//        ResultScanner scanner = table.getScanner(scan);
//        Iterator<Result> iterator = scanner.iterator();
////
//        while (iterator.hasNext()) {
//            Result next = iterator.next();
//            System.out.println(Bytes.toString(next.getValue(Bytes.toBytes("info"), Bytes.toBytes("age"))));
//            System.out.println(Bytes.toString(next.getValue(Bytes.toBytes("info"), Bytes.toBytes("name"))));
//            System.out.println(Bytes.toString(next.getValue(Bytes.toBytes("info"), Bytes.toBytes("id"))));
//
//        }
    }


}
