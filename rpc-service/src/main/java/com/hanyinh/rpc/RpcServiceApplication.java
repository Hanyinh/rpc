package com.hanyinh.rpc;

import com.hanyinh.rpc.registry.RegisterCenterImpl;
import com.hanyinh.rpc.remote.RpcRemoteService;
import com.hanyinh.rpc.remote.impl.RpcRemoteServiceImpl;
import com.hanyinh.rpc.server.netty.NettyServer;

/**
 * 服务端启动
 *
 * @author Hanyinh
 * @date 2020/9/20 11:32
 */
public class RpcServiceApplication {
    public static void main(String[] args) {
        RpcRemoteService rpcRemoteService = new RpcRemoteServiceImpl();

        // 方式一：Socket通信，发布服务
        // SocketServer server = new SocketServer();
        // server.publisher(rpcRemoteService);

        // 方式二：Netty通信，发布服务
        NettyServer server = new NettyServer(new RegisterCenterImpl(), "127.0.0.1:9090");
        server.bind(rpcRemoteService);
        server.publisher();

    }
}
