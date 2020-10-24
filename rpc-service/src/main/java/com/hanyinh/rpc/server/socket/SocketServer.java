package com.hanyinh.rpc.server.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Socket的方式实现
 *
 * @author Hanyinh
 * @date 2020/9/20 22:16
 */
@Slf4j
public class SocketServer {

    private static final String IP = "127.0.0.1";

    private static final Integer PORT = 8083;

    private ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 10, 5, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10), new CustomizableThreadFactory("rpc_"));


    public void publisher(Object service) {
        log.error("等待客户端请求。。。");
        start(service);
    }

    private void start(Object service) {
        try {
            // 连接socket
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                // 使用线程池进行异步处理
                pool.execute(new ProcessorHandler(socket, service));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
