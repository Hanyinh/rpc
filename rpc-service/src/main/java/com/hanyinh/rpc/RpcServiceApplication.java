package com.hanyinh.rpc;

import com.hanyinh.rpc.remote.RpcRemoteService;
import com.hanyinh.rpc.remote.impl.RpcRemoteServiceImpl;
import com.hanyinh.rpc.server.NettyServer;

/**
 * 服务端启动
 *
 * @author Hanyinh
 * @date 2020/9/20 11:32
 */
public class RpcServiceApplication {
    public static void main(String[] args) {
        // 发布服务
        NettyServer server = new NettyServer();
        RpcRemoteService rpcRemoteService = new RpcRemoteServiceImpl();
        server.publisher(rpcRemoteService);
    }
}
