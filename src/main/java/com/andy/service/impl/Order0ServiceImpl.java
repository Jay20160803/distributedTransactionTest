package com.andy.service.impl;

import com.andy.mapper.ds0.Order0Dao;
import com.andy.model.Order;
import com.andy.service.Order0Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Andy
 * @date 2020/4/24 10:07
 */
@Service
public class Order0ServiceImpl implements Order0Service {

    @Autowired
    private Order0Dao order0Dao;

    @Override
    public void saveOrder(Order order) {
        int num = order0Dao.insert(order);
        if(1 != num){
            throw new RuntimeException("Order0ServiceImpl.saveOrder 失败");
        }
    }
}
