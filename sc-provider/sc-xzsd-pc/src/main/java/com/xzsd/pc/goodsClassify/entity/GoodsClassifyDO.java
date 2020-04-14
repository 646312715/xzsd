package com.xzsd.pc.goodsClassify.entity;

import java.util.List;

public class GoodsClassifyDO {
    /**
     * 商品分类列表
     */
    private List<GoodsClassifyInfo> oneClassifyList;

    public List<GoodsClassifyInfo> getOneClassifyList() {
        return oneClassifyList;
    }

    public void setOneClassifyList(List<GoodsClassifyInfo> oneClassifyList) {
        this.oneClassifyList = oneClassifyList;
    }
}
