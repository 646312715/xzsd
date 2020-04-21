package com.xzsd.app.clientGoods.entity;

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
     * 商品信息集合
     * @return
     */
    private List<ClientGoodsInfo> goodsList = new ArrayList<ClientGoodsInfo>();

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

    public List<ClientGoodsInfo> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(ClientGoodsInfo clientGoodsInfo) {
        goodsList.add(clientGoodsInfo);
    }
}
