package com.alcor.rpc.transport;

/**
 * @author guchun
 * @description 启动、监听端口， 接受请求，关闭监听
 * @date 2022/6/6 20:23
 */
public interface TransportServer {

    void init(int port, RequestHandler handler);

    void start();

    void stop();
}
