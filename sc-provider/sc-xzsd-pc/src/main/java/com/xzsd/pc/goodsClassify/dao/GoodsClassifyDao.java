package com.xzsd.pc.goodsClassify.dao;

import com.xzsd.pc.goodsClassify.entity.GoodsClassifyInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsClassifyDao {
    /**
     * 新增商品分类接口
     * @param goodsClassifyInfo
     * @return
     */
    int addGoodsClassify(GoodsClassifyInfo goodsClassifyInfo);

    /**
     * 查询商品分类详情接口
     * @param classifyId
     * @return
     */
    GoodsClassifyInfo getGoodsClassify(String classifyId);

    /**
     * 查询商品分类列表接口
     * @return
     */
    List<GoodsClassifyInfo> listAllGoodsClassify();

    /**
     * 修改商品分类接口
     * @param goodsClassifyInfo
     * @return
     */
    int updateGoodsClassify(GoodsClassifyInfo goodsClassifyInfo);

    /**
     * 删除商品分类接口
     * @param classifyId
     * @return
     */
    int deleteGoodsClassify(@Param("classifyId") String classifyId,@Param("userId") String userId);
}
