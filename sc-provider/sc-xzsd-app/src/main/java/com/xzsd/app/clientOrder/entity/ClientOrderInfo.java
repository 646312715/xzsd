package com.xzsd.app.clientOrder.entity;

import java.util.ArrayList;
import java.util.List;

public class ClientOrderInfo {
    /**
     * 用户编号
     */
    private String userId;
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 门店编号
     */
    private String storeId;
    /**
     * 订单状态
     */
    private String orderStateId;
    /**
     * 订单总价
     */
    private String orderAllCost;
    /**
     * 订单商品总数量
     */
    private String orderAllGoodsCount;
    /**
     * 页码
     */
    private int pageSize;
    /**
     * 页数
     */
    private int pageNum;
    /**
     * 作废标记 0为存在，1为作废
     */
    private int isDelete;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 创建者
     */
    private String createUser;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 更新者
     */
    private String updateUser;
    /**
     * 版本号
     */
    private String version;

    /**
     * 商品列表
     */
    private List<ClientOrderGoodsInfo> clientOrderGoodsInfoList = new ArrayList<ClientOrderGoodsInfo>();

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getOrderStateId() {
        return orderStateId;
    }

    public void setOrderStateId(String orderStateId) {
        this.orderStateId = orderStateId;
    }

    public String getOrderAllCost() {
        return orderAllCost;
    }

    public void setOrderAllCost(String orderAllCost) {
        this.orderAllCost = orderAllCost;
    }

    public String getOrderAllGoodsCount() {
        return orderAllGoodsCount;
    }

    public void setOrderAllGoodsCount(String orderAllGoodsCount) {
        this.orderAllGoodsCount = orderAllGoodsCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<ClientOrderGoodsInfo> getClientOrderGoodsInfoList() {
        return clientOrderGoodsInfoList;
    }

    public void setClientOrderGoodsInfoList(ClientOrderGoodsInfo clientOrderGoodsInfo) {
        clientOrderGoodsInfoList.add(clientOrderGoodsInfo);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setClientOrderGoodsInfoList(List<ClientOrderGoodsInfo> clientOrderGoodsInfoList) {
        this.clientOrderGoodsInfoList = clientOrderGoodsInfoList;
    }

    @Override
    public String toString() {
        return "ClientOrderInfo{" +
                "orderId='" + orderId + '\'' +
                ", storeId='" + storeId + '\'' +
                ", orderStateId='" + orderStateId + '\'' +
                ", orderAllCost='" + orderAllCost + '\'' +
                ", orderAllGoodsCount='" + orderAllGoodsCount + '\'' +
                ", pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                ", isDelete=" + isDelete +
                ", createTime='" + createTime + '\'' +
                ", createUser='" + createUser + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", version='" + version + '\'' +
                ", clientOrderGoodsInfoList=" + clientOrderGoodsInfoList +
                '}';
    }
}

