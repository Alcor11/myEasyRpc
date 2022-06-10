package com.alcor.rpc.client;

import com.alcor.rpc.Request;
import com.alcor.rpc.Response;
import com.alcor.rpc.ServiceDescriptor;
import com.alcor.rpc.codec.Decoder;
import com.alcor.rpc.codec.Encoder;
import com.alcor.rpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author guchun
 * @description 调用远程服务的代理类
 * @date 2022/6/7 15:23
 */
@Slf4j
public class RemoteInvoker implements InvocationHandler {

    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RemoteInvoker(Class clazz,
                         Encoder encoder,
                         Decoder decoder,
                         TransportSelector selector) {
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.selector = selector;
    }

    @Override
    public Object invoke(Object proxy,
                         Method method,
                         Object[] args) throws Throwable {
        // 动态代理实现类，将参数、方法封装到Request协议中
        Request request = new Request();
        request.setService(ServiceDescriptor.from(clazz, method));
        request.setParameters(args);
        // 调用invokeRemote方法发送并获取resp
        Response resp = invokeRemote(request);
        if (resp == null || resp.getCode() != 0) {
            throw new IllegalStateException("fail to invoke remote" + resp);
        }
        return resp.getData();
    }

    private Response invokeRemote(Request request) {
        Response resp = null;
        TransportClient client = null;

        try {
            // TODO : 在这里从注册中心获取
            client = selector.select();

            byte[] outBytes = encoder.encode(request);
            InputStream inputStream = client.write(new ByteArrayInputStream(outBytes));

            byte[] inBytes = IOUtils.readFully(inputStream, inputStream.available());
            resp = decoder.decode(inBytes, Response.class);
        } catch (IOException e) {
            log.warn(e.getMessage(), e);
            resp = new Response();
            resp.setCode(1);
            resp.setMessage("RpcClient got error:" + e.getClass() + e.getMessage());
        } finally {
            if (client != null) {
                selector.release(client);
            }
        }
        return resp;
    }
}
