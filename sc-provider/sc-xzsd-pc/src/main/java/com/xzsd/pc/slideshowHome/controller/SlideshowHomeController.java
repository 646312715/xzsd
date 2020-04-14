package com.xzsd.pc.slideshowHome.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.sun.jersey.core.impl.provider.entity.XMLRootObjectProvider;
import com.xzsd.pc.goods.entity.GoodsInfo;
import com.xzsd.pc.slideshowHome.entity.SlideshowHomeInfo;
import com.xzsd.pc.slideshowHome.service.SlideshowHomeServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author 64631
 */
@RestController
@RequestMapping("/slideshowHome")
public class SlideshowHomeController {
    private static final Logger logger = LoggerFactory.getLogger(SlideshowHomeController.class);
    @Resource
    private SlideshowHomeServices slideshowHomeServices;
    /**
     * 新增轮播图
     * @param slideshowHomeInfo
     * @return AppResponse
     * @author feng
     * @date 2020-04-13
     */
    @PostMapping("addSlideshowHome")
    public AppResponse addSlideshowHome(SlideshowHomeInfo slideshowHomeInfo, String imagePath) {
        try {
            String userId = SecurityUtils.getCurrentUserId();
            slideshowHomeInfo.setCreateUser(userId);
            slideshowHomeInfo.setUpdateUser(userId);
            slideshowHomeInfo.setSlideshowPath(imagePath);
            AppResponse appResponse = slideshowHomeServices.addSlideshowHome(slideshowHomeInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("轮播图新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 轮播图列表
     * @param slideshowHomeInfo
     * @return AppResponse
     * @author feng
     * @date 2020-04-13
     */
    @PostMapping("listSlideshowHome")
    public AppResponse listSlideshowHome(SlideshowHomeInfo slideshowHomeInfo){
        try{
            AppResponse appResponse = slideshowHomeServices.listSlideshowHome(slideshowHomeInfo);
            return appResponse;
        }catch (Exception e){
            logger.error("轮播图列表查询失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 商品列表
     * @param goodsInfo
     * @return AppResponse
     * @author feng
     * @date 2020-04-13
     */
    @PostMapping("listGoods")
    public AppResponse listGoods(GoodsInfo goodsInfo){
        try{
            AppResponse appResponse = slideshowHomeServices.listGoods(goodsInfo);
            return appResponse;
        }catch (Exception e){
            logger.error("商品列表查询失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 修改轮播图状态
     * @param slideshowHomeInfo
     * @return AppResponse
     * @author feng
     * @date 2020-04-13
     */
    @PostMapping("updateSlideshowHomeState")
    public AppResponse updateSlideshowHomeState(SlideshowHomeInfo slideshowHomeInfo){
        try{
            slideshowHomeInfo.setUpdateUser(SecurityUtils.getCurrentUserId());
            return slideshowHomeServices.updateSlideshowHomeState(slideshowHomeInfo);
        }catch (Exception e){
            logger.error("修改轮播图状态失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 删除轮播图
     * @param slideshowIds
     * @return AppResponse
     * @author feng
     * @date 2020-04-13
     */
    @PostMapping("deleteSlideshowHome")
    public AppResponse deleteSlideshowHome(String slideshowIds){
        try {
            String userId = SecurityUtils.getCurrentUserId();
            slideshowHomeServices.deleteSlideshowHome(slideshowIds, userId);
            return AppResponse.success("删除轮播图成功");
        }catch(Exception e){
            logger.error("删除轮播图失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
