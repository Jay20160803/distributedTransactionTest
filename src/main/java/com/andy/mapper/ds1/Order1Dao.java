package com.andy.mapper.ds1;

import com.andy.model.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Andy
 * @date 2020/4/24 10:02
 */
@Mapper
public interface Order1Dao {
    int insert(Order order);
}
