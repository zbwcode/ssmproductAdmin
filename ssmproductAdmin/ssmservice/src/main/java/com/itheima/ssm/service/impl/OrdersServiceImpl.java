package com.itheima.ssm.service.impl;


import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.OrdersDao;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService{
    @Autowired
    private OrdersDao ordersDao;
    @Override
    public List<Orders> findAll(Integer page,Integer pageSize) throws Exception{
        PageHelper.startPage(page,pageSize);
        return ordersDao.findAll();
    }

    @Override
    public Orders findOrdersById(String orderId) {
        return ordersDao.findOrdersById(orderId);
    }
}
