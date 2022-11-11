package com.zy.spring.aop.service;

public class UserServiceImpl implements UserService{
    @Override
    public void createUser() {
        System.out.println("正在执行创建用户业务逻辑");
    }
}
