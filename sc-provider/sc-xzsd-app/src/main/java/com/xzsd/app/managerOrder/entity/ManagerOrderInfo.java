package com.xzsd.app.managerOrder.entity;

import com.xzsd.app.clientOrder.entity.ClientOrderGoodsInfo;

import java.util.ArrayList;
import java.util.List;

public class ManagerOrderInfo {
    /**
     * 用户编号
     */
    private String userId;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 店铺名称
     */
    private String storeName;
    /**
     * 店铺编号
     */
    private String storeId;
    /**
     * 详情地址
     */
    private String address;
    /**
     * 手机
     */
    private String phone;
    /**
     * 订单编号
     */
    private String orderId;
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
    private List<ManagerOrderGoodsInfo> goodsList = new ArrayList<ManagerOrderGoodsInfo>();

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public List<ManagerOrderGoodsInfo> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(ManagerOrderGoodsInfo goodsInfo) {
        goodsList.add(goodsInfo);
    }

    public void setGoodsList(List<ManagerOrderGoodsInfo> goodsList) {
        this.goodsList = goodsList;
    }

    @Override
    public String toString() {
        return "ManagerOrderInfo{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", orderId='" + orderId + '\'' +
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
                ", goodsList=" + goodsList +
                '}';
    }
}
