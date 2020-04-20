package com.xzsd.pc.order.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.goods.entity.GoodsInfo;
import com.xzsd.pc.order.dao.OrderDao;
import com.xzsd.pc.order.entity.OrderDeepenDO;
import com.xzsd.pc.order.entity.OrderDeepenInfo;
import com.xzsd.pc.order.entity.OrderInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderServices {
    @Resource
    private OrderDao orderDao;

    /**
     * 查询订单详情
     * @param orderId
     * @return
     * @Author feng
     * @Date 2020-04-14
     */
    public AppResponse getListOrder(String orderId){
        List<OrderDeepenInfo> orderDeepenList = orderDao.getListOrder(orderId);
        if(orderDeepenList.size() == 0){
            return AppResponse.notFound("未找到是数据");
        }
        OrderDeepenDO orderDeepenDO = new OrderDeepenDO();
        orderDeepenDO.setOrderDeepenList(orderDeepenList);
        return AppResponse.success("查询订单详情成功",orderDeepenDO);
    }

    /**
     * 查询订单列表
     * @param orderInfo
     * @param loginId
     * @return
     * @Author feng
     * @Date 2020-04-14
     */
    public AppResponse listOrders(OrderInfo orderInfo,String loginId){
        PageHelper.startPage(orderInfo.getPageNum(), orderInfo.getPageSize());
        List<OrderInfo> orderInfos = orderDao.listOrders(orderInfo,loginId);
        if(orderInfos.size() == 0){
            return AppResponse.notFound("未找到是数据");
        }
        PageInfo<OrderInfo> pageData = new PageInfo<OrderInfo>(orderInfos);
        return AppResponse.success("查询订单列表成功！",pageData);
    }

    /**
     * 修改订单状态
     * @param orderInfo
     * @return
     * @Author feng
     * @Date 2020-04-14
     */
    public AppResponse updateOrderState(OrderInfo orderInfo){
        AppResponse appResponse = AppResponse.success("修改状态成功！");
        List<String> listOrdersId = Arrays.asList(orderInfo.getOrderId().split(","));
        List<String> listVersion = Arrays.asList(orderInfo.getVersion().split(","));
        List<OrderInfo> orderInfos = new ArrayList<>();
        for (int index=0;index<listOrdersId.size();index++){
            OrderInfo orderInfo1 = new OrderInfo();
            orderInfo1.setOrderId(listOrdersId.get(index));
            orderInfo1.setOldVersion(listVersion.get(index));
            orderInfo1.setVersion(String.valueOf(Integer.parseInt(listVersion.get(index))+1));
            orderInfo1.setUpdateUser(orderInfo.getUpdateUser());
            orderInfo1.setOrderStateId(orderInfo.getOrderStateId());
            orderInfos.add(orderInfo1);
        }
        System.out.println(orderInfos);
        int count = orderDao.updateOrderState(orderInfos);
        System.out.println(count);
        if(0 == count) {
            appResponse = AppResponse.bizError("数据已更新，请重试！");
        }
        return appResponse;
    }

}
