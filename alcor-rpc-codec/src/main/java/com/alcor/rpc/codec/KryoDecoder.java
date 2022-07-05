package com.alcor.rpc.codec;

import com.alcor.rpc.Request;
import com.alcor.rpc.Response;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;

import java.io.ByteArrayInputStream;

/**
 * @author guchun
 * @description TODO
 * @date 2022/7/5 23:05
 */
public class KryoDecoder implements Decoder{

    private final ThreadLocal<Kryo> kryoThreadLocal = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.register(Response.class);
        kryo.register(Request.class);
        return kryo;
    });

    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             Input input = new Input(byteArrayInputStream)) {
            Kryo kryo = kryoThreadLocal.get();
            // byte->Object:从byte数组中反序列化出对对象
            Object o = kryo.readObject(input, clazz);
            kryoThreadLocal.remove();
            return clazz.cast(o);
        } catch (Exception e) {
            throw new SerializationException("Deserialization failed");
        }
    }
}
