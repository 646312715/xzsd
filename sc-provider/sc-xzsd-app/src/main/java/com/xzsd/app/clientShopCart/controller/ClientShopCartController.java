package com.xzsd.app.clientShopCart.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.clientShopCart.entity.ClientShopCartInfo;
import com.xzsd.app.clientShopCart.service.ClientShopCartServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/clientShopCart")
public class ClientShopCartController {

    private static final Logger logger = LoggerFactory.getLogger(ClientShopCartController.class);
    @Resource
    private ClientShopCartServices clientShopCartServices;

    /**
     * 新增购物车
     * @param clientShopCartInfo
     * @return
     * @date 2020-04-21
     * @author feng
     */
    @PostMapping("addShoppingCart")
    public AppResponse addShoppingCart(ClientShopCartInfo clientShopCartInfo){
        try{

            String userId = SecurityUtils.getCurrentUserId();
            clientShopCartInfo.setUserId(userId);
            clientShopCartInfo.setCreateUser(userId);
            clientShopCartInfo.setUpdateUser(userId);
            return clientShopCartServices.addShoppingCart(clientShopCartInfo);
        }catch (Exception e){
            logger.error("购物车新增失败", e);
            System.out.println(e.toString());
            throw e;
        }

    }

    /**
     * 查询购物车
     * @param clientShopCartInfo
     * @return
     * @date 2020-04-21
     * @author feng
     */
    @PostMapping("listShoppingCarts")
    public AppResponse listShoppingCarts(ClientShopCartInfo clientShopCartInfo){
        try{
            String userId = SecurityUtils.getCurrentUserId();
            clientShopCartInfo.setUserId(userId);
            return clientShopCartServices.listShoppingCarts(clientShopCartInfo);
        }catch (Exception e){
            logger.error("购物车查询失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改购物车
     * @param clientShopCartInfo
     * @return
     * @date 2020-04-21
     * @author feng
     */
    @PostMapping("updateShoppingCart")
    public AppResponse updateShoppingCart(ClientShopCartInfo clientShopCartInfo){
        try{
            return clientShopCartServices.updateShoppingCart(clientShopCartInfo);
        }catch (Exception e){
            logger.error("购物车修改失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除购物车
     * @param shopCartId
     * @return
     * @date 2020-04-21
     * @author feng
     */
    @PostMapping("deleteShoppingCart")
    public AppResponse deleteShoppingCart(String shopCartId){
        try{
            return clientShopCartServices.deleteShoppingCart(shopCartId);
        }catch (Exception e){
            logger.error("购物车删除失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
