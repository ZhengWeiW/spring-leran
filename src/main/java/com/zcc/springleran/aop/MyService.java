package com.zcc.springleran.aop;

import org.springframework.stereotype.Service;

/**
 * @description:
 * 演示
 * 5. 类内部自调用
 * 场景描述：当一个Bean内部的方法直接调用同一个Bean内部的另一个方法时（无论该方法是否私有），AOP可能无法拦截这个内部调用（取决于调用方式）。
 * 原因：AOP是基于代理的，如果调用是通过原始对象而非代理对象进行的，那么AOP将无法拦截
 * @author: zcc
 * @createDate: 2024/7/18
 * @version: 1.0
 */
@Service
public class MyService {
    public void publicMethod() {
        // 外部调用会通过代理，可以被AOP拦截（如果拦截的是publicMethod的话）
        System.out.println("调用 publicMethod");
        privateMethod(); // 内部自调用，不会通过代理，AOP无法拦截
    }

    // 假设这是受保护或包级私有的，仅用于说明内部自调用
    void privateMethod() {
        System.out.println("调用 privateMethod");
        // 这里是privateMethod的逻辑
    }
}
