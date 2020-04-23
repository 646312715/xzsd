package com.xzsd.app.clientOrder.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.StringUtil;
import com.xzsd.app.clientGoods.entity.CommentInfo;
import com.xzsd.app.clientGoods.entity.ImageDO;
import com.xzsd.app.clientOrder.dao.ClientOrderDao;
import com.xzsd.app.clientOrder.entity.ClientOrderGoodsInfo;
import com.xzsd.app.clientOrder.entity.ClientOrderInfo;
import com.xzsd.app.clientOrder.entity.EvaluateInfo;
import com.xzsd.app.clientShopCart.dao.ClientShopCartDao;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ClientOrderServices {

    @Resource
    private ClientOrderDao clientOrderDao;

    @Resource
    private ClientShopCartDao clientShopCartDao;
    /**
     * 新增订单
     * @param clientOrderGoodsInfo
     * @param storeId
     * @param shopCartId
     * @return
     * @author feng
     * @date 2020-4-22
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addOrder(ClientOrderGoodsInfo clientOrderGoodsInfo,String storeId,String shopCartId){

        //初始化订单商品
        List<String> goodsIdList = Arrays.asList(clientOrderGoodsInfo.getGoodsId().split(","));
        List<String> goodsPriceList = Arrays.asList(clientOrderGoodsInfo.getGoodsPrice().split(","));
        List<String> clientGoodsNumList = Arrays.asList(clientOrderGoodsInfo.getClientGoodsNum().split(","));
        List<String> shopCartIdList = Arrays.asList(shopCartId.split(","));
        List<ClientOrderGoodsInfo> clientOrderGoodsInfoList = new ArrayList<ClientOrderGoodsInfo>();
        int count = clientShopCartDao.deleteShoppingCart(shopCartIdList);
        if(count == 0){
            return AppResponse.bizError("新增订单失败，删除购物车失败");
        }
        String userId = SecurityUtils.getCurrentUserId();
        String orderId = StringUtil.getCommonCode(2);
        double allPrice = 0;
        int orderAllGoodsNum = 0;
        for(int index = 0 ; index<goodsIdList.size() ; index++){
            String orderGoodsId = StringUtil.getCommonCode(2);
            ClientOrderGoodsInfo clientOrderGoodsInfo1 = new ClientOrderGoodsInfo();
            clientOrderGoodsInfo1.setOrderGoodsId(orderGoodsId);
            clientOrderGoodsInfo1.setOrderId(orderId);
            clientOrderGoodsInfo1.setGoodsId(goodsIdList.get(index));
            clientOrderGoodsInfo1.setGoodsPrice(goodsPriceList.get(index));
            clientOrderGoodsInfo1.setClientGoodsNum(clientGoodsNumList.get(index));
            clientOrderGoodsInfo1.setCreateUser(userId);
            clientOrderGoodsInfo1.setUpdateUser(userId);
            clientOrderGoodsInfo1.setIsDelete(0);
            clientOrderGoodsInfo1.setVersion("0");
            clientOrderGoodsInfoList.add(clientOrderGoodsInfo1);
            double goodsPrice= Double.parseDouble(clientOrderGoodsInfo1.getGoodsPrice())*Integer.parseInt(clientOrderGoodsInfo1.getClientGoodsNum());
            clientOrderGoodsInfo1.setTheGoodsAllPrice(String.valueOf(goodsPrice));
            allPrice += goodsPrice;
            orderAllGoodsNum +=Integer.parseInt(clientOrderGoodsInfo1.getClientGoodsNum());
        }
        //新增商品订单
        count = clientOrderDao.addOrderGoods(clientOrderGoodsInfoList);
        if(count == 0){
            return AppResponse.bizError("新增订单商品失败");
        }
        //初始化订单
        ClientOrderInfo clientOrderInfo = new ClientOrderInfo();
        clientOrderInfo.setStoreId(storeId);
        clientOrderInfo.setOrderId(orderId);
        clientOrderInfo.setOrderAllCost(String.valueOf(allPrice));
        clientOrderInfo.setUserId(userId);
        clientOrderInfo.setOrderStateId("0");
        clientOrderInfo.setCreateUser(userId);
        clientOrderInfo.setUpdateUser(userId);
        clientOrderInfo.setIsDelete(0);
        clientOrderInfo.setOrderAllGoodsCount(String.valueOf(orderAllGoodsNum));
        clientOrderInfo.setVersion("0");
        //新增订单
        count = clientOrderDao.addOrder(clientOrderInfo);
        if(count == 0){
            return AppResponse.bizError("新增订单失败");
        }
        return AppResponse.success("新增成功");
    }

    /**
     * 查询订单列表
     * @param clientOrderInfo
     * @return
     * @author feng
     * @date 2020-4-22
     */
    public AppResponse listOrder(ClientOrderInfo clientOrderInfo){
        PageHelper.startPage(clientOrderInfo.getPageNum(), clientOrderInfo.getPageSize());
        String orderStateId = clientOrderInfo.getOrderStateId();
        String userId = clientOrderInfo.getUserId();
        //查找订单
        List<ClientOrderInfo> clientOrderInfoList = clientOrderDao.listOrder(orderStateId,userId);
        if (clientOrderInfoList.size() == 0){
            return AppResponse.notFound("未找到订单");
        }
        List<String> orderIds = new ArrayList<String>();
        Map<String,Integer> map = new HashMap<String,Integer>();
        for (int index = 0 ; index < clientOrderInfoList.size() ; index++){
            ClientOrderInfo clientOrderInfo1 = clientOrderInfoList.get(index);
            map.put(clientOrderInfo1.getOrderId(),index);
            orderIds.add(clientOrderInfo1.getOrderId());
        }
        //查找订单商品
        List<ClientOrderGoodsInfo> clientOrderGoodsInfoList = clientOrderDao.listOrderGoods(orderIds);
        if (clientOrderGoodsInfoList.size() == 0){
            return AppResponse.notFound("未找到订单商品");
        }
        //订单商品存入订单
        for (int index = 0 ; index <clientOrderGoodsInfoList.size() ; index++){
            ClientOrderGoodsInfo clientOrderGoodsInfo = clientOrderGoodsInfoList.get(index);
            clientOrderInfoList.get(map.get(clientOrderGoodsInfo.getOrderId())).setClientOrderGoodsInfoList(clientOrderGoodsInfo);
        }
        PageInfo<ClientOrderInfo> pageData = new PageInfo<ClientOrderInfo>(clientOrderInfoList);
        return AppResponse.success("订单查询成功！", pageData);
    }

    /**
     * 修改订单状态
     *
     * @param clientOrderInfo
     * @return
     * @author feng
     * @date 2020-4-22
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateOrderState(ClientOrderInfo clientOrderInfo) {
        AppResponse appResponse = AppResponse.success("修改状态成功！");
        int count = clientOrderDao.updateOrderState(clientOrderInfo);
        if (0 == count) {
            appResponse = AppResponse.bizError("数据已更新，请重试！");
        }
        return appResponse;
    }
    /**
     * 查询订单详情
     *
     * @param orderId
     * @return
     * @author feng
     * @date 2020-4-22
     */
    public AppResponse listOrderDeepen(String orderId){
        //查找订单
        ClientOrderInfo clientOrderInfo = clientOrderDao.listOrderDeepen(orderId);
        if (clientOrderInfo == null){
            return AppResponse.notFound("未找到数据");
        }
        //查找订单商品
        List<String> orderGoodsList = new ArrayList<String>();
        orderGoodsList.add(orderId);
        List<ClientOrderGoodsInfo> clientOrderGoodsInfoList = clientOrderDao.listOrderGoods(orderGoodsList);
        if (clientOrderGoodsInfoList.size() == 0){
            return AppResponse.notFound("未找到数据");
        }
        clientOrderInfo.setClientOrderGoodsInfoList(clientOrderGoodsInfoList);
        return AppResponse.success("查询订单详情成功",clientOrderInfo);
    }

    /**
     * 查询订单评价商品信息列表
     * @param orderId
     * @return
     * @author feng
     * @date 2020-4-22
     */
    public AppResponse listGoodsForEvaluate(String orderId){
        List<ClientOrderGoodsInfo> goodsInfoList = clientOrderDao.listGoodsForEvaluate(orderId);
        if (goodsInfoList.size() == 0){
            return AppResponse.notFound("未找到数据");
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("goodsList",goodsInfoList);
        return AppResponse.success("查询订单评价商品信息列表成功",map);
    }

    /**
     * 新增订单商品评价
     * @param evaluateInfo
     * @return
     * @author feng
     * @date 2020-4-22
     */
    public AppResponse addGoodsEvaluate(EvaluateInfo evaluateInfo){
        //评论和图片分离
        List<CommentInfo> commentInfos = evaluateInfo.getEvaluateList();
        String userId = SecurityUtils.getCurrentUserId();
        List<ImageDO> imageDOList = new ArrayList<ImageDO>();
        for(int index = 0 ; index < commentInfos.size() ; index++){
            CommentInfo commentInfo = commentInfos.get(index);
            String commentId = StringUtil.getCommonCode(2);
            for (int temp = 0 ; temp < commentInfo.getImageList().size() ; temp++){
                ImageDO imageDO = new ImageDO();
                imageDO.setImageId(StringUtil.getCommonCode(2));
                imageDO.setEvaluateId(commentId);
                imageDO.setImagePath(commentInfo.getImageList().get(temp).getImagePath());
                imageDO.setImageNum(commentInfo.getImageList().get(temp).getImageNum());
                imageDO.setCreateUser(userId);
                imageDO.setUpdateUser(userId);
                imageDO.setVersion("0");
                imageDO.setIsDelete(0);
                imageDOList.add(imageDO);
            }

            commentInfos.get(index).setUserId(userId);
            commentInfos.get(index).setCreateUser(userId);
            commentInfos.get(index).setUpdateUser(userId);
            commentInfos.get(index).setUserId(userId);
            commentInfos.get(index).setVersion("0");
            commentInfos.get(index).setIsDelete(0);
            commentInfos.get(index).setEvaluateId(commentId);
        }
        //新增评价
        int count = clientOrderDao.addGoodsEvaluate(commentInfos);
        if(count == 0){
            return AppResponse.bizError("新增评价失败");
        }
        //新增评价图片
        count = clientOrderDao.addGoodsEvaluateImage(imageDOList);
        if(count == 0){
            return AppResponse.bizError("新增评价图片失败");
        }
        ClientOrderInfo clientOrderInfo = new ClientOrderInfo();
        clientOrderInfo.setOrderId(evaluateInfo.getOrderId());
        clientOrderInfo.setOrderStateId("5");
        //修改状态
        count = clientOrderDao.updateOrderState(clientOrderInfo);
        if(count == 0){
            return AppResponse.bizError("修改状态失败");
        }
        //修改分数
        count = clientOrderDao.updateScore(commentInfos);
        if(count == 0){
            return AppResponse.bizError("修改评分失败");
        }
        return AppResponse.success("新增评价成功");
    }
}
