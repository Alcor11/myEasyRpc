package com.alcor.rpc.server;

import com.alcor.rpc.Request;
import com.alcor.rpc.server.ServiceInstance;

import java.util.List;

/**
 * @author guchun
 * @description 注册中心
 * @date 2022/6/9 16:59
 */
public interface Registry {

    <T> void register(Class<T> interfaceClass, T bean);

    ServiceInstance lookup(Request request);

}
