package com.alcor.rpc.common.utils;

import junit.framework.TestCase;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author guchun
 * @description TODO
 * @date 2022/6/6 20:03
 */
public class ReflectionUtilsTest extends TestCase {

    @Test
    public void testNewInstance() {
        TestClass t = ReflectionUtils.newInstance(TestClass.class);
        assertNotNull(t);
    }

    public void testGetPublicMethods() {
        Method[] publicMethods = ReflectionUtils.getPublicMethods(TestClass.class);
        assertEquals(1, publicMethods.length);

        String name = publicMethods[0].getName();
        assertEquals("b", name);
    }

    public void testInvoke() {
        Method[] publicMethods = ReflectionUtils.getPublicMethods(TestClass.class);
        TestClass testClass = new TestClass();
        Object invoke = ReflectionUtils.invoke(testClass, publicMethods[0]);

        assertEquals("b", invoke);
    }
}