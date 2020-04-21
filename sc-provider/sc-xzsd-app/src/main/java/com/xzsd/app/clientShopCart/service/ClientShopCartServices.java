package com.xzsd.app.clientShopCart.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.app.clientShopCart.dao.ClientShopCartDao;
import com.xzsd.app.clientShopCart.entity.ClientShopCartInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class ClientShopCartServices {

    @Resource
    private ClientShopCartDao clientShopCartDao;

    /**
     * 新增购物车
     * @param clientShopCartInfo
     * @return
     * @date 2020-04-21
     * @author feng
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addShoppingCart(ClientShopCartInfo clientShopCartInfo){
        //查看购物车里是否已有该物品
        ClientShopCartInfo clientShopCartInfo1 = clientShopCartDao.getShoppingCart(clientShopCartInfo.getUserId(),clientShopCartInfo.getGoodsId());
        System.out.println(clientShopCartInfo1);
        if (clientShopCartInfo1 == null){
            //没有则增加购物车
            clientShopCartInfo.setVersion("0");
            clientShopCartInfo.setIsDelete(0);
            clientShopCartInfo.setShopCartId(StringUtil.getCommonCode(2));
            clientShopCartInfo.setGoodsPrice(clientShopCartDao.getGoodsPrice(clientShopCartInfo.getGoodsId()));
            int count = clientShopCartDao.addShoppingCart(clientShopCartInfo);
            if(count == 0){
                return AppResponse.bizError("增加购物车失败");
            }
            return AppResponse.success("增加购物车成功");
        }else {
            //有则该商品数量加1
            clientShopCartInfo1.setCartGoodsCount(clientShopCartInfo1.getCartGoodsCount()+clientShopCartInfo.getCartGoodsCount());
            return updateShoppingCart(clientShopCartInfo1);
        }

    }

    /**
     * 查询购物车
     * @param clientShopCartInfo
     * @return
     * @date 2020-04-21
     * @author feng
     */
    public AppResponse listShoppingCarts(ClientShopCartInfo clientShopCartInfo){
        PageHelper.startPage(clientShopCartInfo.getPageNum(), clientShopCartInfo.getPageSize());
        List<ClientShopCartInfo> clientShopCartInfos = clientShopCartDao.listShoppingCarts(clientShopCartInfo.getUserId());
        if (clientShopCartInfos.size() == 0){
            return AppResponse.notFound("未找到数据");
        }
        PageInfo<ClientShopCartInfo> pageData = new PageInfo<ClientShopCartInfo>(clientShopCartInfos);
        return AppResponse.success("查询成功！", pageData);
    }

    /**
     * 修改购物车
     * @param clientShopCartInfo
     * @return
     * @date 2020-04-21
     * @author feng
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateShoppingCart(ClientShopCartInfo clientShopCartInfo){
        int count = clientShopCartDao.updateShoppingCart(clientShopCartInfo);
        if (count == 0){
            return AppResponse.bizError("修改失败");
        }
        return AppResponse.success("修改成功");
    }

    /**
     * 删除购物车
     * @param shopCartId
     * @return
     * @date 2020-04-21
     * @author feng
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteShoppingCart(String shopCartId){
        List<String> shopCartIds = Arrays.asList(shopCartId.split(","));
        if (shopCartIds.size() == 0){
            return AppResponse.notFound("未找到数据");
        }
        int count = clientShopCartDao.deleteShoppingCart(shopCartIds);
        if (count == 0){
            return AppResponse.bizError("删除失败，请重试");
        }
        return AppResponse.success("删除购物车成功");
    }
}
