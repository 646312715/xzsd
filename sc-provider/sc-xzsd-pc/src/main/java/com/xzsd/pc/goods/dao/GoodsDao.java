package com.xzsd.pc.goods.dao;



import com.xzsd.pc.goods.entity.GoodsInfo;
import com.xzsd.pc.goodsClassify.entity.GoodsClassifyInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsDao {
    /**
     * 新增商品
     * @param goodsInfo 商品信息
     * @return 新增结果
     */
    int addGoods(GoodsInfo goodsInfo);
    /**
     * 查询商品详情信息
     * @param itemId 商品编号
     * @return 商品信息
     */
    GoodsInfo getGoods(String itemId);
    /**
     *修改商品信息
     * @param goodsInfo 商品信息
     * @return 修改结果
     */
    int updateGoods(GoodsInfo goodsInfo);
    /**
     * 查询所有商品信息
     * @param goodsInfo 商品信息
     * @return 所有商品信息
     */
    List<GoodsInfo> listGoods(GoodsInfo goodsInfo);
    /**
     * 删除商品信息
     * @param listCode 商品编号列表
     * @param userId 修改人编号
     * @return 删除结果
     */
    int deleteGoods(@Param("listCode") List<String> listCode, @Param("userId") String userId);
    /**
     * 修改上下架状态
     * @param goodsInfos 商品所需集合
     * @return 状态修改结果
     */
    int updateGoodsShelfState(List<GoodsInfo> goodsInfos);
    /**
     * 增加浏览量
     * @param itemId 商品编号
     * @return 浏览量增加结果
     */
    int addLookAmount(String itemId);

    /**
     * 查询商品信息数量
     * @param goodsInfo 商品编号
     * @return
     */
    int findGoodCount(GoodsInfo goodsInfo);

    /**
     * 查询商品是否有轮播图
     * @param listCode
     * @return
     */
    int checkGoods(@Param("listCode")List<String> listCode);

    /**
     * 查询商品分类下拉框接口
     * @param id
     * @return
     */
    List<GoodsClassifyInfo> listGoodsClassify(String id);

}
