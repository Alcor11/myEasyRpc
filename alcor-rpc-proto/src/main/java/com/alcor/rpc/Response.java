package com.alcor.rpc;

import lombok.Data;

/**
 * @author guchun
 * @description 表示RPC的返回
 * @date 2022/6/6 19:37
 */
@Data
public class Response {
    /**
     * 服务返回编码： 0-success  else fail
     */
    private int code = 0;
    /**
     * error message
     */
    private String message = "ok";
    /**
     * 数据
     */
    private Object data;
}
