package com.alcor.rpc.server;

import com.alcor.rpc.Request;
import com.alcor.rpc.common.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author guchun
 * @description 调用具体服务
 * @date 2022/6/6 21:37
 */
@Slf4j
public class ServiceInvoker {
    public Object invoke(ServiceInstance service, Request request) {
        log.info(request.getParameters().toString());
        return ReflectionUtils.invoke(
                service.getTarget(),
                service.getMethod(),
                request.getParameters());
    }
}
