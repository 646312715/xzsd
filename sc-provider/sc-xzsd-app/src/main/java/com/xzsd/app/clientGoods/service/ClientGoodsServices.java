package com.xzsd.app.clientGoods.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.clientGoods.dao.ClientGoodsDao;
import com.xzsd.app.clientGoods.entity.*;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientGoodsServices {

    @Resource
    private ClientGoodsDao clientGoodsDao;

    /**
     * 查询商品信息详情
     * @param goodsId
     * @return
     * @date 2020-04-21
     * @author feng
     */
    public AppResponse getGoods(String goodsId){
        ClientGoodsInfo clientGoodsInfo = clientGoodsDao.getGoods(goodsId);
        if (clientGoodsInfo == null){
            return AppResponse.notFound("未找到商品数据");
        }
        if(clientGoodsInfo.getGoodsSales() != 0){
            clientGoodsInfo.setGoodsEvaluateScore(String.valueOf((double)clientGoodsInfo.getAllScore()/clientGoodsInfo.getGoodsSales()));
        }else{
            clientGoodsInfo.setGoodsEvaluateScore("0.0");
        }
        return AppResponse.success("查询商品详情成功",clientGoodsInfo);
    }

    /**
     * 查询商品评价列表
     * @param commentInfo
     * @return
     * @date 2020-04-21
     * @author feng
     */
    public AppResponse listGoodsEvaluates(CommentInfo commentInfo){
        //找出评论分页列表和图片列表
        PageHelper.startPage(commentInfo.getPageNum(), commentInfo.getPageSize());
        List<CommentInfo> commentInfos = clientGoodsDao.listGoodsEvaluates(commentInfo);
        System.out.println(commentInfos);
        if (commentInfos.size() == 0) {
            return AppResponse.notFound("查询无评论数据！");
        }
        List<CommentImageInfo> commentImageInfos = clientGoodsDao.listGoodsEvaluatesImage(commentInfo);

        //记录评论列表的下标到map,id当key
        Map<String,Integer> map = new HashMap<String,Integer>();
        for (int index = 0 ; index < commentInfos.size() ; index++){
            map.put(commentInfos.get(index).getEvaluateId(),index);
        }
        //把图片实体类存放到评论实体类中
        for(int index = 0 ; index < commentImageInfos.size() ; index++){
            CommentImageInfo commentImageInfo = commentImageInfos.get(index);
            ImageDO imagePath = new ImageDO();
            imagePath.setImagePath(commentImageInfo.getImagePath());
            commentInfos.get(map.get(commentImageInfo.getEvaluateId())).setImageList(imagePath);
        }
        // 包装Page对象
        PageInfo<CommentInfo> pageData = new PageInfo<CommentInfo>(commentInfos);
        return AppResponse.success("查询成功！", pageData);
    }
    /**
     * 查询一级商品分类列
     * @param
     * @return
     * @date 2020-04-21
     * @author feng
     */
    public AppResponse listOneGoodsClassify(){
        List<GoodsClassifyInfo> goodsClassifyInfos = clientGoodsDao.listGoodsClassify("0");
        if (goodsClassifyInfos.size() == 0){
            return AppResponse.notFound("未找到数据");
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("oneClassifyList",goodsClassifyInfos);
        return AppResponse.success("查询商品分类列表成功",map);
    }
    /**
     * 查询二级商品分类以及商品
     * @param
     * @return
     * @date 2020-04-21
     * @author feng
     */
    public AppResponse listGetClassGoods(String classifyId){
       //查找二级列表
        List<GoodsClassifyInfo> goodsClassifyInfos = clientGoodsDao.listGoodsClassify(classifyId);
        if (goodsClassifyInfos.size() == 0){
            return AppResponse.notFound("未找到二级列表");
        }
        //查找一级列表下所有的商品
        List<ClientGoodsInfo> clientGoodsInfos = clientGoodsDao.listGetClassGoods(classifyId);
        if (clientGoodsInfos.size() == 0){
            return AppResponse.notFound("未找到商品");
        }
        //记录二级商品列表的下标到map,id当key
        Map<String,Integer> map = new HashMap<String,Integer>();
        for (int index = 0 ; index < goodsClassifyInfos.size() ; index++){
            map.put(goodsClassifyInfos.get(index).getClassifyId(),index);
        }
        //把商品放到对应的二级商品列表实体类中
        for (int index = 0 ; index < clientGoodsInfos.size() ; index++){
            ClientGoodsInfo clientGoodsInfo = clientGoodsInfos.get(index);
            goodsClassifyInfos.get(map.get(clientGoodsInfo.getTwoClassifyId())).setGoodsList(clientGoodsInfo);
        }
        //包装对象，返回
        Map<String,Object> mapName = new HashMap<String,Object>();
        mapName.put("twoClassifyList",goodsClassifyInfos);
        return AppResponse.success("查询查询二级商品分类以及商品成功",mapName);
    }
}
