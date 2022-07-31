package com.alcor.rpc.example;

import com.alcor.rpc.annotation.RpcService;

/**
 * @author guchun
 * @description TODO
 * @date 2022/6/7 15:36
 */
public interface CalcService {
    Integer add(Integer a, Integer b);
    Integer minus(Integer a, Integer b);
}
