package com.xzsd.pc.order.entity;

import java.util.List;

public class OrderDeepenDO {
    /**
     * 订单详情
     */
    private List<OrderDeepenInfo> orderDeepenList;

    public List<OrderDeepenInfo> getOrderDeepenList() {
        return orderDeepenList;
    }

    public void setOrderDeepenList(List<OrderDeepenInfo> orderDeepenList) {
        this.orderDeepenList = orderDeepenList;
    }
}
