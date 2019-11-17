package com.xxq.rpc.client;

import com.xxq.rpc.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author xq
 */
public class RemoteInvocationHandler implements InvocationHandler {

    private String host;

    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 构造请求参数
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        System.out.println("in :" + method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setArgs(args);

        // 发送请求
        RpcNetTransport rpcNetTransport = new RpcNetTransport(host, port);
        
        // 拿到结果
        Object result = rpcNetTransport.send(rpcRequest);

        // 返回结果
        return result;
    }
}
