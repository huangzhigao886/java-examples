package hbase;

import cn.hutool.core.util.ZipUtil;
import com.bigdata.hbase.HBaseUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.Pair;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2020/3/18
 * @Description:
 */
public class HbaseTest {

    @Test
    public void testInsertData() throws IOException, InterruptedException {
//        HTable table = HBaseUtil.getTable("hzg:region4");
//        HBaseUtil.insert1(table);
    }

    @Test
    public void test(){
        String path = "/encdata/datengine/aa.txt";
        String[] split = path.split("/");
        System.out.println(split[split.length-1]);
    }

    @Test
    public void testUnZip(){
        System.out.println(Charset.defaultCharset());
        File gbk = ZipUtil.unzip("D://AA.zip", Charset.defaultCharset());
        System.out.println(gbk);
    }

    @Test
    public void testGetRegions() throws IOException {
        HTable table = HBaseUtil.getTable("hzg:region4");
        Pair<byte[][], byte[][]> keys = table.getRegionLocator().getStartEndKeys();
        for (int i = 0; i < keys.getFirst().length; i++) {
            byte[][] startKeys = keys.getFirst();
            byte[][] endKeys = keys.getSecond();
            System.out.println(Bytes.toString(startKeys[i]));
            System.out.println(Bytes.toString(endKeys[i]));
        }
    }

    @Test
    public void testScan() throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "cluster-node-1,cluster-node-2,cluster-node-3");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.addResource("D://hbase/hbase-site.xml");
        conf.addResource("D://hbase/core-site.xml");
        conf.addResource("D://hbase/hdfs-site.xml");
        Connection conn = ConnectionFactory.createConnection(conf);
        Admin admin = conn.getAdmin();
        Table table = conn.getTable(TableName.valueOf("hzg:region4"));
        List<HRegionInfo> lr = admin.getTableRegions(TableName.valueOf("hzg:region4"));
        Result r = null;

        // 遍历表的每个region
        Iterator<HRegionInfo> ir = lr.iterator();

        while (ir.hasNext()) {
            HRegionInfo next = ir.next();
            ResultScanner scanner = null;
            byte[] startRowkey = next.getStartKey();
            Scan sc = new Scan();
            if (startRowkey.length != 0) {
                sc.withStartRow(startRowkey);
            }
            sc.setBatch(1);
            try {
                scanner = table.getScanner(sc);
                r = scanner.next();
                System.out.println(Bytes.toString(r.getRow()));
                String s = Bytes.toString(r.getValue(Bytes.toBytes("info"), Bytes.toBytes("age")));
                System.out.println(s);
                scanner.close();
            } catch (Exception e) {
            }
        }
    }

    @Test
    public void testRange() throws IOException {
        HTable table = HBaseUtil.getTable("hzg:region4");
        Scan scan = new Scan();
        scan.withStartRow(Bytes.toBytes("0002-1584844862213"), false);
        scan.withStopRow(Bytes.toBytes("0002-9999999999999"), false);
        Iterator<Result> iterator = table.getScanner(scan).iterator();
        while (iterator.hasNext()) {
            Result next = iterator.next();
            System.out.println(Bytes.toString(next.getRow()));
        }
//        System.out.println(iterator.hasNext());

    }
}
