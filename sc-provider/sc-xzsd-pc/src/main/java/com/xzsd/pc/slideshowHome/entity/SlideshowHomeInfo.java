package com.xzsd.pc.slideshowHome.entity;


/**
 * @author 64631
 */
public class SlideshowHomeInfo {
    /**
     * 轮播图编号
     */
    private String slideshowId;

    /**
     * 轮播图排序
     */
    private int slideshowNum;

    /**
     * 轮播图图片路径
     */
    private String slideshowPath;

    /**
     * 有效期开始时间
     */
    private String startTime;

    /**
     * 有效期截止时间
     */
    private String endTime;

    /**
     * 商品编号
     */
    private String goodsId;

    /**
     * 轮播图状态编号
     */
    private int slideshowStateId;

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
     * 旧版本号
     */
    private String oldVersion;

    public String getSlideshowId() {
        return slideshowId;
    }

    public void setSlideshowId(String slideshowId) {
        this.slideshowId = slideshowId;
    }

    public int getSlideshowNum() {
        return slideshowNum;
    }

    public void setSlideshowNum(int slideshowNum) {
        this.slideshowNum = slideshowNum;
    }

    public String getSlideshowPath() {
        return slideshowPath;
    }

    public void setSlideshowPath(String slideshowPath) {
        this.slideshowPath = slideshowPath;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public int getSlideshowStateId() {
        return slideshowStateId;
    }

    public void setSlideshowStateId(int slideshowStateId) {
        this.slideshowStateId = slideshowStateId;
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

    public String getOldVersion() {
        return oldVersion;
    }

    public void setOldVersion(String oldVersion) {
        this.oldVersion = oldVersion;
    }

    @Override
    public String toString() {
        return "SlideshowHomeInfo{" +
                "slideshowId='" + slideshowId + '\'' +
                ", slideshowNum=" + slideshowNum +
                ", slideshowPath='" + slideshowPath + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", slideshowStateId=" + slideshowStateId +
                ", pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                ", isDelete=" + isDelete +
                ", createTime='" + createTime + '\'' +
                ", createUser='" + createUser + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", version='" + version + '\'' +
                ", oldVersion='" + oldVersion + '\'' +
                '}';
    }
}
