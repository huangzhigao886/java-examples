package com.bigdata.impala;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zoujianchao
 * @Description
 * @date 2019/10/24
 */
public class TestImpala {

    static String JDBC_DRIVER = "com.cloudera.impala.jdbc41.Driver";
    static String CONNECTION_URL = "jdbc:impala://url:21050/originald;AuthMech=1;KrbRealm=HADOOP.COM;KrbHostFQDN=cdhcluster-node-142;KrbServiceName=ossuser";
    public static void testImpala() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Configuration conf = new Configuration();
        conf.set("hadoop.security.authentication", "Kerberos");
        System.setProperty("java.security.auth.login.config", "E:/krb/impala_admin_jaas.conf");
        System.setProperty("java.security.krb5.conf", "E:/krb/krb5.conf");
        try {
            Class.forName(JDBC_DRIVER);
            UserGroupInformation.setConfiguration(conf);
            conn = DriverManager.getConnection(CONNECTION_URL);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select name,id from originald.zhytest_123");
            List<Map<String, Object>> recordList = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> recod = new CaseInsensitiveMap();
                ResultSetMetaData metaData = rs.getMetaData();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    String columnName = metaData.getColumnName(i + 1);
                    Object value = rs.getObject(columnName);
                    recod.put(columnName, value);
                }
                recordList.add(recod);
            }
            System.out.println(recordList);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        testImpala();
    }
}
