package com.bigdata.hbase;

import cn.hutool.core.util.RandomUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.security.UserGroupInformation;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.PrivilegedAction;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @Auther: huangzhigao
 * @Date: 2020/3/18
 * @Description:
 */
public class HBaseUtil {

    /**
     * 获取表
     *
     * @param tableName
     * @return
     * @throws IOException
     */
    public static HTable getTable(String tableName) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "cluster-node-2,cluster-node-3,cluster-node-4");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.addResource("D://141krb/hbase-site.xml");
        conf.addResource("D://141krb/core-site.xml");
        Connection conn = ConnectionFactory.createConnection(conf);
        HTable table = (HTable) conn.getTable(TableName.valueOf(tableName));
        return table;
    }


    public static HTable getTestTable(String tableName) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "cluster-node-1,cluster-node-2,cluster-node-3");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.addResource("D://100krb/hbase-site.xml");
        conf.addResource("D://100krb/core-site.xml");
        Connection conn = ConnectionFactory.createConnection(conf);
        HTable table = (HTable) conn.getTable(TableName.valueOf(tableName));
        return table;
    }


    public static Connection getConn() throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "cluster-node-2,cluster-node-3,cluster-node-4");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.addResource("D://141krb/hbase-site.xml");
        conf.addResource("D://141krb/core-site.xml");
        Connection conn = ConnectionFactory.createConnection(conf);
        return conn;
    }


    public static void insert(Table table) throws IOException, InterruptedException {
        DecimalFormat format = new DecimalFormat();
        format.applyPattern("0000");
        List<Put> puts = new ArrayList<>();
        for (int i = 1001; i < 2000; i++) {
            String prefix = format.format(System.currentTimeMillis() % 3) + "-";
            byte[] rowKey = Bytes.add(Bytes.toBytes(prefix), Bytes.toBytes(String.valueOf(System.currentTimeMillis())));
            Put put = new Put(rowKey);
            put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes("wuxia" + i));
            put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("age"), Bytes.toBytes("ppp" + i));
            puts.add(put);
            if (puts.size() >= 10000) {
                table.put(puts);
                puts.clear();
            }
            Thread.sleep(1);
        }
        table.put(puts);
    }


    @Test
    public void insert1() throws IOException, InterruptedException {
        HTable table = getTable("hzg:student");
        DecimalFormat format = new DecimalFormat();
        format.applyPattern("0000");
        List<Put> puts = new ArrayList<>();
        for (int i = 3000; i < 3005; i++) {
            String prefix = format.format(System.currentTimeMillis() % 3) + "-";
            byte[] rowKey = Bytes.add(Bytes.toBytes("0002-"), Bytes.toBytes(String.valueOf(System.currentTimeMillis())));
            Put put = new Put(rowKey);
            put.addColumn(Bytes.toBytes("sindex"), Bytes.toBytes("name"), Bytes.toBytes("wuxia" + i));
            put.addColumn(Bytes.toBytes("sindex"), Bytes.toBytes("age"), Bytes.toBytes("ppp" + i));
            puts.add(put);
            if (puts.size() >= 10000) {
                table.put(puts);
                puts.clear();
            }
            Thread.sleep(1);
        }
        table.put(puts);
    }


    @Test
    public void getTestTablse() throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "cluster-node-1,cluster-node-2,cluster-node-3");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.addResource("D://100krb/hbase-site.xml");
        conf.addResource("D://100krb/core-site.xml");
        Connection conn = ConnectionFactory.createConnection(conf);
        System.out.println(conn);
    }


    @Test
    public void insert2() throws IOException, InterruptedException {
        HTable table = getTable("hzg:test122");
        List<Put> puts = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("null-null");
        Put put = new Put(Bytes.toBytes(stringBuilder.toString()));
//        Put put = new Put(Bytes.add(Bytes.toBytes(stringBuilder.toString()), Bytes.toBytes(String.valueOf(System.currentTimeMillis()))));
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes("wuxia"));
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("age"), Bytes.toBytes("ppp"));
        puts.add(put);
        table.put(puts);
        table.close();
    }


    @Test
    public void getCount() throws IOException {
        HTable table = getTable("hzg:test122");
        Connection conn = getConn();

    }


    @Test
    public void insert5() throws IOException, InterruptedException {
        HTable table = getTable("hzg:labelTest8");
        DecimalFormat format = new DecimalFormat();
        format.applyPattern("0000");
        List<Put> puts = new ArrayList<>();
        for (int i = 0; i < 3000000; i++) {
            String rowKey = "T" + i + "000000";
            Put put = new Put(Bytes.toBytes(rowKey));
            put.addColumn(Bytes.toBytes("sindex"), Bytes.toBytes("name"), Bytes.toBytes("wuxia" + i));
            put.addColumn(Bytes.toBytes("sindex"), Bytes.toBytes("age"), Bytes.toBytes("ppp" + i));
//            String rowKey = ""
            puts.add(put);
            if (puts.size() >= 10000) {
                table.put(puts);
                puts.clear();
            }
            Thread.sleep(1);
        }
        table.put(puts);
    }


    @Test
    public void insert3() throws IOException, InterruptedException {
        HTable table = getTable("hzg:labelTest3");
        table.setAutoFlush(true, false);
        List<Put> puts = new ArrayList<>();
        for (int i = 0; i < 1022; i++) {
            Put put = new Put(Bytes.add(Bytes.toBytes("00001" + RandomUtil.randomString(4)), Bytes.toBytes(String.valueOf(System.currentTimeMillis()))));
            put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes("wuxia"));
            put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("age"), Bytes.toBytes("ppp"));
            table.put(put);
        }
        table.close();
    }


    @Test
    public void deleteTest() throws IOException {
        HTable table = getTestTable("labelNamespace:oss_label_table");
        System.out.println(table);
        DecimalFormat format = new DecimalFormat();
        format.applyPattern("0000000000");
        String startRow = "L_" + format.format(589);
        String stopRow = "L_" + format.format(589 + 1);
        Scan scan = new Scan().withStartRow(Bytes.toBytes(startRow), true).withStopRow(Bytes.toBytes(stopRow),
                false);
        ResultScanner rscan = table.getScanner(scan);
        Iterator<Result> itr = rscan.iterator();
        while (itr.hasNext()) {
            Result result = itr.next();
            System.out.println(result);
        }
    }

    @Test
    public void creatTables() throws IOException, InterruptedException {
        Connection conn = getConn();
        Admin admin = conn.getAdmin();
        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf("hzg:test122"));
        HColumnDescriptor hColumnDescriptor = new HColumnDescriptor("info");
        hTableDescriptor.addFamily(hColumnDescriptor);
        admin.createTable(new HTableDescriptor(hTableDescriptor));
    }

    @Test
    public void deleteTable() throws IOException, InterruptedException {
        Connection conn = getConn();
        Admin admin = conn.getAdmin();
        admin.disableTable(TableName.valueOf("hzg:labelTest3"));
        admin.deleteTable(TableName.valueOf("hzg:labelTest3"));
    }


    @Test
    public void scanTest() throws IOException, InterruptedException {
        HTable table = getTable("labelNamespace:oss_label_table");
        DecimalFormat format = new DecimalFormat();
        format.applyPattern("0000000000");
        String startRow = "T_" + format.format(589);
        String stopRow = "T_" + format.format(590);
        Scan scan = new Scan().withStartRow(Bytes.toBytes(startRow), true).withStopRow(Bytes.toBytes(stopRow),
                false);
        scan.setMaxResultSize(10000);
        scan.setLimit(100000);
        scan.setCaching(-1);
        ResultScanner scanner = table.getScanner(scan);
        Iterator<Result> iterator = scanner.iterator();
        List<Delete> list = new ArrayList<>(100000);
        int i = 0;
        while (iterator.hasNext()) {

            Result next = iterator.next();
            list.add(new Delete(next.getRow()));
//            String rowKey = Bytes.toString(next.getRow());
            i++;

//            System.out.println(next.getRow());
        }
        System.out.println("总共读取任务数据为:" + i);
        long startTime = System.currentTimeMillis();
        table.delete(list);
        System.out.println("删除耗时:" + (System.currentTimeMillis() - startTime));
    }

    @Test
    public void scanTest11() throws IOException {
        HTable table = getTable("enc_lzc_test:hbasetest1");
        Scan scan = new Scan();
        scan.setRowOffsetPerColumnFamily(20);
        scan.setMaxResultsPerColumnFamily(30);
        scan.setLimit(10);
        ResultScanner scanner = table.getScanner(scan);
        Iterator<Result> iterator = scanner.iterator();

        while (iterator.hasNext()) {
            System.out.println("aaa");
            Result next = iterator.next();
        }
    }

    @Test
    public void test111() throws IOException {
        Connection connection = null;
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "cluster-node-2,cluster-node-3,cluster-node-4");
        InputStream hbaseSite = new FileInputStream("D://141krb/hbase-site.xml");
        InputStream coreSite = new FileInputStream("D://141krb/core-site.xml");
        conf.addResource(hbaseSite);
        conf.addResource(coreSite);
        conf.set("kerberos.principal", "ossuser/cluster-node-2@HADOOP.COM");
        // 使用用户keytab文件认证
        conf.set("keytab.file", "D://141krb/ossuser.keytab");
        if ("kerberos".equals(conf.get("hbase.security.authentication"))) {
            UserGroupInformation.setConfiguration(conf);
            UserGroupInformation.loginUserFromKeytab(conf.get("kerberos.principal"), conf.get("keytab.file"));
            try {
                // 登录
                UserGroupInformation.loginUserFromKeytab(conf.get("kerberos.principal"), conf.get("keytab.file"));
            } catch (IOException e) {
                throw new RuntimeException("Unabled to login.");
            }
            UserGroupInformation ugi = UserGroupInformation.getLoginUser();
            connection = ugi.doAs(new PrivilegedAction<Connection>() {

                @Override
                public Connection run() {
                    try {
                        return ConnectionFactory.createConnection(conf);
                    } catch (IOException e) {
                        return null;
                    }
                }
            });
        } else {
            connection = ConnectionFactory.createConnection(conf);
        }

        TableName tableName = TableName.valueOf("enc_lzc_test:hbasetest1");
        Table table = connection.getTable(tableName);
        Scan scan = new Scan();
        scan.setRowOffsetPerColumnFamily(1);
        scan.setMaxResultsPerColumnFamily(10);
        scan.setLimit(10);
        ResultScanner scanner = table.getScanner(scan);
        Iterator<Result> iterator = scanner.iterator();
        while (iterator.hasNext()){
            System.out.println("aaa");
            Result next = iterator.next();
        }
    }


    @Test
    public void addFamily() throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "cluster-node-2,cluster-node-3,cluster-node-4");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
//        conf.addResource("D://141krb/hbase-site.xml");
//        conf.addResource("D://141krb/core-site.xml");
        Connection conn = ConnectionFactory.createConnection(conf);
        Admin admin = conn.getAdmin();
//        HTable table = (HTable) conn.getTable(TableName.valueOf("hzg:student"));
//        admin.disableTable(TableName.valueOf("hzg:student"));
//        HTableDescriptor tableDescriptor = table.getTableDescriptor();
//        admin.modifyTable(TableName.valueOf("hzg:student"),tableDescriptor);
//        HColumnDescriptor columnDescriptor = new HColumnDescriptor("sindex");
//        tableDescriptor.addFamily(columnDescriptor);
//        admin.enableTable(TableName.valueOf("hzg:student"));

        TableName tableName = TableName.valueOf("hzg:student");
        admin.disableTable(tableName);
        admin.deleteColumn(tableName, Bytes.toBytes("sindex"));
//        admin.modifyColumn(tableName,new HColumnDescriptor("sindex"));
        admin.enableTable(tableName);
        admin.close();
    }
}
