package com.alcor.rpc.transport;

import com.alcor.rpc.Peer;

import java.io.InputStream;

/**
 * @author guchun
 * @description 创建链接，发送数据并等待响应，关闭连接
 * @date 2022/6/6 20:21
 */
public interface TransportClient {
    void connect(Peer peer);

    InputStream write(InputStream data);

    void close();
}
