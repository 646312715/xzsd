package com.xzsd.app.clientOrder.controller;

import com.alibaba.fastjson.JSON;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.clientGoods.entity.CommentInfo;
import com.xzsd.app.clientOrder.entity.ClientOrderGoodsInfo;
import com.xzsd.app.clientOrder.entity.ClientOrderInfo;
import com.xzsd.app.clientOrder.entity.EvaluateInfo;
import com.xzsd.app.clientOrder.service.ClientOrderServices;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/clientOrder")
public class ClientOrderController {
    private static final Logger logger = LoggerFactory.getLogger(ClientOrderController.class);

    @Resource
    private ClientOrderServices clientOrderServices;


    @PostMapping("addOrder")
    public AppResponse addOrder(ClientOrderGoodsInfo clientOrderGoodsInfo, String storeId,String shopCartId){
        try{
            return clientOrderServices.addOrder(clientOrderGoodsInfo,storeId,shopCartId);
        }catch (Exception e){
            logger.error("新增订单失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询订单列表
     * @param clientOrderInfo
     * @return
     */
    @PostMapping("listOrder")
    public AppResponse listOrder(ClientOrderInfo clientOrderInfo){
        try{
            return clientOrderServices.listOrder(clientOrderInfo);
        }catch (Exception e){
            logger.error("查询订单失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改订单状态
     * @param clientOrderInfo
     * @return
     * @Author feng
     * @Date 2020-04-14
     */
    @PostMapping("updateOrderState")
    public AppResponse updateOrderState(ClientOrderInfo clientOrderInfo){
        try{
            String userId = SecurityUtils.getCurrentUserId();
            clientOrderInfo.setUpdateUser(userId);
            return clientOrderServices.updateOrderState(clientOrderInfo);
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
    @PostMapping("listOrderDeepen")
    public AppResponse listOrderDeepen(String orderId){
        try{
            return clientOrderServices.listOrderDeepen(orderId);
        }catch (Exception e){
            logger.error("订单详情查询失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询订单评价商品信息列表
     * @param orderId
     * @return
     * @author feng
     * @date 2020-4-22
     */
    @PostMapping("listGoodsForEvaluate")
    public AppResponse listGoodsForEvaluate(String orderId){
        try{
            return clientOrderServices.listGoodsForEvaluate(orderId);
        }catch (Exception e){
            logger.error("查询订单评价商品信息列表失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 新增订单商品评价
     * @param json
     * @return
     * @author feng
     * @date 2020-4-22
     */
    @PostMapping("addGoodsEvaluate")
    public AppResponse addGoodsEvaluate(@RequestBody  String json){
        try{
            System.out.println(json);
            EvaluateInfo evaluateInfo = JSON.parseObject(json,EvaluateInfo.class);
            System.out.println(evaluateInfo);
            return clientOrderServices.addGoodsEvaluate(evaluateInfo);
        }catch (Exception e){
            logger.error("新增订单商品评价失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
