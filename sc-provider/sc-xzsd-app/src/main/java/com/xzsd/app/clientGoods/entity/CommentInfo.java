package com.xzsd.app.clientGoods.entity;

import java.util.ArrayList;
import java.util.List;

public class CommentInfo {
    /**
     * 商品编号
     */
    private String goodsId;
    /**
     * 用户编号
     */
    private String userId;
    /**
     * 用户账号
     */
    private String userAcct;
    /**
     * 评论编号
     */
    private String evaluateId;
    /**
     * 评价内容
     */
    private String evaluateContent;
    /**
     * 商品等级
     */
    private int evaluateScore;
    /**
     * 评价图片输出列表
     */
    private List<ImageDO> imageList = new ArrayList<ImageDO>();
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
     * 页码
     */
    private int pageSize;
    /**
     * 页数
     */
    private int pageNum;

    public String getGoodsId() {
        return goodsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setImageList(List<ImageDO> imageList) {
        this.imageList = imageList;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(String evaluateId) {
        this.evaluateId = evaluateId;
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

    public String getUserAcct() {
        return userAcct;
    }

    public void setUserAcct(String userAcct) {
        this.userAcct = userAcct;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public int getEvaluateScore() {
        return evaluateScore;
    }

    public void setEvaluateScore(int evaluateScore) {
        this.evaluateScore = evaluateScore;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<ImageDO> getImageList() {
        return imageList;
    }

    public void setImageList(ImageDO imageDO) {
        imageList.add(imageDO);
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
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
        return "CommentInfo{" +
                "userAcct='" + userAcct + '\'' +
                ", evaluateContent='" + evaluateContent + '\'' +
                ", evaluateScore='" + evaluateScore + '\'' +
                ", imageList=" + imageList +
                ", isDelete=" + isDelete +
                ", createTime='" + createTime + '\'' +
                ", createUser='" + createUser + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
