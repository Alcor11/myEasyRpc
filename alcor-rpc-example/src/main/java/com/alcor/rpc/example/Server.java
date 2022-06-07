package com.alcor.rpc.example;

import com.alcor.rpc.server.RpcServer;

/**
 * @author guchun
 * @description TODO
 * @date 2022/6/7 15:35
 */
public class Server {
    public static void main(String[] args) {
        RpcServer server = new RpcServer();
        server.register(CalcService.class, new CalcServiceImpl());
        server.start();
    }
}
