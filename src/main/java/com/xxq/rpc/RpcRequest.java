package com.xxq.rpc;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author xq
 */
public class RpcRequest implements Serializable {

    private static final long serialVersionUID = -5516588527718022090L;

    /**
     * {@link com.xxq.rpc.IHelloService#sayHello}
     */
    private String methodName;

    /**
     * sayHello("小强")
     */
    private Object[] args;

    /**
     * com.xxq.rpc.IHelloService
     */
    private String className;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "RpcRequest{" +
                "methodName='" + methodName + '\'' +
                ", args=" + Arrays.toString(args) +
                ", className='" + className + '\'' +
                '}';
    }
}
