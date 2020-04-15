package com.xzsd.pc.hotGoods.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.goods.controller.GoodsController;
import com.xzsd.pc.hotGoods.entity.GoodsShowNumInfo;
import com.xzsd.pc.hotGoods.entity.HotGoodsInfo;
import com.xzsd.pc.hotGoods.service.HotGoodsServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/hotGoods")
public class HotGoodsController {
    private static final Logger logger = LoggerFactory.getLogger(HotGoodsController.class);

    @Resource
    private HotGoodsServices hotGoodsServices;
    /**
     * 新增热门商品接口
     * @param hotGoodsInfo
     * @return
     * @author feng
     * @date 2020-4-14
     */
    @PostMapping("addHotGoods")
    public AppResponse addHotGoods(HotGoodsInfo hotGoodsInfo){
        try{
            String userId = SecurityUtils.getCurrentUserId();
            hotGoodsInfo.setUpdateUser(userId);
            hotGoodsInfo.setCreateUser(userId);
            return hotGoodsServices.addHotGoods(hotGoodsInfo);
        }catch (Exception e){
            logger.error("热门商品新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询热门商品详情
     * @param hotGoodsId
     * @return
     * @author feng
     * @date 2020-4-14
     */
    @PostMapping("getHotGoods")
    public AppResponse getHotGoods(String hotGoodsId){
        try {
            return hotGoodsServices.getHotGoods(hotGoodsId);
        }catch (Exception e){
            logger.error("热门商品查询失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 分页查询热门商品
     * @param hotGoodsInfo
     * @return
     * @author feng
     * @date 2020-4-15
     */
    @PostMapping("listHotGoods")
    public AppResponse listHotGoods(HotGoodsInfo hotGoodsInfo){
        try{
            return hotGoodsServices.listHotGoods(hotGoodsInfo);
        }catch (Exception e){
            logger.error("热门商品分页查询失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 修改热门商品
     * @param hotGoodsInfo
     * @return
     * @author feng
     * @date 2020-4-15
     */
    @PostMapping("updateHotGoods")
    public AppResponse updateHotGoods(HotGoodsInfo hotGoodsInfo){
        try{
            String userId = SecurityUtils.getCurrentUserId();
            hotGoodsInfo.setUpdateUser(userId);
            return hotGoodsServices.updateHotGoods(hotGoodsInfo);
        }catch (Exception e){
            logger.error("热门商品修改失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 查询热门商品展示数量
     * @return
     * @author feng
     * @date 2020-4-15
     */
    @PostMapping("getHotGoodsShowNum")
    public AppResponse getHotGoodsShowNum(){
        try{
            return hotGoodsServices.getHotGoodsShowNum();
        }catch (Exception e){
            logger.error("热门商品展示数量查询失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改热门商品展示数量
     * @param goodsShowNumInfo
     * @return
     * @author feng
     * @date 2020-4-15
     */
    @PostMapping("updateHotGoodsShowNum")
    public AppResponse updateHotGoodsShowNum(GoodsShowNumInfo goodsShowNumInfo){
        try{
            String userId = SecurityUtils.getCurrentUserId();
            goodsShowNumInfo.setUpdateUser(userId);
            return hotGoodsServices.updateHotGoodsShowNum(goodsShowNumInfo);
        }catch (Exception e){
            logger.error("修改热门商品展示数量失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除热门商品
     * @param hotGoodsId
     * @return
     * @author feng
     * @date 2020-4-15
     */
    @PostMapping("deleteHotGoods")
    public AppResponse deleteHotGoods(String hotGoodsId){
        try{
            String userId = SecurityUtils.getCurrentUserId();
            return hotGoodsServices.deleteHotGoods(hotGoodsId,userId);
        }catch (Exception e){
            logger.error("删除热门商品失败", e);
            System.out.println(e.toString());
            throw e;
        }


    }
}
