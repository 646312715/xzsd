package com.xzsd.app.managerOrder.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.managerOrder.entity.ManagerOrderInfo;
import com.xzsd.app.managerOrder.service.ManagerOrderServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("managerOrder")
public class ManagerOrderController {
    private static final Logger logger = LoggerFactory.getLogger(ManagerOrderController.class);

    @Resource
    private ManagerOrderServices managerOrderServices;

    /**
     * 查询订单列表
     * @param managerOrderInfo
     * @return
     */
    @PostMapping("listManagerOrders")
    public AppResponse listManagerOrders(ManagerOrderInfo managerOrderInfo){
        try{
            //String userId = SecurityUtils.getCurrentUserId();
            String userId = "2020040922283411448";
            managerOrderInfo.setUserId(userId);
            return managerOrderServices.listManagerOrders(managerOrderInfo);
        }catch (Exception e){
            logger.error("查询订单失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改订单状态
     * @param managerOrderInfo
     * @return
     * @Author feng
     * @Date 2020-04-14
     */
    @PostMapping("updateManagerOrderState")
    public AppResponse updateManagerOrderState(ManagerOrderInfo managerOrderInfo){
        try{
            String userId = SecurityUtils.getCurrentUserId();
            managerOrderInfo.setUpdateUser(userId);
            return managerOrderServices.updateManagerOrderState(managerOrderInfo);
        }catch (Exception e){
            logger.error("订单状态修改失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询订单详情
     *
     * @param orderId
     * @return
     * @author feng
     * @date 2020-4-22
     */
    @PostMapping("listManagerOrderDeepen")
    public AppResponse listManagerOrderDeepen(String orderId){
        try{
            return managerOrderServices.listManagerOrderDeepen(orderId);
        }catch (Exception e){
            logger.error("订单详情查询失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
