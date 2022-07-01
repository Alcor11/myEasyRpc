package com.alcor.rpc.codec;

import com.caucho.hessian.io.HessianInput;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author guchun
 * @description hessian序列化decoder
 * @date 2022/7/1 23:12
 */
@Slf4j
public class HessianDecoder implements Decoder{
    HessianInput hessianInput = null;
    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes)) {
            hessianInput = new HessianInput(byteArrayInputStream);
            return (T) hessianInput.readObject(clazz);
        } catch (IOException e) {
            log.error("反序列化错误" + e);
            throw new SerializationException("反序列化错误");
        } finally {
            if (hessianInput != null) {
                hessianInput.close();
            }
        }
    }
}
