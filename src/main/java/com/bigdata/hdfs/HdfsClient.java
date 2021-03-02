package com.bigdata.hdfs;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: hzg
 * @create: 2019-10-10 13:43
 **/

public class HdfsClient {

    private static final String AUTH_BY_KERBEROS = "kerberos";
    private static final String CONF_AUTH_TO_LOCAL = "hadoop.security.auth_to_local";
    private static final String CONF_AUTH_TO_LOCAL_RULE = "RULE:[2:$1]";

    public static void main(String[] args) throws IOException {
        FileSystem fs = null;
        Configuration conf = new Configuration();
        //直接调用addResource(文件)   会导致获取的是LocalFileSystem,可能是后续的文件覆盖导致
//        conf.addResource("D://141krb/core-site.xml");
//        conf.addResource("D://141krb/hdfs-site.xml");

        //推荐写法
        conf.addResource(getResource("D://141krb/core-site.xml"));
        conf.addResource(getResource("D://141krb/hdfs-site.xml"));
        conf.set(CONF_AUTH_TO_LOCAL, CONF_AUTH_TO_LOCAL_RULE);
        String auth = conf.get("hadoop.security.authentication");
        if (AUTH_BY_KERBEROS.equals(auth)) {
            try {
                System.setProperty("java.security.krb5.conf", "D://141krb/krb5.conf");
                sun.security.krb5.Config.refresh();
            } catch (Exception e) {
                throw new RuntimeException("Can not refresh krb5 config");
            }
            String keytab = getFilePath("D:/141krb/ossuser.keytab");

            UserGroupInformation.setConfiguration(conf);
            UserGroupInformation.loginUserFromKeytab("ossuser/cluster-node-1@HADOOP.COM", keytab);
            final UserGroupInformation ugi = UserGroupInformation.getLoginUser();
        }
        fs = FileSystem.get(conf);
        FileStatus[] fileStatuses = fs.listStatus(new Path("/encdata"));
        System.out.println(fs);

    }


    public static String getFilePath(String filePath) throws IOException {
        File configFile = new File(filePath);
        if (configFile.exists()) {
            return configFile.getCanonicalPath();
        }

        URL url = new URL("D:/141krb/ossuser.keytab");
        if (url != null) {
            return url.getPath();
        }
        return filePath;
    }

    private static InputStream getResource(String resourceName) throws IOException {
        File configFile = new File(resourceName);
        if (configFile.exists()) {
            return new FileInputStream(configFile);
        }
        InputStream in = null;
        try {
            in = HdfsClient.class.getClassLoader().getResourceAsStream(resourceName);
        } catch (Exception e) {
        }
        if (null == in) {
            String configDir = System.getenv("HADOOP_CONF_DIR");
            if (StringUtils.isEmpty(configDir)) {
                throw new IOException("Can not load config file " + resourceName);
            }
            configFile = new File(configDir, resourceName);
            return new FileInputStream(configFile);
        }

        return in;
    }
}

