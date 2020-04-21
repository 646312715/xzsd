package com.xzsd.app.clientGoods.dao;

import com.xzsd.app.clientGoods.entity.ClientGoodsInfo;
import com.xzsd.app.clientGoods.entity.CommentImageInfo;
import com.xzsd.app.clientGoods.entity.CommentInfo;
import com.xzsd.app.clientGoods.entity.GoodsClassifyInfo;

import java.util.List;

public interface ClientGoodsDao {
    /**
     * 查询商品信息详情接口
     * @param goodsId
     * @return
     */
    ClientGoodsInfo getGoods(String goodsId);

    /**
     * 查询商品评价列表接口
     * @param commentInfo
     * @return
     */
    List<CommentInfo> listGoodsEvaluates(CommentInfo commentInfo);

    /**
     * 查询商品评价图片列表接口
     * @param commentInfo
     * @return
     */
    List<CommentImageInfo> listGoodsEvaluatesImage(CommentInfo commentInfo);

    /**
     * 查询商品分类列接口
     * @param classifyId
     * @return
     */
    List<GoodsClassifyInfo> listGoodsClassify(String classifyId);

    /**
     * 查询商品接口
     * @param classifyId
     * @return
     */
    List<ClientGoodsInfo> listGetClassGoods(String classifyId);
}
