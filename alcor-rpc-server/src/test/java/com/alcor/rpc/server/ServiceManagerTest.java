package com.alcor.rpc.server;

import com.alcor.rpc.Peer;
import com.alcor.rpc.Request;
import com.alcor.rpc.ServiceDescriptor;
import com.alcor.rpc.common.utils.ReflectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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
        List<Peer> peerList = new ArrayList<>();

        Peer peer1 = new Peer("127.0.0.1", 3000);
        Peer peer2 = new Peer("127.0.0.1", 3000);
        peerList.add(peer1);
        peerList.add(peer2);

        sm.register(TestInterface.class, bean, peerList);
    }

    @Test
    public void testRegister() {
        List<Peer> peerList = new ArrayList<>();

        Peer peer1 = new Peer("127.0.0.1", 3000);
        Peer peer2 = new Peer("127.0.0.1", 3000);
        peerList.add(peer1);
        peerList.add(peer2);

        TestInterface bean = new TestClass();
        sm.register(TestInterface.class, bean, peerList);
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