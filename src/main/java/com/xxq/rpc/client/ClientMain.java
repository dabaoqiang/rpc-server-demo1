package com.xxq.rpc.client;

import com.xxq.rpc.IHelloService;

/**
 * @author xq
 */
public class ClientMain {
    public static void main(String[] args) {
        RpcProxyClient rpcProxyClient = new RpcProxyClient();
        IHelloService iHelloService = rpcProxyClient.
                clientProxy(IHelloService.class,
                        "localhost",
                        8080);
        String result = iHelloService.sayHello("Mic");
        System.out.println(result);
    }
}
