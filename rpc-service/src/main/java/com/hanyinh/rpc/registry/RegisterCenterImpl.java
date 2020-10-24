package com.hanyinh.rpc.registry;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * 注册中心实现
 *
 * @author Hanyinh
 * @date 2020/10/24 14:16
 */
@Slf4j
public class RegisterCenterImpl implements RegisterCenter {

    /**
     * 连接zk
     */
    private CuratorFramework curatorFramework;

    {
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(ZkConfig.CONNECTION_STR)
                .sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 10))
                .build();
        curatorFramework.start();
    }

    @Override
    public void register(String serviceName, String serviceAddress) {
        String servicePath = ZkConfig.ZK_REGISTER_PATH + serviceName;
        try {
            // 判断路径存不存在，不存在则创建
            if (curatorFramework.checkExists().forPath(servicePath) == null) {
                // 持久节点
                curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(servicePath, "0".getBytes());
            }
            // 服务路径
            String addressPath = servicePath + "/" + serviceAddress;
            // 节点路径     临时节点
            String rsNode = curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath(addressPath, "0".getBytes());
            log.error("服务注册成功，{}", rsNode);
        } catch (Exception e) {

        }
    }
}
