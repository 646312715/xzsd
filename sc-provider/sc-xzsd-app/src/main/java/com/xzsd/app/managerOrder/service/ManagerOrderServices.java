package com.xzsd.app.managerOrder.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.managerOrder.dao.ManagerOrderDao;
import com.xzsd.app.managerOrder.entity.ManagerOrderGoodsInfo;
import com.xzsd.app.managerOrder.entity.ManagerOrderInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManagerOrderServices {

    @Resource
    private ManagerOrderDao managerOrderDao;

    /**
     * 查询订单列表
     * @param managerOrderInfo
     * @return
     * @author feng
     * @date 2020-4-22
     */
    public AppResponse listManagerOrders(ManagerOrderInfo managerOrderInfo){
        PageHelper.startPage(managerOrderInfo.getPageNum(), managerOrderInfo.getPageSize());
        String orderStateId = managerOrderInfo.getOrderStateId();
        String userId = managerOrderInfo.getUserId();
        //查找订单
        List<ManagerOrderInfo> managerOrderInfoList = managerOrderDao.listManagerOrders(orderStateId,userId);
        if (managerOrderInfoList.size() == 0){
            return AppResponse.notFound("未找到订单");
        }
        List<String> orderIds = new ArrayList<String>();
        Map<String,Integer> map = new HashMap<String,Integer>();
        for (int index = 0 ; index < managerOrderInfoList.size() ; index++){
            ManagerOrderInfo managerOrderInfo1 = managerOrderInfoList.get(index);
            map.put(managerOrderInfo1.getOrderId(),index);
            orderIds.add(managerOrderInfo1.getOrderId());
        }
        //查找订单商品
        List<ManagerOrderGoodsInfo> managerOrderGoodsInfoList = managerOrderDao.listManagerOrdersGoods(orderIds);
        if (managerOrderGoodsInfoList.size() == 0){
            return AppResponse.notFound("未找到订单商品");
        }
        //订单商品存入订单
        for (int index = 0 ; index <managerOrderGoodsInfoList.size() ; index++){
            ManagerOrderGoodsInfo clientOrderGoodsInfo = managerOrderGoodsInfoList.get(index);
            managerOrderInfoList.get(map.get(clientOrderGoodsInfo.getOrderId())).setGoodsList(clientOrderGoodsInfo);
        }
        PageInfo<ManagerOrderInfo> pageData = new PageInfo<ManagerOrderInfo>(managerOrderInfoList);
        return AppResponse.success("订单查询成功！", pageData);
    }

    /**
     * 修改订单状态
     *
     * @param managerOrderInfo
     * @return
     * @author feng
     * @date 2020-4-22
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateManagerOrderState(ManagerOrderInfo managerOrderInfo) {
        AppResponse appResponse = AppResponse.success("修改状态成功！");
        int count = managerOrderDao.updateManagerOrderState(managerOrderInfo);
        if (0 == count) {
            appResponse = AppResponse.bizError("数据已更新，请重试！");
        }
        return appResponse;
    }
    /**
     * 查询订单详情
     *
     * @param orderId
     * @return
     * @author feng
     * @date 2020-4-22
     */
    public AppResponse listManagerOrderDeepen(String orderId){
        //查找订单
        ManagerOrderInfo managerOrderInfo = managerOrderDao.listManagerOrderDeepen(orderId);
        if (managerOrderInfo == null){
            return AppResponse.notFound("未找到数据");
        }
        //查找订单商品
        List<String> orderGoodsList = new ArrayList<String>();
        orderGoodsList.add(orderId);
        List<ManagerOrderGoodsInfo> managerOrderGoodsInfoList = managerOrderDao.listManagerOrdersGoods(orderGoodsList);
        if (managerOrderGoodsInfoList.size() == 0){
            return AppResponse.notFound("未找到数据");
        }
        managerOrderInfo.setGoodsList(managerOrderGoodsInfoList);
        return AppResponse.success("查询订单详情成功",managerOrderInfo);
    }
}
