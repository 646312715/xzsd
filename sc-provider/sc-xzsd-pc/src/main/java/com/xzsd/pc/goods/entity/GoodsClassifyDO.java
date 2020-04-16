package com.xzsd.pc.goods.entity;

import com.xzsd.pc.goodsClassify.entity.GoodsClassifyInfo;

import java.util.List;

public class GoodsClassifyDO {
    /**
     *商品分类信息集合
     */
    private List<GoodsClassifyInfo> goodsClassifyList;

    public List<GoodsClassifyInfo> getGoodsClassifyList() {
        return goodsClassifyList;
    }

    public void setGoodsClassifyList(List<GoodsClassifyInfo> goodsClassifyList) {
        this.goodsClassifyList = goodsClassifyList;
    }
}
