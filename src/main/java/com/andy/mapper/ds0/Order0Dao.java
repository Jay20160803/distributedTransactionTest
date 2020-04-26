package com.andy.mapper.ds0;

import com.andy.model.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Andy
 * @date 2020/4/24 10:02
 */
@Mapper
public interface Order0Dao {
    int insert(Order order);
}
