package com.alcor.rpc.example;

import java.io.Serializable;

/**
 * @author guchun
 * @description TODO
 * @date 2022/6/7 15:36
 */
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
