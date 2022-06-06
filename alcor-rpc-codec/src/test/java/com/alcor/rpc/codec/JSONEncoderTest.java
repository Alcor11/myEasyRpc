package com.alcor.rpc.codec;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author guchun
 * @description TODO
 * @date 2022/6/6 20:14
 */
public class JSONEncoderTest extends TestCase {

    @Test
    public void testEncode() {
        Encoder encoder = new JSONEncoder();

        TestBean bean = new TestBean();
        bean.setAge(1);
        bean.setName("aas");
        byte[] bytes = encoder.encode(bean);
        assertNotNull(bytes);

    }
}