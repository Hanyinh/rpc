package com.hanyinh.rpc.registry;

import java.io.IOException;

/**
 * 服务注册
 *
 * @author Hanyinh
 * @date 2020/10/24 14:41
 */
public class RegisterTest {

    public static void main(String[] args) throws IOException {
        RegisterCenter registerCenter = new RegisterCenterImpl();
        registerCenter.register("com.hanyinh.rpc.remote.RpcRemoteService", "127.0.0.1:9090");

        // 阻塞一下
        System.in.read();
    }
}
