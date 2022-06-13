package com.alcor.rpc.server;

import com.alcor.rpc.*;
import com.alcor.rpc.common.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author guchun
 * @description 管理rpc暴露的服务
 * @date 2022/6/6 20:54
 */
@Slf4j
public class ServiceManager {
    private Map<ServiceDescriptor, ServiceInstance> services;

    public ServiceManager() {
        this.services = new ConcurrentHashMap<>();
    }

    public <T> void register(Class<T> interfaceClass, T bean, List<Peer> peers) {
        Method[] publicMethods = ReflectionUtils.getPublicMethods(interfaceClass);
        for (Method method : publicMethods) {
            ServiceInstance sis = new ServiceInstance(bean, method);
            ServiceDescriptor sdp = ServiceDescriptor.from(interfaceClass, method);

            Registry registry = new RegistryServer();
            registry.register(interfaceClass, peers);

            services.put(sdp, sis);
            log.info("register service: {} {}", sdp.getClazz(), sdp.getMethod());
        }
    }

    public ServiceInstance lookup(Request request) {
        ServiceDescriptor sdp = request.getService();
        return services.get(sdp);
    }
}
