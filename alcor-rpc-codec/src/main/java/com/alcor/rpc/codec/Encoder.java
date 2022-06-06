package com.alcor.rpc.codec;

/**
 * @author guchun
 * @description 序列化
 * @date 2022/6/6 20:09
 */
public interface Encoder {
    byte[] encode(Object obj);
}
