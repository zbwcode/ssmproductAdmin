package com.itheima.ssm.controller;


import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

//    @RequestMapping("/findAll.do")
//    public ModelAndView findAllOrders() throws Exception{
//        ModelAndView mv = new ModelAndView();
//        List<Orders> ordersList = ordersService.findAll();
//        mv.addObject("ordersList",ordersList);
//        mv.setViewName("orders-list");
//        return mv;
//    }

    @RequestMapping("/findAll.do")
    public ModelAndView findAllOrders(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,
                                      @RequestParam(name = "pageSize",required = true,defaultValue = "4") Integer pageSize) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll(page,pageSize);
        PageInfo info  = new PageInfo(ordersList);
        mv.addObject("pageInfo",info);
        mv.setViewName("orders-pages-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String orderId){
        ModelAndView mv = new ModelAndView();
        Orders order = ordersService.findOrdersById(orderId);
        mv.addObject("orders",order);
        mv.setViewName("orders-show");
        return  mv;
    }
}
