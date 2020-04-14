package com.xzsd.pc.goodsClassify.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.goods.entity.GoodsInfo;
import com.xzsd.pc.goodsClassify.entity.GoodsClassifyInfo;
import com.xzsd.pc.goodsClassify.service.GoodsClassifyServices;
import com.xzsd.pc.user.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@RequestMapping("/goodsClassify")
public class GoodsClassifyController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private GoodsClassifyServices goodsClassifyServices;
    /**
     * 增添商品
     * @param goodsClassifyInfo
     * @return AppResponse
     * @author feng
     */
    @PostMapping("addGoodsClassify")
    public AppResponse addGoodsClassify(GoodsClassifyInfo goodsClassifyInfo) {
        try {
            String userId = SecurityUtils.getCurrentUserId();
            goodsClassifyInfo.setCreateUser(userId);
            goodsClassifyInfo.setUpdateUser(userId);
            AppResponse appResponse = goodsClassifyServices.addGoodsClassify(goodsClassifyInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("商品分类新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 查询商品分类详情
     * @param classifyId
     * @return
     * @Author feng
     * @Date 2020-04-13
     */
    @PostMapping("getGoodsClassify")
    public AppResponse getGoodsClassify(String classifyId){
        try {
            AppResponse appResponse = goodsClassifyServices.getGoodsClassify(classifyId);
            return appResponse;
        } catch (Exception e) {
            logger.error("商品分类查询失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 查询商品分类列表
     * @return
     * @Author feng
     * @Date 2020-04-13
     */
    @PostMapping("listAllGoodsClassify")
    public AppResponse listAllGoodsClassify(){
        try{
            return goodsClassifyServices.listAllGoodsClassify();
        } catch (Exception e){
            logger.error("商品分类列表查询失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     *修改商品分类
     * @return
     * @Author feng
     * @Date 2020-04-14
     */
    @PostMapping("updateGoodsClassify")
    public AppResponse updateGoodsClassify(GoodsClassifyInfo goodsClassifyInfo){
        try{
            goodsClassifyInfo.setUpdateUser(SecurityUtils.getCurrentUserId());
            return goodsClassifyServices.updateGoodsClassify(goodsClassifyInfo);
        }catch (Exception e){
            logger.error("商品分类修改失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     *删除商品分类
     * @return
     * @Author feng
     * @Date 2020-04-14
     */
    @PostMapping("deleteGoodsClassify")
    public AppResponse deleteGoodsClassify(String classifyId){
        try{
            String userId = SecurityUtils.getCurrentUserId();
            return goodsClassifyServices.deleteGoodsClassify(classifyId,userId);
        }catch (Exception e){
            logger.error("商品分类修改失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
