package com.xzsd.pc.goodsClassify.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.goodsClassify.dao.GoodsClassifyDao;
import com.xzsd.pc.goodsClassify.entity.GoodsClassifyDO;
import com.xzsd.pc.goodsClassify.entity.GoodsClassifyInfo;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GoodsClassifyServices {

    @Resource
    private GoodsClassifyDao goodsClassifyDao;

    /**
     * 新增商品
     *
     * @param goodsClassifyInfo
     * @return
     * @Author feng
     * @Date 2020-04-13
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addGoodsClassify(GoodsClassifyInfo goodsClassifyInfo) {
        AppResponse appResponse = AppResponse.success("新增成功！");
        goodsClassifyInfo.setClassifyId(StringUtil.getCommonCode(2));
        goodsClassifyInfo.setIsDelete(0);
        goodsClassifyInfo.setVersion("0");
        int count = goodsClassifyDao.addGoodsClassify(goodsClassifyInfo);
        if (count == 0) {
            appResponse = AppResponse.bizError("新增失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 查询商品分类详情
     *
     * @param classifyId
     * @return
     * @Author feng
     * @Date 2020-04-13
     */
    public AppResponse getGoodsClassify(String classifyId) {
        GoodsClassifyInfo goodsClassifyInfo = goodsClassifyDao.getGoodsClassify(classifyId);
        System.out.println(goodsClassifyInfo);
        if (goodsClassifyInfo.getClassifyId() == null) {
            return AppResponse.notFound("未找到数据");
        }
        return AppResponse.success("查询成功", goodsClassifyInfo);
    }

    /**
     * 查询商品分类列表
     *
     * @return
     * @Author feng
     * @Date 2020-04-13
     */
    public AppResponse listAllGoodsClassify() {
        List<GoodsClassifyInfo> goodsClassifyInfos = goodsClassifyDao.listAllGoodsClassify();
        if (goodsClassifyInfos.size() == 0) {
            return AppResponse.notFound("未找到数据");
        }
        List<GoodsClassifyInfo> oneClassifyList = new ArrayList<GoodsClassifyInfo>();
        Map<String, Integer> map = new HashedMap();
        System.out.println(goodsClassifyInfos);
        int count = 0;
        for (int index = 0; index < goodsClassifyInfos.size(); index++) {
            String classifyParent = goodsClassifyInfos.get(index).getClassifyParent();
            System.out.println(classifyParent);
            if (classifyParent.equals("0")) {
                map.put(goodsClassifyInfos.get(index).getClassifyId(), count++);
                oneClassifyList.add(goodsClassifyInfos.get(index));
            }
        }
        for (int index = 0; index < goodsClassifyInfos.size(); index++) {
            String classifyParent = goodsClassifyInfos.get(index).getClassifyParent();
            if (!classifyParent.equals("0")) {
                oneClassifyList.get(map.get(classifyParent)).setTwoClassifyList(goodsClassifyInfos.get(index));
            }
        }
        GoodsClassifyDO goodsClassifyDO = new GoodsClassifyDO();
        goodsClassifyDO.setOneClassifyList(oneClassifyList);
        return AppResponse.success("查询分页成功", goodsClassifyDO);
    }

    /**
     * 修改商品分类
     *
     * @return
     * @Author feng
     * @Date 2020-04-14
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateGoodsClassify(GoodsClassifyInfo goodsClassifyInfo) {
        AppResponse appResponse = AppResponse.success("修改状态成功！");
        int count = goodsClassifyDao.updateGoodsClassify(goodsClassifyInfo);
        System.out.println(count);
        if (0 == count) {
            appResponse = AppResponse.bizError("数据已更新，请重试！");
        }
        return appResponse;
    }

    /**
     * 删除商品分类
     *
     * @return
     * @Author feng
     * @Date 2020-04-14
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteGoodsClassify(String classifyId, String userId) {
        int count = goodsClassifyDao.deleteGoodsClassify(classifyId, userId);
        AppResponse appResponse = AppResponse.success("删除成功！");
        if (0 == count) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }
}
