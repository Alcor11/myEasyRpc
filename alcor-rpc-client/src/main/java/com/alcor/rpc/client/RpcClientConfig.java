package com.alcor.rpc.client;

import com.alcor.rpc.Peer;
import com.alcor.rpc.codec.Decoder;
import com.alcor.rpc.codec.Encoder;
import com.alcor.rpc.codec.JSONDecoder;
import com.alcor.rpc.codec.JSONEncoder;
import com.alcor.rpc.transport.HTTPTransportClient;
import com.alcor.rpc.transport.TransportClient;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author guchun
 * @description TODO
 * @date 2022/6/7 15:14
 */
@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass =
            HTTPTransportClient.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private Class<? extends TransportSelector> selectorClass =
            RandomTransportSelector.class;
    private int connectCount = 1;
    // 发现服务后，设置config
//    private List<Peer> servers = Arrays.asList(
//            new Peer("127.0.0.1", 3000)
//    );
    private List<Peer> servers = new ArrayList<>();
    
}
