package com.xzsd.pc.order.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.order.entity.OrderInfo;
import com.xzsd.pc.order.service.OrderServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Resource
    private OrderServices orderServices;

    /**
     * 查询订单详情
     * @param orderId
     * @return
     * @Author feng
     * @Date 2020-04-14
     */
    @PostMapping("getListOrder")
    public AppResponse getListOrder(String orderId){
        try{
            return orderServices.getListOrder(orderId);
        }catch (Exception e){
            logger.error("查询订单详情失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 查询订单列表
     * @param orderInfo
     * @return
     * @Author feng
     * @Date 2020-04-14
     */
    @PostMapping("listOrders")
    public AppResponse listOrders(OrderInfo orderInfo){
        try{
            String loginId = SecurityUtils.getCurrentUserId();
            return orderServices.listOrders(orderInfo,loginId);
        }catch (Exception e){
            logger.error("查询订单详情失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改订单状态
     * @param orderInfo
     * @return
     * @Author feng
     * @Date 2020-04-14
     */
    @PostMapping("updateOrderState")
    public AppResponse updateOrderState(OrderInfo orderInfo){
        try{
            orderInfo.setUpdateUser(SecurityUtils.getCurrentUserId());
            return orderServices.updateOrderState(orderInfo);
        }catch (Exception e){
            logger.error("订单状态修改失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
