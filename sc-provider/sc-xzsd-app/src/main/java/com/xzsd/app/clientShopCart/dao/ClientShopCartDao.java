package com.xzsd.app.clientShopCart.dao;

import com.xzsd.app.clientShopCart.entity.ClientShopCartInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClientShopCartDao {
    /**
     * 新增购物车接口
     * @return
     */
    int addShoppingCart(ClientShopCartInfo clientShopCartInfo);

    /**
     * 查询购物车接口
     * @return
     */
    List<ClientShopCartInfo> listShoppingCarts(String userId);

    /**
     * 修改购物车接口
     * @param clientShopCartInfo
     * @return
     */
    int updateShoppingCart(ClientShopCartInfo clientShopCartInfo);

    /**'
     * 删除购物车接口
     * @param shopCartIds
     * @return
     */
    int deleteShoppingCart(@Param("shopCartIds") List<String> shopCartIds);

    /**
     * 获取商品价格
     * @param goodsId
     * @return
     */
    String getGoodsPrice(String goodsId);

    /**
     * 购物车详情
     * @param userId
     * @param goodsId
     * @return
     */
    ClientShopCartInfo getShoppingCart(@Param("userId") String userId,@Param("goodsId") String goodsId);
}
