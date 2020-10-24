package com.hanyinh.rpc.registry;

/**
 * 注册中心
 *
 * @author Hanyinh
 * @date 2020/10/24 14:13
 */
public interface RegisterCenter {

    /**
     * 把服务注册到zk上
     * ip：127.0.0.1:8080
     * @param serviceName   com.hanyinh.rpc.remote.RpcRemoteService
     * @param serviceAddress    127.0.0.1:9090
     */
    void register(String serviceName, String serviceAddress);
}
