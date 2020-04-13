package com.xzsd.pc.slideshowHome.dao;

import com.xzsd.pc.goods.entity.GoodsInfo;
import com.xzsd.pc.slideshowHome.entity.SlideshowHomeInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;

import java.util.List;

/**
 * @author 64631
 */
public interface SlideshowHomeDao {
    /**
     * 新增首页轮播图接口
     * @param slideshowHomeInfo
     * @return
     */
    int addSlideshowHome(SlideshowHomeInfo slideshowHomeInfo);

    /**
     * 分页查询首页轮播图接口
     * @param slideshowHomeInfo
     * @return
     */
    List<SlideshowHomeInfo> listSlideshowHome(SlideshowHomeInfo slideshowHomeInfo);

    /**
     * 查询商品接口
     * @param goodsInfo
     * @return
     */
    List<GoodsInfo> listGoods(GoodsInfo goodsInfo);

    /**
     * 修改首页轮播图状态接口
     * @param slideshowHomeInfos
     * @return
     */
    int updateSlideshowHomeState(List<SlideshowHomeInfo> slideshowHomeInfos);

    /**
     * 删除首页轮播图接口
     * @param slideshowIds
     * @param userId
     * @return
     */
    int deleteSlideshowHome(@Param("slideshowIds") List<String> slideshowIds,@Param("userId") String userId);

    /**
     * 轮播图排序
     * @param slideshowNum
     * @return
     */
    int getSlideshowNum(int slideshowNum);

    /**
     * 轮播图响应商品和排序查重
     * @param goodsId
     * @return
     */
    int getGoodsCount(String goodsId);
}
