package com.xxq.rpc;

/**
 * @author xq
 */
public interface IHelloService {
    //
    String sayHello(String content);

    /**
     * 保存用户
     * @param user
     * @return
     */
    String saveUser(User user);
}
