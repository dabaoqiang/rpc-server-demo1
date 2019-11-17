package com.xxq.rpc.server;

import com.xxq.rpc.IHelloService;

/**
 * @author xq
 */
public class ServerMain {
    public static void main(String[] args) {
        IHelloService iHelloService = new HelloServiceImpl();
        RpcProxyServer rpcProxyServer = new RpcProxyServer();
        rpcProxyServer.publisher(iHelloService, 8080);

    }
}
