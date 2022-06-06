package com.alcor.rpc.server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author guchun
 * @description 一个具体服务
 * @date 2022/6/6 20:53
 */
@Data
@AllArgsConstructor
public class ServiceInstance {
    private Object target;
    private Method method;

}
