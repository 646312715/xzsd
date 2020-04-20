package com.xzsd.pc.hotGoods.dao;

import com.xzsd.pc.goods.entity.GoodsInfo;
import com.xzsd.pc.hotGoods.entity.GoodsShowNumInfo;
import com.xzsd.pc.hotGoods.entity.HotGoodsInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HotGoodsDao {

    /**
     * 新增热门商品接口
     * @param hotGoodsInfo
     * @return
     */
    int addHotGoods(HotGoodsInfo hotGoodsInfo);

    /**
     * 查询热门商品详情接口
     * @param hotGoodsId
     * @return
     */
    HotGoodsInfo getHotGoods(String hotGoodsId);

    /**
     * 分页查询热门商品接口
     * @param hotGoodsInfo
     * @return
     */
    List<HotGoodsInfo> listHotGoods(HotGoodsInfo hotGoodsInfo);

    /**
     * 修改热门商品接口
     * @param hotGoodsInfo
     * @return
     */
    int updateHotGoods(HotGoodsInfo hotGoodsInfo);

    /**
     * 查询热门商品展示数量接口
     * @return
     */
    GoodsShowNumInfo getHotGoodsShowNum();

    /**
     * 修改热门商品数量展示接口
     * @param goodsShowNumInfo
     * @return
     */
    int updateHotGoodsShowNum(GoodsShowNumInfo goodsShowNumInfo);

    /**
     * 删除热门商品接口
     * @param hotGoodsId
     * @param loginId
     * @return
     */
    int deleteHotGoods(@Param("hotGoodsId") List<String> hotGoodsId, @Param("loginId") String loginId);

    /**
     * 热门商品查重
     * @param hotGoodsInfo
     * @return
     */
    int getGoodsCount(HotGoodsInfo hotGoodsInfo);
}
