package com.xzsd.pc.order.dao;

import com.xzsd.pc.order.entity.OrderDeepenInfo;
import com.xzsd.pc.order.entity.OrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDao {

    /**
     * 查询订单详情接口
     * @param orderId
     * @return
     */
    List<OrderDeepenInfo> getListOrder(String orderId);

    /**
     * 分页查询订单接口
     * @param orderInfo
     * @param loginId
     * @return
     */
    List<OrderInfo> listOrders(@Param("orderInfo") OrderInfo orderInfo,@Param("loginId") String loginId);

    /**
     * 修改订单状态接口
     * @param orderInfos
     * @return
     */
    int updateOrderState(List<OrderInfo> orderInfos);
}
