package com.xzsd.pc.goods.controller;

import com.neusoft.core.restful.AppResponse;

import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.goods.entity.GoodsInfo;
import com.xzsd.pc.goods.service.GoodsServices;
import com.xzsd.pc.user.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    private GoodsServices goodsServices;


    /**
     * 增添商品
     * @param goodsInfo
     * @return AppResponse
     * @author feng
     */
    @PostMapping("addGoods")
    public AppResponse addGoods(GoodsInfo goodsInfo,String imagePath) {
        try {
            String userId = SecurityUtils.getCurrentUserId();
            goodsInfo.setCreateUser(userId);
            goodsInfo.setUpdateUser(userId);
            goodsInfo.setGoodsImagePath(imagePath);
            AppResponse appResponse = goodsServices.addGoods(goodsInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("商品新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改商品信息
     * @param goodsInfo
     * @return AppRessponse
     */
    @PostMapping("updateGoods")
    public AppResponse updateGoods(GoodsInfo goodsInfo,String imagePath){
        try{
            String userId = SecurityUtils.getCurrentUserId();
            goodsInfo.setUpdateUser(userId);
            goodsInfo.setGoodsImagePath(imagePath);

            AppResponse appResponse = goodsServices.updateGoods(goodsInfo);
            return appResponse;
        }catch (Exception e){
            logger.error("商品修改失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 查询商品列表
     * @param goodsInfo
     * @return AppRessponse
     */
    @PostMapping("listGoods")
    public AppResponse listGoods(GoodsInfo goodsInfo){
        try {
            return goodsServices.listGoods(goodsInfo);

        } catch (Exception e) {
            logger.error("查询商品列表异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * demo 查询商品详情
     *
     * @param goodsId
     * @return AppResponse
     * @author feng
     * @Date 2020-03-21
     */
    @RequestMapping(value = "getGoods")
    public AppResponse getGoods(String goodsId) {
        try {
            return goodsServices.getGoods(goodsId);
        } catch (Exception e) {
            logger.error("用户查询错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * demo 删除商品
     *
     * @param goodsId
     * @return AppResponse
     * @author feng
     * @Date 2020-04-11
     */
    @RequestMapping(value = "deleteGoods")
    public AppResponse deleteGoods(String goodsId) {
        try {
            String userId = SecurityUtils.getCurrentUserId();
            return goodsServices.deleteGoods(userId,goodsId);
        } catch (Exception e) {
            logger.error("商品查询错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }
//    public AppResponse showOfRedis(){
//        try{
//
//        } catch (Exception e){
//            logger.error("获取数据失败", e);
//            System.out.println(e.toString());
//            throw e;
//        }
//    }

    /**
     * 修改商品状态
     * @param goodsInfo
     * @return
     */
    @PostMapping("updateGoodsShelfState")
    public AppResponse updateGoodsShelfState(GoodsInfo goodsInfo){
        try{
            goodsInfo.setUpdateUser(SecurityUtils.getCurrentUserId());
            return goodsServices.updateGoodsShelfState(goodsInfo);
        }catch (Exception e){
            logger.error("商品查询错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
