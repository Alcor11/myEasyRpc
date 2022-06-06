package com.alcor.rpc.codec;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author guchun
 * @description TODO
 * @date 2022/6/6 20:16
 */
public class JSONDecoderTest extends TestCase {

    @Test
    public void testDecode() {
        Encoder encoder = new JSONEncoder();

        TestBean bean = new TestBean();
        bean.setAge(1);
        bean.setName("aas");
        byte[] bytes = encoder.encode(bean);

        Decoder decoder = new JSONDecoder();
        TestBean decode = decoder.decode(bytes, TestBean.class);
        assertEquals(decode.getName(), bean.getName());
    }
}