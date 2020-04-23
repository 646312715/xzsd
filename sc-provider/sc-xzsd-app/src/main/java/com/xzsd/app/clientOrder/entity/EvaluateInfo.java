package com.xzsd.app.clientOrder.entity;

import com.xzsd.app.clientGoods.entity.CommentImageInfo;
import com.xzsd.app.clientGoods.entity.CommentInfo;
import com.xzsd.app.clientGoods.entity.ImageDO;

import java.util.List;

public class EvaluateInfo {
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 评论列表
     */
    private List<CommentInfo> evaluateList;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<CommentInfo> getEvaluateList() {
        return evaluateList;
    }

    public void setEvaluateList(List<CommentInfo> evaluateList) {
        this.evaluateList = evaluateList;
    }

    @Override
    public String toString() {
        return "EvaluateInfo{" +
                "orderId='" + orderId + '\'' +
                ", evaluateList=" + evaluateList +
                '}';
    }
}
