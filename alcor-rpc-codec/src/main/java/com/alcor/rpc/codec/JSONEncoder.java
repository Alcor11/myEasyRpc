package com.alcor.rpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * @author guchun
 * @description 基于json的序列化实现
 * @date 2022/6/6 20:12
 */
public class JSONEncoder implements Encoder{
    @Override
    public byte[] encode(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}
