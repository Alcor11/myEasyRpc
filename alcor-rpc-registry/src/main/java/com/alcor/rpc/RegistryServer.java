package com.alcor.rpc;

import org.omg.CORBA.Request;

import java.util.List;

/**
 * @author guchun
 * @description 注册中心服务
 * @date 2022/6/13 15:18
 */
public class RegistryServer implements Registry{

    @Override
    public <T> void register(Class<T> interfaceClass, List<Peer> peers) {
        JedisUtils.recordCache(interfaceClass, peers);
    }

    @Override
    public <T> List<Peer> lookup(Class<T> interfaceClass) {
        List<Peer> peers = (List<Peer>) JedisUtils.getObject(interfaceClass);
        return peers;
    }
}
