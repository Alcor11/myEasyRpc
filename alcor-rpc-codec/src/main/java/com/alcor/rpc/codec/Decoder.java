package com.alcor.rpc.codec;

/**
 * @author guchun
 * @description TODO
 * @date 2022/6/6 20:10
 */
public interface Decoder {

    <T> T decode(byte[] bytes, Class<T> clazz);

}
