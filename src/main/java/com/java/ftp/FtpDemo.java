package com.java.ftp;

import com.model.SourceDef;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.server.RemoteServer;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/9
 * @Description:
 */
public class FtpDemo {
    public static void main(String[] args) throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("url.149.104", 21);
        ftpClient.login("ftpuser", "bocomftp");
        String encoding = "UTF-8";
        if (FTPReply.isPositiveCompletion(ftpClient.sendCommand("OPTS UTF-8", "ON"))) {
            encoding = "UTF-8";
        }

        ftpClient.setControlEncoding(encoding);
        ftpClient.enterLocalPassiveMode();
        RemoteServerConfig remoteServerConfig = new RemoteServerConfig();
        remoteServerConfig.setRemotePath(new String("/data/ftpuser/newpic/".getBytes("UTF-8"), "UTF-8"));
        ftpClient.changeWorkingDirectory(remoteServerConfig.getRemotePath());
        FTPFile[] ftpFiles = ftpClient.listFiles(remoteServerConfig.getRemotePath());

        BigDecimal num = new BigDecimal(3463734734578345.23);


    }


    @Test
    public void test1() throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("url.146.81", 21);
//        ftpClient.login("ftpuser", "bocomftp");
        String encoding = "UTF-8";
        if (FTPReply.isPositiveCompletion(ftpClient.sendCommand("OPTS UTF-8", "ON"))) {
            encoding = "UTF-8";
        }

        ftpClient.setControlEncoding(encoding);
        ftpClient.enterLocalPassiveMode();
        RemoteServerConfig remoteServerConfig = new RemoteServerConfig();
        remoteServerConfig.setRemotePath(new String("/test/file_test/20200325/gbk2/".getBytes("UTF-8"), "UTF-8"));
        ftpClient.changeWorkingDirectory(remoteServerConfig.getRemotePath());
        FTPFile[] ftpFiles = ftpClient.listFiles(remoteServerConfig.getRemotePath());
    }

    @Test
    public void testString(){
        String str1 = "1234";
        String str2 = "2345";
        System.out.println(str1.compareTo(str2));
    }
}
