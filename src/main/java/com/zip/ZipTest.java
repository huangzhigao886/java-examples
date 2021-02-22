package com.zip;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2020/2/6
 * @Description:
 */
public class ZipTest {
    public static void main(String[] args) throws IOException {
        ArchiveEntry archiveEntry = null;
        List<String> result = new ArrayList<>();
        ArchiveInputStream zin = new ZipArchiveInputStream(new FileInputStream("D://万条数据.zip"),"GBK",true);
        while ((archiveEntry = zin.getNextEntry()) != null) {
            if (!archiveEntry.isDirectory()) {
                System.out.println(archiveEntry.getName());
            }
        }
    }
}
