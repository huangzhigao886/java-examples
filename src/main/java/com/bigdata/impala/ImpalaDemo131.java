package com.bigdata.impala;


import cn.hutool.core.io.FileUtil;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/25
 * @Description:
 */
public class ImpalaDemo131 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
        System.setProperty("java.security.auth.login.config", "D:/121krb/daas_jaas.conf");
        System.setProperty("java.security.krb5.conf", "D:/121krb/krb5.conf");
        String JDBC_DRIVER = "com.cloudera.impala.jdbc41.Driver";
        String CONNECTION_URL = "jdbc:impala://url.151.121:25004/originald;AuthMech=1;KrbRealm=HADOOP.COM;KrbHostFQDN=cluster-node-1;KrbServiceName=impala";
        Class.forName(JDBC_DRIVER);
        Connection connection = DriverManager.getConnection(CONNECTION_URL, "", "");
        System.out.println(connection);
        connection.close();
    }


    @Test
    public void insertPic() throws SQLException, ClassNotFoundException, IOException {
        Connection con = getCon();
        System.out.println(con);
//        BufferedInputStream in = FileUtil.getInputStream("D://图片库/aa.jpg");
//        String string = IOUtils.toString(in, Charset.defaultCharset());
//        PreparedStatement preparedStatement = con.prepareStatement("insert into phh values(?)");
//        preparedStatement.setBinaryStream(1,in);
//        preparedStatement.execute();
    }


    public static Connection getCon() throws ClassNotFoundException, SQLException {
        System.setProperty("java.security.auth.login.config", "D:/141krb/daas_jaas.conf");
        System.setProperty("java.security.krb5.conf", "D:/141krb/krb5.conf");
        String JDBC_DRIVER = "com.cloudera.impala.jdbc41.Driver";
        String CONNECTION_URL = "jdbc:impala://url:25004/originald;AuthMech=1;KrbRealm=HADOOP.COM;KrbHostFQDN=cluster-node-1;KrbServiceName=impala";
        Class.forName(JDBC_DRIVER);
        Connection connection = DriverManager.getConnection(CONNECTION_URL, "", "");
        return connection;
    }
}
