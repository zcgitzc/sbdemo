package com.zark.sbproject.boot.web.controller.test;

import com.zark.sbproject.boot.api.user.bo.UserBO;
import com.zark.sbproject.boot.api.user.service.UserLocalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 测试 @Transaction 主键。 搞清楚原理
 *
 * @author zark
 * @description
 * @date 2020-03-15 17:12
 */
@Slf4j
@RestController
public class TransactionalTestController {

    @Resource
    public UserLocalService userLocalService;


    @RequestMapping("/case1")
    public void case1() {
        UserBO userBO = new UserBO();
        userBO.setUsername("test-transaction-1");
        userLocalService.insert(userBO);
        log.error("没加事务，后面失败了，还是会插入成功");
        System.out.println(1 / 0);
    }

    @Transactional
    @RequestMapping("/case2")
    public void case2() {
        UserBO userBO = new UserBO();
        userBO.setUsername("test-transaction-2");
        userLocalService.insert(userBO);
        log.error("加了事务，后面失败了，插入失败");
        System.out.println(1 / 0);
    }

    @Transactional
    @RequestMapping("/case3")
    public void case3() throws Exception {
        UserBO userBO = new UserBO();
        userBO.setUsername("test-transaction-3");
        userLocalService.insert(userBO);
        log.error("加了事务，但是抛出的是Exception,Transaction不会捕获到（默认捕获 RuntimeException）所以还是插入成功");
        throw new Exception("加了事务，但是抛出的是Exception,Transaction不会捕获到（默认捕获 RuntimeException）所以还是插入成功");
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("/case4")
    public void case4() throws Exception {
        UserBO userBO = new UserBO();
        userBO.setUsername("test-transaction-4");
        userLocalService.insert(userBO);
        log.error("加了事务，指定捕获Exception,Transaction捕获到，所以插入失败");
        throw new Exception("加了事务，指定捕获Exception,Transaction捕获到，所以插入失败");
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("/case5")
    public void case5() {
        UserBO userBO = new UserBO();
        userBO.setUsername("test-transaction-5");
        userLocalService.insert(userBO);
        try {
            System.out.println(1 / 0);
        } catch (Exception e) {
            log.error("抛出的异常已经被捕获，Transaction感知不到，所以插入成功");
            //如果代码必须要catch,又需要事务回滚，只能手动设置事务状态，使其回滚
            //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }


    @RequestMapping("/case6")
    public void case6() {
        privateTransaction();
        log.info("private 方法用Transaction修饰，无效。所以插入成功");
    }


    @Transactional
    private void privateTransaction() {
        UserBO userBO = new UserBO();
        userBO.setUsername("test-transaction-6");
        userLocalService.insert(userBO);
        System.out.println(1 / 0);
    }


    @RequestMapping("/case7")
    public void case7() {
        this.insertData();
        log.info("Transactional是基于动态代理对象来实现的，而在类内部的方法的调用是通过this关键字来实现的，没有经过动态代理对象，所以事务回滚失效");
    }

    @Transactional
    public void insertData() {
        UserBO userBO = new UserBO();
        userBO.setUsername("test-transaction-7");
        userLocalService.insert(userBO);
        System.out.println(1 / 0);
    }


    @RequestMapping("/case8")
    public void case8() {
        userLocalService.insertWithTransaction();
    }
}
