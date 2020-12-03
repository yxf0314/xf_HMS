package com.xf.dao;

import com.xf.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Collection;
import java.util.Map;

@Mapper
@Repository
public interface OrderMapper {
    Collection<Order> SelectOrder(Map<String,Object> map);
    int AddOrder(Order order);
    int leastroom(String typename);
    int checkorder(int id);
}
