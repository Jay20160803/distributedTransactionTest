package com.andy.service.impl;

import com.andy.mapper.ds1.Order1Dao;
import com.andy.model.Order;
import com.andy.service.Order1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Andy
 * @date 2020/4/24 10:08
 */
@Service
public class Order1ServiceImpl implements Order1Service {

    @Autowired
    private Order1Dao order1Dao;

    @Override
    public void saveOrder(Order order) {
        int num = order1Dao.insert(order);
        if(1 != num){
            throw new RuntimeException("Order1ServiceImpl.saveOrder 失败");
        }
    }
}
