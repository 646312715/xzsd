package com.xzsd.pc.goods.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.JsonUtils;
import com.neusoft.util.StringUtil;

import com.xzsd.pc.goods.dao.GoodsDao;
import com.xzsd.pc.goods.entity.GoodsInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class GoodsServices {
    @Resource
    private GoodsDao goodsDao;

    /**
     * 新增商品
     * @param goodsInfo
     * @return
     * @Author feng
     * @Date 2020-03-21
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addGoods (GoodsInfo goodsInfo){
        AppResponse appResponse=AppResponse.success("新增成功！");
        int count = goodsDao.findGoodCount(goodsInfo);
        if (count != 0){
            return AppResponse.bizError("已存在商品，请重新输入！");
        }
        goodsInfo.setGoodsStateId(3);
        goodsInfo.setGoodsId(StringUtil.getCommonCode(2));
        goodsInfo.setIsDelete(0);
        goodsInfo.setVersion("0");
        count = goodsDao.addGoods(goodsInfo);
        if (count==0) {
            appResponse = AppResponse.bizError("新增失败，请重试！");
        }
        return appResponse;
    }


    /**
     * 查询商品详情
     * @param goodsId
     * @return AppResponse
     * @author feng
     * @date 2020-3-27
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse getGoods(String goodsId){
        GoodsInfo goodsInfo = goodsDao.getGoods(goodsId);

        return AppResponse.success("查询成功", goodsInfo);
    }

    /**
     * 修改商品数据
     * @param goodsInfo
     * @return AppResponse
     * @author feng
     * @date 2020-3-27
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateGoods(GoodsInfo goodsInfo){
        AppResponse appResponse=AppResponse.success("修改成功！");
        int count = goodsDao.findGoodCount(goodsInfo);
        if (count != 0){
            return AppResponse.bizError("已存在商品，请重新输入！");
        }
        goodsInfo.setOldVersion(goodsInfo.getVersion());
        goodsInfo.setVersion(String.valueOf(Integer.parseInt(goodsInfo.getVersion())+1));
        count = goodsDao.updateGoods(goodsInfo);
        if (count == 0){
            appResponse=AppResponse.bizError("服务器异常，修改失败");
        }
        return appResponse;
    }

    /**
     * demo 查询商品列表（分页）
     * @param goodsInfo
     * @return
     * @Author feng
     * @Date 2020-03-21
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse listGoods(GoodsInfo goodsInfo) {

            PageHelper.startPage(goodsInfo.getPageNum(), goodsInfo.getPageSize());
            List<GoodsInfo> goodsInfoList = goodsDao.listGoods(goodsInfo);
            // 包装Page对象
            PageInfo<GoodsInfo> pageData = new PageInfo<GoodsInfo>(goodsInfoList);
            return AppResponse.success("mysql查询成功！",pageData);
    }

    /**
     * 删除商品
     * @param goodsId,userId
     * @return AppResponse
     * @author feng
     * @date 2020-4-11
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteGoods(String userId,String goodsId){
        GoodsInfo goodsInfo = goodsDao.getGoods(goodsId);
        List<String> listCode = Arrays.asList(goodsId.split(","));
        // 删除商品
        int count = goodsDao.deleteGoods(listCode,userId);
        AppResponse appResponse = AppResponse.success("删除成功！");
        if(0 == count) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 修改商品状态
     * @param goodsInfo
     * @return
     * @author feng
     * @date 2020-4-12
     */
    public AppResponse updateGoodsShelfState(GoodsInfo goodsInfo){
        AppResponse appResponse = AppResponse.success("修改状态成功！");
        List<String> listGoodsId = Arrays.asList(goodsInfo.getGoodsId().split(","));
        List<String> listVersion = Arrays.asList(goodsInfo.getVersion().split(","));
        List<GoodsInfo> goodsInfos = new ArrayList<>();
        for (int index=0;index<listGoodsId.size();index++){
            GoodsInfo goodsInfo1 = new GoodsInfo();
            goodsInfo1.setGoodsId(listGoodsId.get(index));
            goodsInfo1.setOldVersion(listVersion.get(index));
            goodsInfo1.setVersion(String.valueOf(Integer.parseInt(listVersion.get(index))+1));
            goodsInfo1.setUpdateUser(goodsInfo.getUpdateUser());
            goodsInfo1.setGoodsStateId(goodsInfo.getGoodsStateId());
            goodsInfos.add(goodsInfo1);
        }
        System.out.println(goodsInfos);
        int count = goodsDao.updateGoodsShelfState(goodsInfos);
        System.out.println(count);
        if(0 == count) {
            appResponse = AppResponse.bizError("数据已更新，请重试！");
        }
        return appResponse;
    }
}
