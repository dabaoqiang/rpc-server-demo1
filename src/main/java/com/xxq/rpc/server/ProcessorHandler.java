package com.xxq.rpc.server;

import com.xxq.rpc.RpcRequest;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @author xq
 */
public class ProcessorHandler implements Runnable {

    private Socket socket;

    private Object service;

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        ObjectInput objectInput = null;
        ObjectOutputStream outputStream = null;
        try {
            objectInput = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest) objectInput.readObject();
            Object result = invoke(rpcRequest);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(result);
            outputStream.flush();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            if (objectInput != null) {
                try {
                    objectInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    /**
     * @param rpcRequest
     */
    private Object invoke(RpcRequest rpcRequest) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object[] requestArgs = rpcRequest.getArgs();
        // 再找方法
        Class<?>[] types = new Class[rpcRequest.getArgs().length];
        for (int i = 0; i < requestArgs.length; i++) {
            types[i] = requestArgs[i].getClass();
        }
        String methodName = rpcRequest.getMethodName();
        Class<?> clazz = Class.forName(rpcRequest.getClassName());
        Method clazzMethod = clazz.getMethod(methodName, types);
        Object result = clazzMethod.invoke(service, requestArgs);
        return result;
    }
}
