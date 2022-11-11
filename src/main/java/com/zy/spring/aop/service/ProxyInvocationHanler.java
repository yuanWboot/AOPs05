package com.zy.spring.aop.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProxyInvocationHanler implements InvocationHandler {
    private Object target;  //目标对象
    private ProxyInvocationHanler(Object target){
        this.target = target;
    }
    /**
     * 在invoke()方法对目标方法进行增强
     * @param proxy 代理类对象
     * @param method 目标方法对象
     * @param args 目标方法实参
     * @return 目标方法运行后返回值
     * @throws Throwable 目标方法抛出的异常
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("==="+
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date())+"======");
        Object ret = method.invoke(target, args); //调用目标方法
        return ret;
    }

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        ProxyInvocationHanler invocationHanler = new ProxyInvocationHanler(userService);
        //动态创建代理类
        UserService userServiceProxy = (UserService)Proxy.newProxyInstance(userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(), invocationHanler);
        userServiceProxy.createUser();
    }
}
