package com.bigdata.impala;


import cn.hutool.core.util.RandomUtil;

import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/25
 * @Description:
 */
public class ImpalaDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.setProperty("java.security.auth.login.config", "D:/100krb/daas_jaas.conf");
        System.setProperty("java.security.krb5.conf", "D:/100krb/krb5.conf");
        String JDBC_DRIVER = "com.cloudera.impala.jdbc41.Driver";
        String CONNECTION_URL = "jdbc:impala://url:25004/zhutiku;AuthMech=1;KrbRealm=HADOOP.COM;KrbHostFQDN=cluster-node-1;KrbServiceName=impala";
        Class.forName(JDBC_DRIVER);
        Connection connection = DriverManager.getConnection(CONNECTION_URL, "", "");
        System.out.println(connection);
        long currentTimeMillis = System.currentTimeMillis();
        PreparedStatement preparedStatement = connection.prepareStatement("select zjlid,\n" +
                "id_src,\n" +
                "xm,\n" +
                "bmch,\n" +
                "cym,\n" +
                "xb_dm,\n" +
                "xb_mc,\n" +
                "sg,\n" +
                "xx,\n" +
                "sx,\n" +
                "xz,\n" +
                "tz,\n" +
                "nl,\n" +
                "csrq,\n" +
                "mz_dm,\n" +
                "mz_mc,\n" +
                "xl,\n" +
                "dzyx,\n" +
                "zy02,\n" +
                "zzmm,\n" +
                "byxx,\n" +
                "wxhm,\n" +
                "qqhm,\n" +
                "zp,\n" +
                "byzt_dm,\n" +
                "byzt_mc,\n" +
                "csd_dm,\n" +
                "csd_mc,\n" +
                "csd_xz,\n" +
                "gj_dm,\n" +
                "gj_mc,\n" +
                "gxpcs_dm,\n" +
                "gxpcs_mc,\n" +
                "gzdw,\n" +
                "hjdqh_dm,\n" +
                "hjdqh_mc,\n" +
                "hjdz,\n" +
                "hyzk_dm,\n" +
                "hyzk_mc,\n" +
                "jgdqh_dm,\n" +
                "jgdqh_mc,\n" +
                "lxdh,\n" +
                "lxdh01,\n" +
                "gmsfzh,\n" +
                "ryzt_dm,\n" +
                "ryzt_mc,\n" +
                "xzz,\n" +
                "zfb,\n" +
                "zjhm,\n" +
                "zjlx_dm,\n" +
                "zjlx_mc,\n" +
                "zjxy_dm,\n" +
                "zjxy_mc,\n" +
                "zxrq,\n" +
                "sjgxsj,\n" +
                "xxrksj,\n" +
                "bz,\n" +
                "enc_db_src\n from t_dm_ryztb1 order by xxrksj limit 10 offset 300");
        preparedStatement.setFetchSize(10);
        preparedStatement.executeQuery();
        System.out.println(System.currentTimeMillis()-currentTimeMillis);

//        connection.createStatement().execute("select * from ysk_jqbbcs_user_detail_info_3");

//        PreparedStatement statement = connection.prepareStatement("insert into order2_tmp values(?,?)");
//        for (int i = 0; i < 50; i++) {
//            if (i % 3 == 1) {
//                statement.setInt(1, 1);
//
//            } else if (i % 3 == 2) {
//                statement.setInt(1, 2);
//            } else {
//                statement.setInt(1, 3);
//            }
//            statement.setInt(2,i);
//            statement.execute();
//        }


//        String tmp = "partition10";
//
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        executor.execute(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    Statement statement = null;
//                    try {
//                        statement = connection.createStatement();
//
//                        boolean execute = statement.execute("select 1");
//                        System.out.println(execute);
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        Thread.sleep(15 * 1000L);
//                    } catch (InterruptedException e) {
//                        break;
//                    }
//                }
//            }
//        });
//
//
//        for (int i = 0;i<100;i++){
//            System.out.println("1");
//            try {
//                Thread.sleep(2*1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        for(int i = 0;i<10;i++){
//            boolean execute = connection.prepareStatement("refresh " + tmp).execute();
//            System.out.println(execute);
//        }


//        String sql = "insert into test1 values(?,?)";
//
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);

//        for (int i = 1; i < 120; i++) {
//            if (i % 2 == 0) {
//                preparedStatement.setString(1, "zhou"+i);
//                preparedStatement.setString(2,"偶数");
//            } else {
//                preparedStatement.setString(1, "wnag"+i);
//                preparedStatement.setString(2,"奇数");
//            }
//            preparedStatement.addBatch();
//            if (i % 119 == 0) {
//                preparedStatement.executeBatch();
//            }
//        }


//        PreparedStatement statement = connection.prepareStatement("select * from originald.dept");
//        ResultSet resultSet = statement.executeQuery();
//        while (resultSet.next()){
//            System.out.println(resultSet.getObject(1));
//        }


//        System.setProperty("java.security.auth.login.config", "C:/krb/impala_admin_jaas.conf");
//        System.setProperty("java.security.krb5.conf", "C:/krb/krb5.conf");
//        String JDBC_DRIVER = "com.cloudera.impala.jdbc41.Driver";
//        String CONNECTION_URL = "jdbc:impala://15.52.13.166:25004/originald;AuthMech=1;KrbRealm=HADOOP.COM;KrbHostFQDN=cluster-node-1;KrbServiceName=impala";
//        Class.forName(JDBC_DRIVER);
//        Connection connection = DriverManager.getConnection(CONNECTION_URL);
//        System.out.println(connection);

    }
}
