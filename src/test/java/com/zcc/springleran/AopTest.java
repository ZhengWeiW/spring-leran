package com.zcc.springleran;

import com.zcc.springleran.aop.MyService;
import com.zcc.springleran.aop.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @description:
 * @author: zcc
 * @createDate: 2024/7/18
 * @version: 1.0
 */
@SpringBootTest
public class AopTest {
    @Resource
    private SysUserService sysUserService;

    @Resource
    private MyService myService;


    @Test
    public void testBefore(){
        sysUserService.add();
    }

    @Test
    public void testMyServiceBefore(){
        myService.publicMethod();
    }
}
