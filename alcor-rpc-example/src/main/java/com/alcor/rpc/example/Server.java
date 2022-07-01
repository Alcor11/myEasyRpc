package com.alcor.rpc.example;

import com.alcor.rpc.codec.HessianDecoder;
import com.alcor.rpc.codec.HessianEncoder;
import com.alcor.rpc.server.RpcServer;
import com.alcor.rpc.server.RpcServerConfig;

import java.net.UnknownHostException;

/**
 * @author guchun
 * @description TODO
 * @date 2022/6/7 15:35
 */
public class Server {
    public static void main(String[] args) throws UnknownHostException {
        RpcServerConfig rpcServerConfig = new RpcServerConfig();
        rpcServerConfig.setEncoderClass(HessianEncoder.class);
        rpcServerConfig.setDecoderClass(HessianDecoder.class);
        RpcServer server = new RpcServer(rpcServerConfig);
        server.register(CalcService.class, new CalcServiceImpl());
        server.start();
    }
}
