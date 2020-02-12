package com.itheima.ssm.service;

import com.itheima.ssm.domain.Orders;

import java.util.List;

public interface OrdersService {
//    List<Orders> findAll() throws Exception;
    //分页查询
    List<Orders> findAll(Integer page,Integer pageSize) throws Exception;

    Orders findOrdersById(String orderId);
}
