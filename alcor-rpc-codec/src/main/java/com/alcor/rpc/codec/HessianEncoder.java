package com.alcor.rpc.codec;

import com.caucho.hessian.io.HessianOutput;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author guchun
 * @description hessian序列化encoder
 * @date 2022/7/1 23:05
 */
@Slf4j
public class HessianEncoder implements Encoder{
    @Override
    public byte[] encode(Object obj) {
        HessianOutput hessianOutput = null;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            hessianOutput = new HessianOutput(byteArrayOutputStream);
            hessianOutput.writeObject(obj);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            log.error("序列化发生错误" + e);
            throw new SerializationException("序列化错误");
        } finally {
            if (hessianOutput != null) {
                try {
                    hessianOutput.close();
                } catch (IOException e) {
                    log.error("关闭流发生错误");
                }
            }
        }
    }
}
