package com.alcor.rpc;

import lombok.Data;

import java.io.Serializable;

/**
 * @author guchun
 * @description 表示RPC的一个请求
 * @date 2022/6/6 19:35
 */
@Data
public class Request implements Serializable {

    private ServiceDescriptor service;

    private Object[] parameters;

}
