package com.hanyinh.rpc.server.netty;

import com.alibaba.fastjson.JSON;
import com.hanyinh.rpc.common.RpcRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * netty服务端处理器
 *
 * @author Hanyinh
 * @date 2020/5/8 16:32
 */
@Slf4j
public class  NettyServerHandler extends ChannelInboundHandlerAdapter {

    private Map<String, Object> handlerMap = new HashMap<>();

    public NettyServerHandler(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }

    /**
    * 客户端连接会触发
    * @author Hanyinh
    * @date 2020/5/8 16:33
    * @param ctx
    * @return void
    */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.error("====================Channel active====================");
    }

    /**
    * 客户端发送消息会触发
    * @author Hanyinh
    * @date 2020/5/8 16:34
    * @param ctx
    * @param msg
    * @return void
    */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!(msg instanceof RpcRequest)) {
            log.error("服务器收到消息：{}", msg.toString());
            return;
        }
        RpcRequest rpcRequest = (RpcRequest) msg;
        log.error("服务器收到消息：{}", JSON.toJSONString(rpcRequest));
        Object result = new Object();
        if (handlerMap.containsKey(rpcRequest.getClassName())) {
            // 利用反射调用
            Object clazz = handlerMap.get(rpcRequest.getClassName());
            Method method = clazz.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getTypes());
            result = method.invoke(clazz, rpcRequest.getParams());
        }
        ctx.write("你好，我是服务端！");
        ctx.write(result);
        ctx.flush();
        ctx.close();
    }

    /**
    * 发生异常会触发
    * @author Hanyinh
    * @date 2020/5/8 16:36
    * @param ctx
    * @param cause
    * @return void
    */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
