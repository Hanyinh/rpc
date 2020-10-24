package com.hanyinh.rpc.server.netty;

import com.hanyinh.rpc.registry.RegisterCenter;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Netty的方式实现
 *
 * @author Hanyinh
 * @date 2020/10/24 16:08
 */
@Slf4j
public class NettyServer {

    private RegisterCenter registerCenter;

    private String serviceAddress;

    private Map<String, Object> handlerMap = new HashMap<>();

    public NettyServer(RegisterCenter registerCenter, String serviceAddress) {
        this.registerCenter = registerCenter;
        this.serviceAddress = serviceAddress;
    }

    public void publisher() {
        for (String serviceName : handlerMap.keySet()) {
            registerCenter.register(serviceName, serviceAddress);
        }
        // new 一个主线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // new 一个工作线程组
        EventLoopGroup workGroup = new NioEventLoopGroup(200);

        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerChannelInitializer(handlerMap))
                // 设置队列大小
                .option(ChannelOption.SO_BACKLOG, 1024)
                // 两小时内没有数据的通信时，TCP会自动发送一个活动探测数据报文
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        // 绑定端口，开始接收进来的连接
        try {
            String[] address = serviceAddress.split(":");
            String host = address[0];
            int port = Integer.parseInt(address[1]);
            ChannelFuture future = bootstrap.bind(host, port).sync();
            log.error("服务器启动开始监听，host：{}，端口：{}", host, port);
            // 等待连接被关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭主线程组
            bossGroup.shutdownGracefully();
            // 关闭工作线程组
            workGroup.shutdownGracefully();
        }
    }

    public void bind(Object... services) {
        for (Object servicec : services) {
            String serviceName = servicec.getClass().getInterfaces()[0].getName();
            handlerMap.put(serviceName, servicec);
        }

    }
}
