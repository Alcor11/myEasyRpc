package com.alcor.rpc.client;

import com.alcor.rpc.codec.Decoder;
import com.alcor.rpc.codec.Encoder;
import com.alcor.rpc.common.utils.ReflectionUtils;

import java.lang.reflect.Proxy;

/**
 * @author guchun
 * @description TODO
 * @date 2022/6/7 15:18
 */
public class RpcClient {

    private RpcClientConfig config;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RpcClient() {
        this(new RpcClientConfig());
    }
    public RpcClient(RpcClientConfig config) {
        this.config = config;

        this.encoder = ReflectionUtils.newInstance(this.config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(this.config.getDecoderClass());
        this.selector = ReflectionUtils.newInstance(this.config.getSelectorClass());

        this.selector.init(
                // TODO 这里访问注册中心获取servers
                this.config.getServers(),
                this.config.getConnectCount(),
                this.config.getTransportClass()
        );
    }

    public <T> T getProxy(Class<T> clazz) {
        // java自带动态代理
        return (T) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{clazz},
                new RemoteInvoker(clazz, encoder, decoder, selector)
        );
    }
}
