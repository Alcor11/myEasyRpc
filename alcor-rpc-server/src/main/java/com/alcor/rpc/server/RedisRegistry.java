package com.alcor.rpc.server;

import com.alcor.rpc.JedisUtils;
import com.alcor.rpc.Request;
import com.alcor.rpc.ServiceDescriptor;
import com.alcor.rpc.common.utils.ReflectionUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @author guchun
 * @description redis注册中心
 * @date 2022/6/10 16:07
 */
@Slf4j
public class RedisRegistry implements Registry{


    public RedisRegistry() {}

    @Override
    public <T> void register(Class<T> interfaceClass, T bean) {
        Method[] methods = ReflectionUtils.getPublicMethods(interfaceClass);
        for (Method method : methods) {
            ServiceDescriptor sdp = ServiceDescriptor.from(interfaceClass, method);
            ServiceInstance sis = new ServiceInstance(bean, method);
            log.info("sis: " + sis);
            log.info("sdp: " + sdp);
            log.info("register service");

            JedisUtils.recordCache(JedisUtils.serialize(sdp), JedisUtils.serialize(sis));
        }
    }

    @Override
    public ServiceInstance lookup(Request request) {

        ServiceDescriptor service = request.getService();
        ServiceInstance instance = (ServiceInstance) JedisUtils.deserialize(JedisUtils.getObject(JedisUtils.serialize(service)));
        log.info(String.valueOf(instance));
        return instance;
    }
}
