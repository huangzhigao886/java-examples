package com.util;

/**
 * @description:
 * @author: hzg
 * @create: 2019-09-26 10:41
 **/

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.security.UserGroupInformation;

public class HdfsReader {

    public static final String AUTH_BY_KERBEROS = "kerberos";

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();


        String auth = conf.get("hadoop.security.authentication");
        if(AUTH_BY_KERBEROS.equals(auth)) {
            String principal = "hdfs/cdhcluster-node-142@HADOOP.COM";
            String keytab = "/opt/flink-1.8.1/keytab/flink.keytab";

            UserGroupInformation.setConfiguration(conf);
            UserGroupInformation.loginUserFromKeytab(principal, keytab);
            final UserGroupInformation ugi = UserGroupInformation.getLoginUser();
            System.out.println("Login as: " + principal);

        }
        FileSystem fs = FileSystem.get(conf);

        FileStatus[] fileStatusList = fs.listStatus(new Path("/encdata"));
        for(FileStatus fileStatus : fileStatusList) {
            System.out.println(fileStatus.getPath() + "\t\t" + fileStatus.getLen());
        }
    }
}