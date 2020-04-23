package com.xzsd.app.clientOrder.dao;

import com.xzsd.app.clientGoods.entity.CommentImageInfo;
import com.xzsd.app.clientGoods.entity.CommentInfo;
import com.xzsd.app.clientGoods.entity.ImageDO;
import com.xzsd.app.clientOrder.entity.ClientOrderGoodsInfo;
import com.xzsd.app.clientOrder.entity.ClientOrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClientOrderDao {
    /**
     * 新增订单商品接口
     * @param clientOrderGoodsInfoList
     * @return
     */
    int addOrderGoods(@Param("clientOrderGoodsInfoList") List<ClientOrderGoodsInfo> clientOrderGoodsInfoList);

    /**
     * 新增订单接口
     * @param clientOrderInfo
     * @return
     */
    int addOrder(ClientOrderInfo clientOrderInfo);
    /**
     * 查询订单列表接口
     * @param orderStateId
     * @return
     */
    List<ClientOrderInfo> listOrder(@Param("orderStateId") String orderStateId);

    /**
     * 查询订单商品列表接口
     * @param orderIds
     * @return
     */
    List<ClientOrderGoodsInfo> listOrderGoods(@Param("orderIds") List<String> orderIds);

    /**
     * 修改订单状态接口
     * @param clientOrderInfo
     * @return
     */
    int updateOrderState(ClientOrderInfo clientOrderInfo);

    /**
     * 查询订单详情接口
     * @param orderId
     * @return
     */
    ClientOrderInfo listOrderDeepen(String orderId);

    /**
     * 查询订单评价商品信息列表接口
     * @param orderId
     * @return
     */
    List<ClientOrderGoodsInfo> listGoodsForEvaluate(String orderId);

    /**
     * 新增订单商品评价接口
     * @param commentInfos
     * @return
     */
    int addGoodsEvaluate(@Param("commentInfos") List<CommentInfo> commentInfos);

    /**
     * 新增订单商品评价图片接口
     * @param imageDOList
     * @return
     */
    int addGoodsEvaluateImage(@Param("imageDOList") List<ImageDO> imageDOList);

    /**
     * 修改评分接口
     * @param commentInfos
     * @return
     */
    int updateScore(@Param("commentInfos") List<CommentInfo> commentInfos);

}
