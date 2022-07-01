package com.alcor.rpc.example;

import com.alcor.rpc.client.RpcClient;
import com.alcor.rpc.client.RpcClientConfig;
import com.alcor.rpc.codec.HessianDecoder;
import com.alcor.rpc.codec.HessianEncoder;

/**
 * @author guchun
 * @description TODO
 * @date 2022/6/7 15:35
 */
public class Client {
    public static void main(String[] args) {
        RpcClientConfig rpcClientConfig = new RpcClientConfig();
        rpcClientConfig.setEncoderClass(HessianEncoder.class);
        rpcClientConfig.setDecoderClass(HessianDecoder.class);
        RpcClient client = new RpcClient(CalcService.class, rpcClientConfig);
        CalcService service = client.getProxy(CalcService.class);

        int r1 = service.add(1, 2);
        int r2 = service.minus(10, 8);

        System.out.println(r1);
        System.out.println(r2);
    }
}
