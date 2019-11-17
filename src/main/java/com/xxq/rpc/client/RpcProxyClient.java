package com.xxq.rpc.client;

import com.xxq.rpc.IHelloService;

import java.lang.reflect.Proxy;

/**
 * @author xq
 */
public class RpcProxyClient {

    public <T> T clientProxy(final Class<IHelloService> interfaces, final String host, final int port) {
        return (T) Proxy.newProxyInstance(interfaces.getClassLoader(),
                new Class<?>[]{interfaces},
                new RemoteInvocationHandler(host, port));
    }

}
