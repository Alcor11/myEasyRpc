package com.alcor.rpc;

import com.sun.xml.internal.ws.api.wsdl.parser.ServiceDescriptor;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author guchun
 * @description TODO
 * @date 2022/6/10 20:38
 */
public class JedisUtilsTest extends TestCase {

    @Test
    public void testSerialize() throws Exception {
        TestClass testClass = new TestClass();
        testClass.setAge(1);
        testClass.setName("test");

        byte[] serialize = JedisUtils.serialize(testClass);
        Object deserialize = JedisUtils.deserialize(serialize);
    }

    @Test
    public void testDeserialize() {
    }
}