package com.bigdata.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;

/**
 * @Auther: huangzhigao
 * @Date: 2020/12/28
 * @Description:
 */
@Slf4j
public class ZkDeleteThread implements Runnable {

    private CuratorFramework curatorClient;

    private static final int retryCount = 3;

    private String leaderNodeName;

    private String leaderlatchNodeName;

    public ZkDeleteThread(CuratorFramework curatorClient, String leaderNodeName, String leaderlatchNodeName) {
        this.curatorClient = curatorClient;
        this.leaderlatchNodeName = leaderlatchNodeName;
        this.leaderNodeName = leaderNodeName;
    }

    @Override
    public void run() {
        for (int i = 0; i < retryCount; i++) {
            try {
                if (null == curatorClient.checkExists().forPath(leaderNodeName)) {
                    break;
                }
                curatorClient.delete().deletingChildrenIfNeeded().forPath(leaderNodeName);
            } catch (Exception e) {
                log.error("Failed to delete zk leader node for job {}" + leaderNodeName);
            }
        }

        for (int i = 0; i < retryCount; i++) {
            try {
                if (null == curatorClient.checkExists().forPath(leaderlatchNodeName)) {
                    break;
                }
                curatorClient.delete().deletingChildrenIfNeeded().forPath(leaderlatchNodeName);
            } catch (Exception e) {
                log.error("Failed to delete zk leader latch node for job {}" + leaderlatchNodeName);
            }
        }
    }
}
