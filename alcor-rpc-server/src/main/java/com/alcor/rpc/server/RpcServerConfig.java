package com.alcor.rpc.server;


import com.alcor.rpc.codec.Decoder;
import com.alcor.rpc.codec.Encoder;
import com.alcor.rpc.codec.JSONDecoder;
import com.alcor.rpc.codec.JSONEncoder;
import com.alcor.rpc.transport.HTTPTransportServer;
import com.alcor.rpc.transport.TransportServer;
import lombok.Data;

/**
 * @author guchun
 * @description server配置
 * @date 2022/6/6 20:48
 */
@Data
public class RpcServerConfig {
    private Class<? extends TransportServer> transportClass = HTTPTransportServer.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private int port = 3000;
}
