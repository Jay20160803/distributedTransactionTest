package com.andy.service.impl;

import com.andy.model.Order;
import com.andy.service.Order0Service;
import com.andy.service.Order1Service;
import com.andy.service.XaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Andy
 * @date 2020/4/26 9:23
 */
@Service
public class XaServiceImpl implements XaService {

    @Autowired
    private Order0Service order0Service;

    @Autowired
    private Order1Service order1Service;

    /**
     * 测试xa 库数据库事务
     * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void testXa(){


        Order order0 = new Order();
        order0.setId(3);
        order0.setName("ds3数据库");
        order0.setType("ds3");

        Order order1 = new Order();
        order1.setId(4);
        order1.setName("ds4数据库");
        order1.setType("ds4");


        order0Service.saveOrder(order0);
        order1Service.saveOrder(order1);
    }

}
