package com.jdbc;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.RandomUtil;
import com.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import sun.misc.IOUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static cn.hutool.db.meta.MetaUtil.getSchema;

/**
 * @description: jdbc客户端
 * @author: hzg
 * @create: 2019-09-02 11:17
 **/

@Slf4j
public class JdbcMain {

    public Connection getLocalCon() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/flink?useCursorFetch=true&defaultFetchSize=1000";
        Connection conn = DriverManager.getConnection(url, "root", "123456");
        return conn;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://url.146.83:3306/test?useCursorFetch=true&defaultFetchSize=1000";
        Connection conn = DriverManager.getConnection(url, "root", "3edcVFR$");
        System.out.println(conn);
//        PreparedStatement statement = conn.prepareStatement("insert into oracle_partition values(?,?)");
//        for(int i = 3000 ;i<10000;i++){
//            statement.setInt(1,i);
//            statement.setInt(2,i+1000);
//            statement.addBatch();
//        }
//
//        statement.executeBatch();
//        while (true) {
//            try {
//                conn.prepareStatement("select 1 from dual").execute();
//                Thread.sleep(60 * 1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        PreparedStatement statement = conn.prepareStatement("select name from testone");
//        ResultSet rs = statement.executeQuery();
//        while (rs.next()) {
//            Object object = rs.getString(1);
//            System.out.println(object.toString());
//            System.out.println(object.toString().length());
//        }

//        System.out.println(execute);
    }

    @Test
    public void getConn() throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.jdbc.Driver");
//        String url = "jdbc:mysql://localhost:3306?useCursorFetch=true&defaultFetchSize=1000";
//        String user = "root";
//        String password = "123456";
//        Connection connection = DriverManager.getConnection(url, user, password);
//        Statement statement = connection.createStatement();
//        statement.addBatch("create table `flink`.`hzg11` (`name` VARCHAR(255))");
//        statement.addBatch("create table `flink`.`hzg12` (`name` VARCHAR(255))");
//        statement.addBatch("insert into `flink`.`hzg11` values('cc')");
//        statement.executeBatch();
        String sql = "create table `flink`.`hzg11` (`name` VARCHAR(255));create table `flink`.`hzg11` (`name` VARCHAR(255));";
        String[] split = sql.split(";");
        System.out.println(split.length);
        System.out.println(split[0] + "第二条" + split[1]);

    }


    public static void closeCon(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                log.error("关闭数据库连接失败");
            }
        }
    }


    public static void getResult(Connection conn, String sql) throws SQLException {
        PreparedStatement psm = conn.prepareStatement(sql);
        ResultSet rs = psm.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();
        String name1 = metaData.getColumnLabel(1);
        String name2 = metaData.getColumnLabel(2);
        String name3 = metaData.getColumnLabel(3);
        String name4 = metaData.getColumnLabel(4);
        System.out.println(name1);
        System.out.println(name2);
        System.out.println(name3);
        System.out.println(name4);

        while (rs.next()) {
            System.out.println(rs.getObject(name1));
            System.out.println(rs.getObject(name2));
            System.out.println(rs.getObject(name3));
            System.out.println(rs.getObject(name4));

        }
//        rs.last();
//        int row = rs.getRow();
//        ResultSetMetaData metaData = rs.getMetaData();
//        System.out.println(row);
//        System.out.println(metaData.getColumnCount());
    }


    @Test
    public void insertData() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://url:3306/enc_original_database?rewriteBatchedStatements=true";
        Connection conn = DriverManager.getConnection(url, "root", "Bocom_123");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("insert into `test_2000W` values(");
        for (int i = 0; i < 61; i++) {
            stringBuilder.append("?,");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(")");
        PreparedStatement preparedStatement = conn.prepareStatement(stringBuilder.toString());
        for (int i = 13000000; i < 16000000; i++) {
            for (int j = 0; j < 61; j++) {
                if (j == 0) {
                    preparedStatement.setInt(1, i);
                } else {
                    preparedStatement.setString(j + 1, "LABLE" + RandomUtil.randomString(4));
                }
            }
            preparedStatement.addBatch();
            if (i % 10000 == 0) {
                preparedStatement.executeBatch();
                preparedStatement.clearBatch();
            }
        }
        preparedStatement.executeBatch();
    }


    @Test
    public void insertData12() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://url?rewriteBatchedStatements=true";
        Connection conn = DriverManager.getConnection(url, "root", "Bocom_123");
        PreparedStatement preparedStatement = conn.prepareStatement("insert into zhutiku_mysql.stu1112my(id,name,age)(select id,name,age from Hba.stu_my1112)");
        preparedStatement.execute();
    }


    @Test
    public void insertPicture() throws ClassNotFoundException, SQLException, FileNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/flink?rewriteBatchedStatements=true&characterEncoding=UTF-8";
        Connection conn = DriverManager.getConnection(url, "root", "123456");
        String sql = "insert into photo values(?)";
        BufferedInputStream in = FileUtil.getInputStream("D://图片库/aa.jpg");
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setBinaryStream(1, in);
        preparedStatement.execute();
    }

    @Test
    public void insertPicture1() throws ClassNotFoundException, SQLException, FileNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://url:3306/enc_original_database?rewriteBatchedStatements=true&characterEncoding=UTF-8";
        Connection conn = DriverManager.getConnection(url, "root", "Bocom_123");
        FileInputStream fileInputStream = new FileInputStream(new File("D://11.jpg"));
        byte[] bytes = IoUtil.readBytes(fileInputStream);
        String sq1 = "insert into photo2 values(?)";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sq1);
        for (int i = 0; i < 1000; i++) {
            preparedStatement1.setBytes(1, bytes);
            preparedStatement1.addBatch();
            if (i % 1000 == 0) {
                preparedStatement1.executeBatch();
            }
        }
        preparedStatement1.executeBatch();

//        BufferedInputStream in = FileUtil.getInputStream("D://图片库/aa.jpg");
//        PreparedStatement preparedStatement = conn.prepareStatement(sql);
//        preparedStatement.setBinaryStream(1, in);

    }


    @Test
    public void readPic() throws ClassNotFoundException, SQLException, FileNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://url:3306/enc_original_database?rewriteBatchedStatements=true&characterEncoding=UTF-8";
        Connection conn = DriverManager.getConnection(url, "root", "Bocom_123");
        String sql = "select XP from cas2edasa limit 1";
        ResultSet resultSet = conn.prepareStatement(sql).executeQuery();
        while (resultSet.next()) {
            FileOutputStream fileOutputStream = new FileOutputStream("D://18.jpg");
            Object object1 = resultSet.getObject(1);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream((byte[]) object1);
            IoUtil.copy(byteArrayInputStream, fileOutputStream, 1024);
        }
    }


    @Test
    public void LoadData() throws ClassNotFoundException, SQLException, FileNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306?rewriteBatchedStatements=true&characterEncoding=UTF-8";
        Connection conn = DriverManager.getConnection(url, "root", "123456");
        String sql = "select * from a";
        ResultSet resultSet = conn.getMetaData().getTables(null, "", "%", null);
        while (resultSet.next()) {
            String tableName = resultSet.getString("TABLE_NAME");
            System.out.println(tableName);
            if (tableName.equals("a")) {
                ResultSet rs = conn.getMetaData().getColumns(null, getSchema(conn), tableName.toUpperCase(), "%");
                while (rs.next()) {
                    Map map = new HashMap();
                    String colName = rs.getString("COLUMN_NAME");
                    map.put("code", colName);

                    String remarks = rs.getString("REMARKS");
                    if (remarks == null || remarks.equals("")) {
                        remarks = colName;
                    }
                    map.put("name", remarks);
                }
            }
        }
    }


    @Test
    public void LoadData1() throws ClassNotFoundException, SQLException, FileNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/flink?rewriteBatchedStatements=true&characterEncoding=UTF-8";
        Connection conn = DriverManager.getConnection(url, "root", "123456");
        String sql = "select * from a";
        ResultSet resultSet = conn.prepareStatement(sql).executeQuery();
        while (resultSet.next()) {

        }
    }


    @Test
    public void insertPeople() throws ClassNotFoundException, SQLException, FileNotFoundException {
        String[] strings = CommonUtils.generatorPeopleName();
        String[] idCards = CommonUtils.generatorIdCard();
        String[] froms = CommonUtils.generatorAddress();
        String[] trainsCode = CommonUtils.generatorTrainsCode();
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://url:3306/enc_original_database?rewriteBatchedStatements=true&characterEncoding=UTF-8";
        Connection conn = DriverManager.getConnection(url, "root", "Bocom_123");
        String sql = "insert into hengyanrl values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        for (int i = 0; i < 100; i++) {
            preparedStatement.setString(1, strings[i]);
            preparedStatement.setInt(2, RandomUtil.randomInt(15, 70));
            preparedStatement.setString(3, idCards[i % idCards.length]);
            preparedStatement.setString(4, froms[i % froms.length]);
            preparedStatement.setString(5, trainsCode[i % trainsCode.length]);
            preparedStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setTimestamp(7, new Timestamp(System.currentTimeMillis() + 1000 * 60 * 5));
            preparedStatement.setDouble(8, RandomUtil.randomDouble(35.7, 38.3));
            preparedStatement.setDouble(9, RandomUtil.randomDouble(10, 42));
            preparedStatement.setDouble(10, RandomUtil.randomDouble(10, 42));
            preparedStatement.setDouble(11, RandomUtil.randomDouble(10, 42));
            preparedStatement.setTimestamp(12, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setString(13, RandomUtil.randomString(4));
            preparedStatement.setString(14, RandomUtil.randomString(10));
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        conn.close();
    }


    @Test
    public void insertFlightInfo() throws ClassNotFoundException, SQLException {
        String[] DAS = new String[]{"D", "A"};
        String[] planeNos = new String[]{"MU5305", "CA1202", "CA1301", "CZ3102", "CA1302", "CZ3101"};
        String[] address = new String[]{"武汉", "北京", "上海", "深圳", "杭州", "长沙"};
        String[] sizes = new String[]{"中型客机", "小型客机", "波音747", "波音753"};
        String[] froms = CommonUtils.generatorAddress();
        String[] flightType = new String[]{"民用", "货用", "医疗救护机"};
        String[] status = new String[]{"起飞", "待飞", "完成"};
        String[] ss = new String[]{"开放", "暂停"};
        String[] HOUSE = new String[]{"T1", "T2"};
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://url:3306/enc_original_database?rewriteBatchedStatements=true&characterEncoding=UTF-8";
        Connection conn = DriverManager.getConnection(url, "root", "Bocom_123");
        String sql = "insert into hk_light_info values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        for (int i = 0; i < 100; i++) {
            preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setString(2, DAS[i % 2]);
            preparedStatement.setString(3, planeNos[i % planeNos.length]);
            preparedStatement.setString(4, froms[i % froms.length]);
            preparedStatement.setString(5, address[i % address.length]);
            preparedStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis() - RandomUtil.randomInt(1, 2) * 1000 * 60 * 60));
            preparedStatement.setTimestamp(7, new Timestamp(System.currentTimeMillis() + RandomUtil.randomInt(1, 2) * 1000 * 60 * 30));
            preparedStatement.setTimestamp(8, new Timestamp(System.currentTimeMillis() - RandomUtil.randomInt(2, 3) * 1000 * 60 * 60));
            preparedStatement.setTimestamp(9, new Timestamp(System.currentTimeMillis() + RandomUtil.randomInt(2, 3) * 1000 * 60 * 30));
            preparedStatement.setTimestamp(10, new Timestamp(System.currentTimeMillis() + RandomUtil.randomInt(2, 3) * 1000 * 60 * 46));
            preparedStatement.setTimestamp(11, null);
            preparedStatement.setString(12, planeNos[i % planeNos.length]);
            preparedStatement.setString(13, sizes[i % sizes.length]);
            preparedStatement.setString(14, flightType[i % flightType.length]);
            preparedStatement.setString(15, status[i % status.length]);
            preparedStatement.setString(16, HOUSE[i % HOUSE.length]);
            preparedStatement.setString(17, String.valueOf(RandomUtil.randomInt(6)));
            preparedStatement.setString(18, ss[i % ss.length]);
            preparedStatement.setTimestamp(19, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setTimestamp(20, new Timestamp(System.currentTimeMillis() + 3600));
            preparedStatement.setString(21, String.valueOf(RandomUtil.randomInt(2)));
            preparedStatement.setTimestamp(22, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setTimestamp(23, new Timestamp(System.currentTimeMillis() + 3600));
            preparedStatement.setString(24, String.valueOf(RandomUtil.randomInt(4)));
            preparedStatement.setString(25, String.valueOf(RandomUtil.randomInt(5)));
            preparedStatement.setString(26, String.valueOf(RandomUtil.randomInt(4)));
            preparedStatement.setString(27, String.valueOf(RandomUtil.randomInt(2)));
            preparedStatement.setString(28, String.valueOf(RandomUtil.randomInt(2)));
            preparedStatement.setString(29, String.valueOf(RandomUtil.randomInt(6)));
            preparedStatement.setTimestamp(30, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setString(31, RandomUtil.randomString(4));
            preparedStatement.setString(32, RandomUtil.randomString(10));
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        conn.close();
    }


    @Test
    public void insertStudentInfo() throws ClassNotFoundException, SQLException {
        String[] schools = new String[]{"清华大学", "北京大学", "浙江大学", "武汉大学", "西安电子科技", "华东政法", "武汉大学", "上海复旦", "上海交大", "同济大学", "华东师范大学", "华东理工大学", "上海中医药大", "上海大学", "上海财经大学"};
        String[] grades = new String[]{"大一", "大二", "大三", "大四"};
        String[] classes = new String[]{"115班", "116班", "117班", "118班", "119班"};
        String[] idCards = CommonUtils.generatorIdCard();
        String[] RXSJ = new String[]{"2001", "2002", "2003", "2004", "2005"};
        String[] HKLX = new String[]{"农村", "城市"};
        String[] birthDay = new String[]{"1981", "1982", "1983", "1984", "1979", "1978"};
        String[] sex = new String[]{"男", "女"};
        String[] han = new String[]{"汉"};
        String[] zzmm = new String[]{"党员"};
        String[] hkszd = new String[]{"厦门", "上海", "杭州", "苏州", "无锡", "深圳", "长沙"};
        String[] scores = new String[]{"78", "79", "80", "81", "82", "83", "85"};
        String[] health = new String[]{"健康", "良好", "感冒", "虚弱"};
        String[] birth = new String[]{"福建", "上海", "浙江", "湖南", "广东"};

        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://url:3306/enc_original_database?rewriteBatchedStatements=true&characterEncoding=UTF-8";
        Connection conn = DriverManager.getConnection(url, "root", "Bocom_123");
        String sql = "insert into shsjyj_gzxj values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        for (int i = 0; i < 100; i++) {
            preparedStatement.setString(1, schools[i % schools.length]);
            preparedStatement.setString(2, grades[i % grades.length]);
            if (i > 94) {
                preparedStatement.setString(3, null);

            } else {
                preparedStatement.setString(3, classes[i % classes.length]);
            }
            if (i % 9 == 0) {
                preparedStatement.setString(4, null);
            } else {
                preparedStatement.setString(4, idCards[i % idCards.length]);

            }
            preparedStatement.setString(5, RXSJ[i % RXSJ.length]);
            preparedStatement.setString(6, HKLX[i % HKLX.length]);

            preparedStatement.setString(7, birthDay[i % birthDay.length]);
            preparedStatement.setString(8, sex[i % sex.length]);
            preparedStatement.setString(9, "汉");
            preparedStatement.setString(10, zzmm[i % zzmm.length]);
            preparedStatement.setString(11, hkszd[i % hkszd.length]);
            preparedStatement.setString(12, scores[i % scores.length]);
            preparedStatement.setString(13, health[i % health.length]);
            preparedStatement.setString(14, hkszd[i % hkszd.length]);
            preparedStatement.setString(15, birth[i % birth.length]);
            preparedStatement.setTimestamp(16, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setString(17, RandomUtil.randomString(8));
            preparedStatement.setString(18, "政府");
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        conn.close();
    }


    @Test
    public void readPic1() throws ClassNotFoundException, SQLException, FileNotFoundException {
        String str = "abbc";
        int b = str.indexOf("b");
        int index = 0;
        while (b != -1) {
            System.out.println(str.substring(index, b));
            index = b;
            b = str.indexOf("b", index + 1);
        }
    }

    @Test
    public void readPic2() throws ClassNotFoundException, SQLException, FileNotFoundException, ParseException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/flink?rewriteBatchedStatements=true&characterEncoding=UTF-8";
        Connection conn = DriverManager.getConnection(url, "root", "123456");
        String sql = "insert into cc values(?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = sdf.parse("1900-12-11");

        System.out.println(parse);
        preparedStatement.setDate(1, new java.sql.Date(parse.getTime()));
        preparedStatement.addBatch();
        preparedStatement.executeBatch();
    }


    @Test
    public void test3() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/flink?rewriteBatchedStatements=true&characterEncoding=UTF-8";
        Connection conn = DriverManager.getConnection(url, "root", "123456");
//        ResultSet resultSet = conn.createStatement().executeQuery("select time1 from b");
        String sql = "insert into xc3 values(?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
//        conn.createStatement().executeQuery("SET NAMES utf8mb4");
//        preparedStatement.setString(1,"2016年2月5日，内蒙古赤峰市红山区清河路北口青年公寓2016室发生一起诈骗案件。2016年2月5日，路春宇通过电话15147663299向内蒙古赤峰市公安局红山区局刑事侦查大队报警称：自己被诈骗。民警接警后立即出警，到达现场后，经了解系报警人路春宇（男，汉族，18岁，身份证号码：150422199805230318，现住址：内蒙古赤峰市红山区清河路北口青年公寓2016室，联系方式：15147663299，QQ：2360388534，QQ昵称：威哒哒哒）。\uD83D\uDC7F看到有人在互联网上发布的虚假视频，其信以为真并添加这个人给出的QQ（该人给出的QQ号为200323），该人利用QQ200323诱骗路春宇购买网络游戏“穿越火线”中的“永久神器”，要求路春宇支付武器购买费、游戏帐号解封费，以逐步设置收费项目为手段，骗取路春宇钱财，对方以QQ红包转账和微信红包转账收款，路春宇以被“网络诈骗”的方式骗走2300元整。拟接受刑事案件立案侦查。电信诈骗。");
//        preparedStatement.setString(2,"aa");
        preparedStatement.setString(1, "2016年2月5日，内蒙古赤峰市红山区清河路北口青年公寓2016室发生一起诈骗案件。2016年2月5日，路春宇通过电话15147663299向内蒙古赤峰市公安局红山区局刑事侦查大队报警称：自己被诈骗。民警接警后立即出警，到达现场后，经了解系报警人路春宇（男，汉族，18岁，身份证号码：150422199805230318，现住址：内蒙古赤峰市红山区清河路北口青年公寓2016室，联系方式：15147663299，QQ：2360388534，QQ昵称：威哒哒哒）。\uD83D\uDC7F看到有人在互联网上发布的虚假视频，其信以为真并添加这个人给出的QQ（该人给出的QQ号为200323），该人利用QQ200323诱骗路春宇购买网络游戏“穿越火线”中的“永久神器”，要求路春宇支付武器购买费、游戏帐号解封费，以逐步设置收费项目为手段，骗取路春宇钱财，对方以QQ红包转账和微信红包转账收款，路春宇以被“网络诈骗”的方式骗走2300元整。拟接受刑事案件立案侦查。电信诈骗。");
//        while (resultSet.next()) {
//            Object object = resultSet.getObject(1);
//            System.out.println(object);
//        }
        preparedStatement.addBatch();
        preparedStatement.executeBatch();
    }


    @Test
    public void fetchTest() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://url:3306/enc_original_database?rewriteBatchedStatements=true";
        Connection conn = DriverManager.getConnection(url, "root", "Bocom_123");
        String sql = "select * from test_2000W limit 100000";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setFetchSize(1000);
        ResultSet rs = preparedStatement.executeQuery();
        int count = 0;
        int jj = 0;
        long startTime = System.currentTimeMillis();
        while (rs.next()) {
            for (int i = 1; i < 40; i++) {
                rs.getObject(i);
            }
            count++;
            if (count % 1000 == 0) {
                System.out.println(count);
                count = 0;

                System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
                startTime = System.currentTimeMillis();
                jj++;
            }

        }
        System.out.println(jj);
    }


    @Test
    public void testInsert() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://url:3306/enc_original_database?rewriteBatchedStatements=true";
        Connection conn = DriverManager.getConnection(url, "root", "Bocom_123");
        String sql = "insert into test_2000W_copy (select * from test_2000W)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.execute();
        preparedStatement.close();
        conn.close();
//        preparedStatement.setFetchSize(1000);
//        ResultSet rs = preparedStatement.executeQuery();
//        int count = 0;
//        int jj =0;
//        long startTime = System.currentTimeMillis();
//        while (rs.next()) {
//            for (int i = 1; i < 40; i++) {
//                rs.getObject(i);
//            }
//            count++;
//            if (count % 1000 == 0) {
//                System.out.println(count);
//                count = 0;
//
//                System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
//                startTime = System.currentTimeMillis();
//                jj++;
//            }
//
//        }
    }

    @Test
    public void insertData2() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/flink?rewriteBatchedStatements=true";
        Connection conn = DriverManager.getConnection(url, "root", "123456");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("insert into `a` values(");
        for (int i = 0; i < 4; i++) {
            stringBuilder.append("?,");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(")");
        PreparedStatement preparedStatement = conn.prepareStatement(stringBuilder.toString());
        for (int i = 1; i < 1000; i++) {
            for (int j = 0; j < 4; j++) {
                if (i != 9) {
                    if (j == 0) {
                        preparedStatement.setInt(1, i);
                    } else {
                        preparedStatement.setString(j + 1, RandomUtil.randomString(3));
                    }
                } else {
                    if (j == 0) {
                        preparedStatement.setInt(1, i);
                    } else if (j != 3) {
                        preparedStatement.setString(j + 1, "LABLE" + RandomUtil.randomString(4));
                    } else {
                        preparedStatement.setString(j + 1, "21ed");
                    }
                }
            }
            preparedStatement.addBatch();
            if (i % 10 == 0) {
                preparedStatement.executeBatch();
                preparedStatement.clearBatch();
            }
        }
        preparedStatement.executeBatch();
    }


    @Test
    public void insertData1() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://url:3306/enc_original_database?serverTimezone=Asia/Shanghai&useUnicode=yes&characterEncoding=UTF-8&rewriteBatchedStatements=true";
        Connection conn = DriverManager.getConnection(url, "root", "Bocom_123");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("insert into `bigData` (id) values(?)");
//        for (int i = 0; i < 4; i++) {
//            stringBuilder.append("?,");
//        }
//        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
//        stringBuilder.append(")");
        PreparedStatement preparedStatement = conn.prepareStatement(stringBuilder.toString());
        for (int i = 1; i < 100000; i++) {
            preparedStatement.setInt(1, i);
            preparedStatement.addBatch();
            if (i % 10000 == 0) {
                preparedStatement.executeBatch();
                preparedStatement.clearBatch();
            }
        }
        preparedStatement.executeBatch();
    }


    @Test
    public void showTables() throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.jdbc.Driver");
//        String url = "jdbc:mysql://localhost:3306/flinkjson?serverTimezone=Asia/Shanghai&useUnicode=yes&characterEncoding=UTF-8&rewriteBatchedStatements=true";
//        Connection conn = DriverManager.getConnection(url, "root", "123456");
//        ResultSet tables = conn.getMetaData().getTables("", "", "%", null);
//        int count = 0;
//        while(tables.next()){
//            count++;
//            System.out.println(tables.getObject(3));
//
//        }
//        System.out.println(count);


//        Object s = 100;
//        String s1 =String.valueOf(s;
//        System.out.println(s1);
        try {
            Object s = 100;
            String s1 = String.valueOf(s);
            System.out.println(s1);
            System.out.println("Bbbb");
        } finally {
            System.out.println("aaa");
        }
    }


    @Test
    public void insertFood() throws ClassNotFoundException, SQLException, FileNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://url:3306/enc_original_database?rewriteBatchedStatements=true&characterEncoding=UTF-8";
        Connection conn = DriverManager.getConnection(url, "root", "Bocom_123");
        String[] names = new String[]{"张海洲", "成果"};
        String[] months = new String[]{"11月", "10月", "12月"};
        String sql = "insert into food_infos values (?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        for (int i = 0; i < 100; i++) {
            preparedStatement.setString(1, months[i % months.length]);
            preparedStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis() - 1000 * 60 * 60 * 24 * 30 * (i % 3)));
            preparedStatement.setString(3, names[i % 2]);
            preparedStatement.setLong(4, RandomUtil.randomLong(2000, 5000));
            preparedStatement.setString(5, "餐饮费");
            preparedStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setString(7, RandomUtil.randomString(4));
            preparedStatement.setString(8, RandomUtil.randomString(4));
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        conn.close();
    }

    @Test
    public void insertMat() throws ClassNotFoundException, SQLException, FileNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://url:3306/enc_original_database?rewriteBatchedStatements=true&characterEncoding=UTF-8";
        Connection conn = DriverManager.getConnection(url, "root", "Bocom_123");
        String[] names = new String[]{"张子怡", "陈晶晶"};
        String[] mats = new String[]{"钢管", "钢瓶", "水泥", "阀门"};
        String[] months = new String[]{"11月", "10月", "12月"};
        String sql = "insert into materials_infos values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        for (int i = 0; i < 5; i++) {
            preparedStatement.setString(1, months[i % months.length]);
            preparedStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis() - 1000 * 60 * 60 * 24 * 30 * (i % 3)));
            preparedStatement.setLong(3, -1);
            preparedStatement.setString(4, mats[i % mats.length]);
            preparedStatement.setString(5, "材料费");
            preparedStatement.setString(6, names[i % 2]);
            preparedStatement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setString(8, RandomUtil.randomString(4));
            preparedStatement.setString(9, RandomUtil.randomString(4));
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        conn.close();
    }


    @Test
    public void inserTraffic() throws ClassNotFoundException, SQLException, FileNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://url:3306/enc_original_database?rewriteBatchedStatements=true&characterEncoding=UTF-8";
        Connection conn = DriverManager.getConnection(url, "root", "Bocom_123");
        String[] names = new String[]{"张正国", "程海峰"};
        String[] froms = new String[]{"廊坊", "郑州"};
        String[] tos = new String[]{"郑州", "廊坊"};
        String[] months = new String[]{"11月", "10月", "12月"};
        String[] carsNos = new String[]{"豫V1232", "豫V4212", "翼D9231", "翼D2135"};
        String sql = "insert into traffic_infos values (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        for (int i = 0; i < 10; i++) {
            preparedStatement.setString(1, froms[i % 2]);
            preparedStatement.setString(2, tos[i % 2]);
            preparedStatement.setString(3, months[i % months.length]);
            preparedStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis() - 1000 * 60 * 60 * 24 * 30 * (i % 3)));
            preparedStatement.setLong(5, -1);
            preparedStatement.setString(6, names[i % 2]);
            preparedStatement.setString(7, carsNos[i % 4]);
            preparedStatement.setString(8, "交通费");
            preparedStatement.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setString(10, RandomUtil.randomString(4));

            preparedStatement.setString(11, RandomUtil.randomString(4));
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        conn.close();
    }


    @Test
    public void insertDevice() throws ClassNotFoundException, SQLException, FileNotFoundException {
        String[] months = new String[]{"01月", "02月", "03月", "04月", "05月", "06月", "07月", "08月", "09月", "10月", "11月", "12月"};
        String[] names = new String[]{"张正国", "程海峰", "张子怡", "陈晶晶", "张海洲", "成果"};
        String[] areas = new String[]{"A区", "B区", "C区"};
        String[] exceptions = new String[]{"设备温度过高", "调测器出口压力过低", "燃气流量超过范围值", "设备异常", "温度感应器损坏"};
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://url:3306/enc_original_database?rewriteBatchedStatements=true&characterEncoding=UTF-8";
        Connection conn = DriverManager.getConnection(url, "root", "Bocom_123");
        String sql = "insert into deviceinfosa values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        for (int i = 0; i < 4000; i++) {
            if (RandomUtil.randomInt(30) > 28) {
                preparedStatement.setLong(1, RandomUtil.randomLong(30000, 80000) * -1);
            } else {
                preparedStatement.setLong(1, RandomUtil.randomLong(30000, 80000));
            }

            if (RandomUtil.randomInt(28) > 24) {
                preparedStatement.setDouble(2, RandomUtil.randomDouble(70, 120) * -1);
            } else {
                preparedStatement.setDouble(2, RandomUtil.randomDouble(70, 120));
            }

            if (RandomUtil.randomInt(20) > 18) {
                preparedStatement.setLong(3, RandomUtil.randomLong(1000, 8000) * -1);
            } else {
                preparedStatement.setLong(3, RandomUtil.randomLong(1000, 8000));
            }
            preparedStatement.setDouble(4, RandomUtil.randomDouble(1500, 3000));
            preparedStatement.setDouble(5, RandomUtil.randomDouble(2500, 3000));
            preparedStatement.setString(6, "A区");
            preparedStatement.setString(7, names[RandomUtil.randomInt(5)]);
            preparedStatement.setString(8, months[RandomUtil.randomInt(0, 12)]);
            preparedStatement.setString(9, String.valueOf(RandomUtil.randomInt(100000, 999999)));
            if (RandomUtil.randomInt(300) > 288) {
                preparedStatement.setString(10, exceptions[RandomUtil.randomInt(5)]);
            } else {
                preparedStatement.setString(10, null);
            }
            //经度，纬度
            if (i % 2 == 0) {
                preparedStatement.setDouble(11, 13515704.362174023 + RandomUtil.randomDouble(2000, 20000));
                preparedStatement.setDouble(12, 3644311.8303395757 + RandomUtil.randomDouble(2000, 20000));
            } else {
                preparedStatement.setDouble(11, 13515704.362174023 - RandomUtil.randomDouble(2000, 20000));
                preparedStatement.setDouble(12, 3644311.8303395757 - RandomUtil.randomDouble(2000, 20000));
            }
            if (RandomUtil.randomInt(29) > 27) {
                preparedStatement.setString(13, null);
            } else {
                preparedStatement.setString(13, "d-" + RandomUtil.randomInt(10000, 99999));
            }
            preparedStatement.setTimestamp(14, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setString(15, RandomUtil.randomString(4));
            preparedStatement.setString(16, RandomUtil.randomString(4));
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        conn.close();
    }


    @Test
    public void insertDevice1() throws ClassNotFoundException, SQLException, FileNotFoundException {
        String[] months = new String[]{"01月", "02月", "03月", "04月", "05月", "06月", "07月", "08月", "09月", "10月", "11月", "12月"};
        String[] names = new String[]{"张正国", "程海峰", "张子怡", "陈晶晶", "张海洲", "成果"};
        String[] areas = new String[]{"A区", "B区", "C区"};
        String[] exceptions = new String[]{"设备温度过高", "调测器出口压力过低", "燃气流量超过范围值", "设备异常", "温度感应器损坏"};
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://url:3306/enc_original_database?rewriteBatchedStatements=true&characterEncoding=UTF-8";
        Connection conn = DriverManager.getConnection(url, "root", "Bocom_123");
        String sql = "insert into deviceinfosb values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        for (int i = 0; i < 4000; i++) {
            if (RandomUtil.randomInt(30) > 28) {
                preparedStatement.setLong(1, RandomUtil.randomLong(30000, 80000) * -1);
            } else {
                preparedStatement.setLong(1, RandomUtil.randomLong(30000, 80000));
            }

            if (RandomUtil.randomInt(28) > 24) {
                preparedStatement.setDouble(2, RandomUtil.randomDouble(70, 120) * -1);
            } else {
                preparedStatement.setDouble(2, RandomUtil.randomDouble(70, 120));
            }

            if (RandomUtil.randomInt(20) > 18) {
                preparedStatement.setLong(3, RandomUtil.randomLong(1000, 8000) * -1);
            } else {
                preparedStatement.setLong(3, RandomUtil.randomLong(1000, 8000));
            }
            preparedStatement.setDouble(4, RandomUtil.randomDouble(1500, 3000));
            preparedStatement.setDouble(5, RandomUtil.randomDouble(2500, 3000));
            preparedStatement.setString(6, "B区");
            preparedStatement.setString(7, names[RandomUtil.randomInt(5)]);
            preparedStatement.setString(8, months[RandomUtil.randomInt(0, 12)]);
            preparedStatement.setString(9, String.valueOf(RandomUtil.randomInt(100000, 999999)));
            if (RandomUtil.randomInt(300) > 288) {
                preparedStatement.setString(10, exceptions[RandomUtil.randomInt(5)]);
            } else {
                preparedStatement.setString(10, null);
            }
            //经度，纬度
            if (i % 2 == 0) {
                preparedStatement.setDouble(11, 13515704.362174023 + RandomUtil.randomDouble(2000, 20000));
                preparedStatement.setDouble(12, 3644311.8303395757 + RandomUtil.randomDouble(2000, 20000));
            } else {
                preparedStatement.setDouble(11, 13515704.362174023 - RandomUtil.randomDouble(2000, 20000));
                preparedStatement.setDouble(12, 3644311.8303395757 - RandomUtil.randomDouble(2000, 20000));
            }
            if (RandomUtil.randomInt(29) > 27) {
                preparedStatement.setString(13, null);
            } else {
                preparedStatement.setString(13, "d-" + RandomUtil.randomInt(10000, 99999));
            }
            preparedStatement.setTimestamp(14, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setString(15, RandomUtil.randomString(4));
            preparedStatement.setString(16, RandomUtil.randomString(4));
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        conn.close();
    }


    @Test
    public void insertDevice2() throws ClassNotFoundException, SQLException, FileNotFoundException {
        String[] months = new String[]{"01月", "02月", "03月", "04月", "05月", "06月", "07月", "08月", "09月", "10月", "11月", "12月"};
        String[] names = new String[]{"张正国", "程海峰", "张子怡", "陈晶晶", "张海洲", "成果"};
        String[] areas = new String[]{"A区", "B区", "C区"};
        String[] exceptions = new String[]{"设备温度过高", "调测器出口压力过低", "燃气流量超过范围值", "设备异常", "温度感应器损坏"};
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://url:3306/enc_original_database?rewriteBatchedStatements=true&characterEncoding=UTF-8";
        Connection conn = DriverManager.getConnection(url, "root", "Bocom_123");
        String sql = "insert into deviceinfosc values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        for (int i = 0; i < 4000; i++) {
            if (RandomUtil.randomInt(30) > 28) {
                preparedStatement.setLong(1, RandomUtil.randomLong(30000, 80000) * -1);
            } else {
                preparedStatement.setLong(1, RandomUtil.randomLong(30000, 80000));
            }

            if (RandomUtil.randomInt(28) > 24) {
                preparedStatement.setDouble(2, RandomUtil.randomDouble(70, 120) * -1);
            } else {
                preparedStatement.setDouble(2, RandomUtil.randomDouble(70, 120));
            }

            if (RandomUtil.randomInt(20) > 18) {
                preparedStatement.setLong(3, RandomUtil.randomLong(1000, 8000) * -1);
            } else {
                preparedStatement.setLong(3, RandomUtil.randomLong(1000, 8000));
            }
            preparedStatement.setDouble(4, RandomUtil.randomDouble(1500, 3000));
            preparedStatement.setDouble(5, RandomUtil.randomDouble(2500, 3000));
            preparedStatement.setString(6, "C区");
            preparedStatement.setString(7, names[RandomUtil.randomInt(5)]);
            preparedStatement.setString(8, months[RandomUtil.randomInt(0, 12)]);
            preparedStatement.setString(9, String.valueOf(RandomUtil.randomInt(100000, 999999)));
            if (RandomUtil.randomInt(300) > 288) {
                preparedStatement.setString(10, exceptions[RandomUtil.randomInt(5)]);
            } else {
                preparedStatement.setString(10, null);
            }
            //经度，纬度
            if (i % 2 == 0) {
                preparedStatement.setDouble(11, 13515704.362174023 + RandomUtil.randomDouble(2000, 20000));
                preparedStatement.setDouble(12, 3644311.8303395757 + RandomUtil.randomDouble(2000, 20000));
            } else {
                preparedStatement.setDouble(11, 13515704.362174023 - RandomUtil.randomDouble(2000, 20000));
                preparedStatement.setDouble(12, 3644311.8303395757 - RandomUtil.randomDouble(2000, 20000));
            }
            if (RandomUtil.randomInt(29) > 27) {
                preparedStatement.setString(13, null);
            } else {
                preparedStatement.setString(13, "d-" + RandomUtil.randomInt(10000, 99999));
            }
            preparedStatement.setTimestamp(14, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setString(15, RandomUtil.randomString(4));
            preparedStatement.setString(16, RandomUtil.randomString(4));
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        conn.close();
    }


    /**
     * 更新数据库已有数据，有则更新数据，无则插入，
     * 更新的内容为insert的字段与update的合并值，如果update与insert对同一个字段有不同值取update中的值
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Test
    public void updateDataWithKey() throws ClassNotFoundException, SQLException {
        Connection con = getLocalCon();
        String sql = "insert into update_demo (id,name,sex,grade,class) values (?,?,?,?,?) ON DUPLICATE KEY update sex = ?,class = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, 7);
        preparedStatement.setString(2, "hhh");
        preparedStatement.setString(3, RandomUtil.randomString(4));
        preparedStatement.setString(4, RandomUtil.randomString(4));
        preparedStatement.setString(5, RandomUtil.randomString(4));
        preparedStatement.setString(6,"替换2");
        preparedStatement.setString(7,"替换3");
        preparedStatement.execute();
        con.close();

    }
}

