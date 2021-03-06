package com.xzsd.pc.goods.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.JsonUtils;
import com.neusoft.util.StringUtil;

import com.xzsd.pc.goods.dao.GoodsDao;
import com.xzsd.pc.goods.entity.GoodsClassifyDO;
import com.xzsd.pc.goods.entity.GoodsInfo;
import com.xzsd.pc.goodsClassify.entity.GoodsClassifyInfo;
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
     *
     * @param goodsInfo
     * @return
     * @Author feng
     * @Date 2020-03-27
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addGoods(GoodsInfo goodsInfo) {
        AppResponse appResponse = AppResponse.success("新增成功！");
        int count = goodsDao.findGoodCount(goodsInfo);
        if (count != 0) {
            return AppResponse.bizError("已存在商品，请重新输入！");
        }
        goodsInfo.setGoodsStateId("3");
        goodsInfo.setGoodsId(StringUtil.getCommonCode(2));
        goodsInfo.setIsDelete(0);
        goodsInfo.setVersion("0");
        count = goodsDao.addGoods(goodsInfo);
        if (count == 0) {
            appResponse = AppResponse.bizError("新增失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 查询商品详情
     *
     * @param goodsId
     * @return AppResponse
     * @author feng
     * @date 2020-3-27
     */
    public AppResponse getGoods(String goodsId) {
        GoodsInfo goodsInfo = goodsDao.getGoods(goodsId);
        if (goodsInfo.getGoodsId() == null) {
            return AppResponse.notFound("未找到商品，请重试");
        }
        return AppResponse.success("查询成功", goodsInfo);
    }

    /**
     * 修改商品数据
     *
     * @param goodsInfo
     * @return AppResponse
     * @author feng
     * @date 2020-3-27
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateGoods(GoodsInfo goodsInfo) {
        AppResponse appResponse = AppResponse.success("修改成功！");
        int count = goodsDao.findGoodCount(goodsInfo);
        if (count != 0) {
            return AppResponse.bizError("已存在商品，请重新输入！");
        }
        count = goodsDao.updateGoods(goodsInfo);
        if (count == 0) {
            appResponse = AppResponse.bizError("数据已更新，请重试");
        }
        return appResponse;
    }

    /**
     * demo 查询商品列表（分页）
     *
     * @param goodsInfo
     * @return
     * @Author feng
     * @Date 2020-03-21
     */
    public AppResponse listGoods(GoodsInfo goodsInfo) {
        PageHelper.startPage(goodsInfo.getPageNum(), goodsInfo.getPageSize());
        List<GoodsInfo> goodsInfoList = goodsDao.listGoods(goodsInfo);
        for (int index=0 ; index < goodsInfoList.size() ; index++){
            if(goodsInfoList.get(index).getGoodsShelfTime() != null){
                goodsInfoList.get(index).setGoodsShelfTime(goodsInfoList.get(index).getGoodsShelfTime().replace(".0",""));
            }
        }
        if (goodsInfoList.size() == 0) {
            return AppResponse.notFound("查询无数据！");
        }
        // 包装Page对象
        PageInfo<GoodsInfo> pageData = new PageInfo<GoodsInfo>(goodsInfoList);
        return AppResponse.success("mysql查询成功！", pageData);
    }

    /**
     * 删除商品
     *
     * @param goodsId,userId
     * @return AppResponse
     * @author feng
     * @date 2020-4-11
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteGoods(String userId, String goodsId) {
        List<String> listCode = Arrays.asList(goodsId.split(","));
        // 检查是否有轮播图和热门商品位
        int count = goodsDao.checkGoods(listCode);
        if ((count & 1) == 1) {
            return AppResponse.bizError("商品含有热门商品,无法删除");
        }
        if ((count & 2) == 2) {
                return AppResponse.bizError("商品含有轮播图,无法删除");
        }
        // 删除商品
        count = goodsDao.deleteGoods(listCode, userId);
        AppResponse appResponse = AppResponse.success("删除成功！");
        if (0 == count) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 修改商品状态
     *
     * @param goodsInfo
     * @return
     * @author feng
     * @date 2020-4-12
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateGoodsShelfState(GoodsInfo goodsInfo) {
        AppResponse appResponse = AppResponse.success("修改状态成功！");
        List<String> listGoodsId = Arrays.asList(goodsInfo.getGoodsId().split(","));
        List<String> listVersion = Arrays.asList(goodsInfo.getVersion().split(","));
        List<GoodsInfo> goodsInfos = new ArrayList<>();
        for (int index = 0; index < listGoodsId.size(); index++) {
            GoodsInfo goodsInfo1 = new GoodsInfo();
            goodsInfo1.setGoodsId(listGoodsId.get(index));
            goodsInfo1.setUpdateUser(goodsInfo.getUpdateUser());
            goodsInfo1.setGoodsStateId(goodsInfo.getGoodsStateId());
            goodsInfo1.setVersion(listVersion.get(index));
            goodsInfos.add(goodsInfo1);
        }
        System.out.println(goodsInfos);
        int count = goodsDao.updateGoodsShelfState(goodsInfos);
        System.out.println(count);
        if (0 == count) {
            appResponse = AppResponse.bizError("数据已更新，请重试！");
        }
        return appResponse;
    }

    /**
     * 查询商品分类下拉框接口
     *
     * @param classifyId
     * @return
     */
    public AppResponse listGoodsClassify(String classifyId) {
        List<GoodsClassifyInfo> goodsClassifyInfos = goodsDao.listGoodsClassify(classifyId);
        if (goodsClassifyInfos.size() == 0) {
            return AppResponse.notFound("没找到数据，请重试");
        }
        GoodsClassifyDO goodsClassifyList = new GoodsClassifyDO();
        goodsClassifyList.setGoodsClassifyList(goodsClassifyInfos);
        return AppResponse.success("查询成功", goodsClassifyList);
    }
}
