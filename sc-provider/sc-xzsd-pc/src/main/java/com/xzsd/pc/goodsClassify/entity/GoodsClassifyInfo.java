package com.xzsd.pc.goodsClassify.entity;

import java.util.ArrayList;
import java.util.List;

public class GoodsClassifyInfo {
    /**
     * 商品分类编号
     */
    private String classifyId;
    /**
     * 商品分类名字
     */
    private String classifyName;
    /**
     * 商品分类父级编号
     */
    private String classifyParent;
    /**
     * 商品分类备注
     */
    private String classifyComment;
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
    /**
     * 二级分类列表
     */
    private List<GoodsClassifyInfo> twoClassifyList = new ArrayList<GoodsClassifyInfo>();

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getClassifyParent() {
        return classifyParent;
    }

    public void setClassifyParent(String classifyParent) {
        this.classifyParent = classifyParent;
    }

    public String getClassifyComment() {
        return classifyComment;
    }

    public void setClassifyComment(String classifyComment) {
        this.classifyComment = classifyComment;
    }

    public List<GoodsClassifyInfo> getTwoClassifyList() {
        return twoClassifyList;
    }

    public void setTwoClassifyList(GoodsClassifyInfo goodsClassifyInfo) {
        this.twoClassifyList.add(goodsClassifyInfo);
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
        return "GoodsClassifyInfo{" +
                "classifyId='" + classifyId + '\'' +
                ", classifyName='" + classifyName + '\'' +
                ", classifyParent='" + classifyParent + '\'' +
                ", classifyComment='" + classifyComment + '\'' +
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
