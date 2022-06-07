package com.alcor.rpc.client;

import com.alcor.rpc.Peer;
import com.alcor.rpc.transport.TransportClient;

import java.util.List;

/**
 * @author guchun
 * @description 选择哪个server去连接
 * @date 2022/6/7 15:03
 */
public interface TransportSelector {

    /**
     * 初始化selector
     * @param peers 可以连接的server端点信息
     * @param count client与server建立多少个连接
     * @param clazz client实现class
     */
    void init(List<Peer> peers,
              int count,
              Class<? extends TransportClient> clazz);
    /**
     * 选择一个transport做交互
     * @return
     */
    TransportClient select();

    /**
     * 释放用完的client
     * @param client
     */
    void release(TransportClient client);

    void close();
}
