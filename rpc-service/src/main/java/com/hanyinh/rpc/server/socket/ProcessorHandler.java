package com.hanyinh.rpc.server.socket;

import com.alibaba.fastjson.JSON;
import com.hanyinh.rpc.common.RpcRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * 线程池异步处理方法
 *
 * @author Hanyinh
 * @date 2020/9/21 15:51
 */
@Slf4j
public class ProcessorHandler implements Runnable {

    private Socket socket;

    private Object service;

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {
            // 获取客户端发送的请求
            RpcRequest rpcRequest = (RpcRequest) ois.readObject();
            log.error("收到客户端的数据：{}", JSON.toJSONString(rpcRequest));
            // 通过反射进行方法调用
            Object result = invoke(rpcRequest);
            // 把处理结果返回给客户端
            oos.writeObject(result);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object invoke(RpcRequest rpcRequest) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> clazz = Class.forName(rpcRequest.getClassName());
        Method method = clazz.getMethod(rpcRequest.getMethodName(), rpcRequest.getTypes());
        return method.invoke(service, rpcRequest.getParams());
    }
}
