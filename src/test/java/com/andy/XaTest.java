package com.andy;

import com.andy.model.Order;
import com.andy.service.Order0Service;
import com.andy.service.Order1Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Andy
 * @date 2020/4/24 10:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={XAApplicaiton.class})// 指定启动类
public class XaTest {

    @Autowired
    private Order0Service order0Service;

    @Autowired
    private Order1Service order1Service;

    /**
     * 测试xa 库数据库事务
     * */
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void testSave(){


        Order order0 = new Order();
        order0.setId(1);
        order0.setName("ds0数据库");
        order0.setType("ds0");

        Order order1 = new Order();
        order1.setId(2);
        order1.setName("ds1数据库");
        order1.setType("ds1");


        order0Service.saveOrder(order0);
        order1Service.saveOrder(order1);
    }
}
