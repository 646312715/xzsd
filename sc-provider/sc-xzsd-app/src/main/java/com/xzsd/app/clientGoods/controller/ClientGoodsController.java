package com.xzsd.app.clientGoods.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.clientGoods.entity.CommentInfo;
import com.xzsd.app.clientGoods.service.ClientGoodsServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/clientGoods")
public class ClientGoodsController {

    private static final Logger logger = LoggerFactory.getLogger(ClientGoodsController.class);

    @Resource
    private ClientGoodsServices clientGoodsServices;
    /**
     * 查询商品信息详情
     * @param goodsId
     * @return
     * @date 2020-04-21
     * @author feng
     */
    @PostMapping("getGoods")
    public AppResponse getGoods(String goodsId) {
        try {
            return clientGoodsServices.getGoods(goodsId);
        } catch (Exception e) {
            logger.error("商品查询错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询商品评价列表
     * @param commentInfo
     * @return
     * @date 2020-04-21
     * @author feng
     */
    @PostMapping("listGoodsEvaluates")
    public AppResponse listGoodsEvaluates(CommentInfo commentInfo){
        try{
            return clientGoodsServices.listGoodsEvaluates(commentInfo);
        }catch (Exception e){
            logger.error("评论列表查询失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 查询一级商品分类列
     * @param
     * @return
     * @date 2020-04-21
     * @author feng
     */
    @PostMapping("listOneGoodsClassify")
    public AppResponse listOneGoodsClassify(){
        try{
            return clientGoodsServices.listOneGoodsClassify();
        }catch (Exception e){
            logger.error("评论列表查询失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 查询二级商品分类以及商品
     * @param
     * @return
     * @date 2020-04-21
     * @author feng
     */
    @PostMapping("listGetClassGoods")
    public AppResponse listGetClassGoods(String classifyId){
        try{
            return clientGoodsServices.listGetClassGoods(classifyId);
        }catch (Exception e){
            logger.error("查询二级商品分类以及商品失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
