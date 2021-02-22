package com.java.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;

/**
 * @Auther: huangzhigao
 * @Date: 2020/3/22
 * @Description:
 */
public class FtpEncoding {
    public static void main(String[] args) throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("url.146.10", 21);
        ftpClient.login("test", "pass");
        int on = ftpClient.sendCommand("OPTS UTF-8", "ON");
        String encoding = "UTF-8";
        if (FTPReply.isPositiveCompletion(ftpClient.sendCommand("OPTS UTF-8", "ON"))) {
            encoding = "UTF-8";
        }

        ftpClient.setControlEncoding(encoding);
        ftpClient.enterLocalPassiveMode();
        FTPFile[] ftpFiles = ftpClient.listFiles("/file");
        System.out.println(on);
    }
}
