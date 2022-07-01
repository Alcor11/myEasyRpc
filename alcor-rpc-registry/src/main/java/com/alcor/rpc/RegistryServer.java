package com.alcor.rpc;

import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guchun
 * @description 注册中心服务
 * @date 2022/6/13 15:18
 */
@Slf4j
public class RegistryServer implements Registry{

    @Override
    public <T> void register(Class<T> interfaceClass, List<Peer> peers) {
        List<Peer> peerList;
        // 判断是否已经存在
        if (lookup(interfaceClass) != null) {
            // 存在则添加进节点列表中
            peerList = lookup(interfaceClass);
            log.info("interface exist, new peers add to Redis");
            peerList.addAll(peers);
        } else {
            peerList = new ArrayList<>(peers);
        }
        JedisUtils.recordCache(interfaceClass, peerList);
    }

    @Override
    public <T> List<Peer> lookup(Class<T> interfaceClass) {
        List<Peer> peers = (List<Peer>) JedisUtils.getObject(interfaceClass);
        return peers;
    }
}
