package com.alcor.rpc.example;

import com.alcor.rpc.annotation.RpcService;

import java.io.Serializable;

/**
 * @author guchun
 * @description TODO
 * @date 2022/6/7 15:36
 */
@RpcService(CalcService.class)
public class CalcServiceImpl implements CalcService, Serializable {
    @Override
    public Integer add(Integer a, Integer b) {
        return a+b;
    }

    @Override
    public Integer minus(Integer a, Integer b) {
        return a-b;
    }
}
