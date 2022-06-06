package com.alcor.rpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * @author guchun
 * @description 基于json反序列化
 * @date 2022/6/6 20:13
 */
public class JSONDecoder implements Decoder{
    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
