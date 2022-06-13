package com.alcor.rpc;


import org.omg.CORBA.Request;

import java.util.List;

/**
 * @author guchun
 * @description 注册中心
 * @date 2022/6/9 16:59
 */
public interface Registry {

    <T> void register(Class<T> interfaceClass, List<Peer> peers);

    <T> List<Peer> lookup(Class<T> interfaceClass);

}
