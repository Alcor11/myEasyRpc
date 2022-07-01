package com.alcor.rpc.server;

import com.alcor.rpc.Peer;
import com.alcor.rpc.Request;
import com.alcor.rpc.Response;
import com.alcor.rpc.codec.Decoder;
import com.alcor.rpc.codec.Encoder;
import com.alcor.rpc.common.utils.ReflectionUtils;
import com.alcor.rpc.transport.RequestHandler;
import com.alcor.rpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guchun
 * @description Server
 * @date 2022/6/6 21:39
 */
@Slf4j
public class RpcServer {
    private RpcServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;

    private ServiceManager serviceManager;

//    private RedisRegistry serviceManager;

    private ServiceInvoker serviceInvoker;

    public RpcServer() {
        this(new RpcServerConfig());
    }
    public RpcServer(RpcServerConfig config) {
        this.config = config;

        // net
        this.net = ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(), this.handler);

        // codec
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());
        // service
        this.serviceInvoker = new ServiceInvoker();
        this.serviceManager = new ServiceManager();
    }


    public <T> void register(Class<T> interfaceClass, T bean) throws UnknownHostException {
        List<Peer> peerList = new ArrayList<>();
        // 获取部署端的localhost
        String host = InetAddress.getLocalHost().getHostAddress();
        peerList.add(new Peer(host, config.getPort()));
        serviceManager.register(interfaceClass, bean, peerList);
    }

    public void start() {
        this.net.start();
    }

    public void stop() {
        this.net.stop();
    }

    private RequestHandler handler = new RequestHandler() {
        Response resp = new Response();

        @Override
        public void onRequest(InputStream receive, OutputStream toResp) {
            try {
                byte[] bytes = IOUtils.readFully(receive, receive.available());
                Request request = decoder.decode(bytes, Request.class);
                log.info("get request {}", request);

                ServiceInstance sis = serviceManager.lookup(request);
                Object ret = serviceInvoker.invoke(sis, request);
                resp.setData(ret);
            } catch (Exception e) {
                log.warn(e.getMessage(), e);
                resp.setCode(1);
                resp.setMessage("RpcServer get error: " + e.getClass().getName()
                + " : " + e.getMessage());
            } finally {
                try {
                    byte[] outBytes = encoder.encode(resp);
                    toResp.write(outBytes);

                    log.info("response client");
                } catch (IOException e) {
                    log.warn(e.getMessage(), e);
                }
            }
        }
    };
}
