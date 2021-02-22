package com.velocity.Bean;

import cn.hutool.core.util.RandomUtil;
import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Auther: huangzhigao
 * @Date: 2020/4/1
 * @Description:
 */
public class FastDfsDemo {
    public static void main(String[] args) throws IOException, MyException {
        Properties properties = new Properties();
        properties.put(ClientGlobal.PROP_KEY_TRACKER_SERVERS, "url:22122,url:22122,url:22122");
        ClientGlobal.initByProperties(properties);
        //创建tracker的客户端
        TrackerClient trackerClient = new TrackerClient();
        //访问tracker服务器，获取连接信息
////        TrackerServer trackerServer = trackerClient.getConnection();
//        StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
//        //获取storage的连接信息
//        StorageClient1 storageClient = new StorageClient1(trackerServer, storageServer);
        List<String> datas = createDatas();
        List<String> res = new ArrayList<>(1);
        long start = System.currentTimeMillis();
        for (int i = 0; i < datas.size(); i++) {
//            String png = storageClient.upload_file1(datas.get(i).getBytes(), "png", null);
//            res.add(png);
        }
        System.out.println("共计耗时:" + (System.currentTimeMillis() - start));

    }


    public static List<String> createDatas() {
        List<String> str = new ArrayList<>(1);
        for (int i = 0; i < 1; i++) {
            str.add(RandomUtil.randomString(4));
        }
        return str;
    }
}
