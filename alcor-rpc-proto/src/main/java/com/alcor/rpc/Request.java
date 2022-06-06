package com.alcor.rpc;

import lombok.Data;

/**
 * @author guchun
 * @description 表示RPC的一个请求
 * @date 2022/6/6 19:35
 */
@Data
public class Request {

    private ServiceDescriptor service;

    private Object[] parameters;

}
