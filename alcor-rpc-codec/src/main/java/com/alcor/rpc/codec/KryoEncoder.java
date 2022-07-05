package com.alcor.rpc.codec;

import com.alcor.rpc.Request;
import com.alcor.rpc.Response;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;

import java.io.ByteArrayOutputStream;

/**
 * @author guchun
 * @description TODO
 * @date 2022/7/5 23:05
 */
public class KryoEncoder implements Encoder{

    private final ThreadLocal<Kryo> kryoThreadLocal = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.register(Response.class);
        kryo.register(Request.class);
        return kryo;
    });

    @Override
    public byte[] encode(Object obj) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             Output output = new Output(byteArrayOutputStream)) {
            Kryo kryo = kryoThreadLocal.get();
            // Object->byte:将对象序列化为byte数组
            kryo.writeObject(output, obj);
            kryoThreadLocal.remove();
            return output.toBytes();
        } catch (Exception e) {
            throw new SerializationException("Serialization failed");
        }
    }
}
