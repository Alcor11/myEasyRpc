package com.alcor.rpc.server;

import com.alcor.rpc.Request;
import com.alcor.rpc.common.utils.ReflectionUtils;

/**
 * @author guchun
 * @description 调用具体服务
 * @date 2022/6/6 21:37
 */
public class ServiceInvoker {
    public Object invoke(ServiceInstance service, Request request) {
        return ReflectionUtils.invoke(
                service.getTarget(),
                service.getMethod(),
                request.getParameters());
    }
}
