package com.xzsd.app.clientHome.dao;

import com.xzsd.app.clientHome.entity.HotGoodsInfo;
import com.xzsd.app.clientHome.entity.SlideshowHomeInfo;

import java.util.List;

public interface ClientHomeDao {

    /**
     * 查询首页轮播图接口
     * @return
     */
    List<SlideshowHomeInfo> listRotationCharHome();

    /**
     * 查询热门商品接口
     * @return
     */
    List<HotGoodsInfo> listHotGoods(int hotGoodsShowNum);
    /**
     * 查询热门商品位展示数量
     * @return
     */
    int getHotGoodsShowNum();
}
