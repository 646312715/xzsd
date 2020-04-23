package com.xzsd.app.clientOrder.entity;

public class ClientOrderGoodsInfo {
    /**
     * 订单物品编号
     */
    private String orderGoodsId;
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 物品编号
     */
    private String goodsId;
    /**
     * 物品名称
     */
    private String goodsName;
    /**
     * 物品数量
     */
    private String cartGoodsCount;
    /**
     * 物品图片路径
     */
    private String goodsImagePath;
    /**
     * 商品描述
     */
    private String goodsDescribe;
    /**
     * 优惠金额
     */
    private String reducedPrice;
    /**
     * 客户选择的商品数量
     */
    private String clientGoodsNum;
    /**
     * 该商品总价
     */
    private String theGoodsAllPrice;
    /**
     * 商品单价
     */
    private String goodsPrice;
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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsImagePath() {
        return goodsImagePath;
    }

    public void setGoodsImagePath(String goodsImagePath) {
        this.goodsImagePath = goodsImagePath;
    }

    public String getGoodsDescribe() {
        return goodsDescribe;
    }

    public void setGoodsDescribe(String goodsDescribe) {
        this.goodsDescribe = goodsDescribe;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getClientGoodsNum() {
        return clientGoodsNum;
    }

    public void setClientGoodsNum(String clientGoodsNum) {
        this.clientGoodsNum = clientGoodsNum;
    }

    public String getOrderGoodsId() {
        return orderGoodsId;
    }

    public void setOrderGoodsId(String orderGoodsId) {
        this.orderGoodsId = orderGoodsId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getCartGoodsCount() {
        return cartGoodsCount;
    }

    public void setCartGoodsCount(String cartGoodsCount) {
        this.cartGoodsCount = cartGoodsCount;
    }

    public String getReducedPrice() {
        return reducedPrice;
    }

    public void setReducedPrice(String reducedPrice) {
        this.reducedPrice = reducedPrice;
    }

    public String getTheGoodsAllPrice() {
        return theGoodsAllPrice;
    }

    public void setTheGoodsAllPrice(String theGoodsAllPrice) {
        this.theGoodsAllPrice = theGoodsAllPrice;
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

    @Override
    public String toString() {
        return "ClientOrderGoodsInfo{" +
                "orderGoodsId='" + orderGoodsId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", cartGoodsCount='" + cartGoodsCount + '\'' +
                ", goodsImagePath='" + goodsImagePath + '\'' +
                ", goodsDescribe='" + goodsDescribe + '\'' +
                ", reducedPrice='" + reducedPrice + '\'' +
                ", clientGoodsNum='" + clientGoodsNum + '\'' +
                ", theGoodsAllPrice='" + theGoodsAllPrice + '\'' +
                ", goodsPrice='" + goodsPrice + '\'' +
                ", isDelete=" + isDelete +
                ", createTime='" + createTime + '\'' +
                ", createUser='" + createUser + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
