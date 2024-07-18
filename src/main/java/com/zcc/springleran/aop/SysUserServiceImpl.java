package com.zcc.springleran.aop;

import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: zcc
 * @createDate: 2024/7/18
 * @version: 1.0
 */
@Service
public class SysUserServiceImpl implements SysUserService{
    @Override
    public void add() {
        System.out.println("add方法调用");
        test();
        delete();
    }

    @Override
    public void delete() {
        System.out.println("delete方法调用");
    }

    private void test() {
        System.out.println("测试代理");
    }

    @Override
    public void updateUser() {
        System.out.println("updateUser方法调用");
    }
}
