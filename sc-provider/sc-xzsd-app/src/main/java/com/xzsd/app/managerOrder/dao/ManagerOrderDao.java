package com.xzsd.app.managerOrder.dao;

import com.xzsd.app.managerOrder.entity.ManagerOrderGoodsInfo;
import com.xzsd.app.managerOrder.entity.ManagerOrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManagerOrderDao {
    /**
     * 查询订单列表接口
     * @param orderStateId
     * @return
     */
    List<ManagerOrderInfo> listManagerOrders(@Param("orderStateId") String orderStateId,@Param("userId") String userId);

    /**
     * 查询订单商品列表接口
     * @param orderIds
     * @return
     */
    List<ManagerOrderGoodsInfo> listManagerOrdersGoods(@Param("orderIds") List<String> orderIds);

    /**
     * 修改店长订单状态接口
     * @param managerOrderInfo
     * @return
     */
    int updateManagerOrderState(ManagerOrderInfo managerOrderInfo);

    /**
     * 查询店长订单详情接口
     * @param orderId
     * @return
     */
    ManagerOrderInfo listManagerOrderDeepen(String orderId);
}
