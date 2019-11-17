package com.xxq.rpc.client;

import com.xxq.rpc.RpcRequest;

import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author xq
 */
public class RpcNetTransport {

    private String host;

    private int port;

    public RpcNetTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object send(RpcRequest rpcRequest) {
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        Socket socket = null;
        Object result = null;
        try {
            // 建立连接
            socket = new Socket(host, port);
            // 发起请求
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(rpcRequest);
            // 发出全部请求流
            outputStream.flush();

            // 接受响应
            inputStream = new ObjectInputStream(socket.getInputStream());
            result = inputStream.read();

        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;

    }

}
