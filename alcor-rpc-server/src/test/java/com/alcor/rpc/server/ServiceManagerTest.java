package com.alcor.rpc.server;

import com.alcor.rpc.Request;
import com.alcor.rpc.ServiceDescriptor;
import com.alcor.rpc.common.utils.ReflectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author guchun
 * @description TODO
 * @date 2022/6/6 21:18
 */
public class ServiceManagerTest {

    ServiceManager sm;
    @Before
    public void init() {
        sm = new ServiceManager();

        TestInterface bean = new TestClass();
        sm.register(TestInterface.class, bean);
    }

    @Test
    public void testRegister() {
        TestInterface bean = new TestClass();
        sm.register(TestInterface.class, bean);
    }

    @Test
    public void testLookup() {
        Method method = ReflectionUtils.getPublicMethods(TestInterface.class)[0];
        ServiceDescriptor sdp = ServiceDescriptor.from(TestInterface.class, method);
        Request request = new Request();
        request.setService(sdp);

        ServiceInstance lookup = sm.lookup(request);
        System.out.println(lookup);
    }
}