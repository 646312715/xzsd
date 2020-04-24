package com.xzsd.pc.hotGoods.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.hotGoods.dao.HotGoodsDao;
import com.xzsd.pc.hotGoods.entity.GoodsShowNumInfo;
import com.xzsd.pc.hotGoods.entity.HotGoodsInfo;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class HotGoodsServices {

    @Resource
    private HotGoodsDao hotGoodsDao;

    /**
     * 新增热门商品接口
     * @param hotGoodsInfo
     * @return
     * @author feng
     * @date 2020-4-14
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addHotGoods(HotGoodsInfo hotGoodsInfo){
        int count = hotGoodsDao.getGoodsCount(hotGoodsInfo);
        if ((count & 1) == 1){
            return AppResponse.bizError("已存在排序，请重试");
        }
        if ((count & 2) == 2) {
            return AppResponse.bizError("已存在商品，请重试");
        }
        hotGoodsInfo.setIsDelete(0);
        hotGoodsInfo.setVersion("0");
        hotGoodsInfo.setHotGoodsId(StringUtil.getCommonCode(2));
        AppResponse appResponse = AppResponse.success("新增热门位成功");
        count = hotGoodsDao.addHotGoods(hotGoodsInfo);
        if (count == 0){
            return AppResponse.bizError("新增失败");
        }
        return appResponse;
    }
    /**
     * 查询热门商品详情
     * @param hotGoodsId
     * @return
     * @author feng
     * @date 2020-4-14
     */
    public AppResponse getHotGoods(String hotGoodsId){
        HotGoodsInfo hotGoodsInfo = hotGoodsDao.getHotGoods(hotGoodsId);
        if(hotGoodsInfo == null){
            return AppResponse.notFound("未找到数据，请重试");
        }
        return AppResponse.success("查询详情成功",hotGoodsInfo);
    }

    /**
     * 分页查询热门商品
     * @param hotGoodsInfo
     * @return
     * @author feng
     * @date 2020-4-15
     */
    public AppResponse listHotGoods(HotGoodsInfo hotGoodsInfo){
        PageHelper.startPage(hotGoodsInfo.getPageNum(), hotGoodsInfo.getPageSize());
        List<HotGoodsInfo> slideshowHomeInfos = hotGoodsDao.listHotGoods(hotGoodsInfo);
        if(slideshowHomeInfos.size() == 0){
            return AppResponse.notFound("未找到数据，请重试");
        }
        PageInfo<HotGoodsInfo> pageData = new PageInfo<HotGoodsInfo>(slideshowHomeInfos);
        return AppResponse.success("查询成功",pageData);
    }

    /**
     * 修改热门商品
     * @param hotGoodsInfo
     * @return
     * @author feng
     * @date 2020-4-15
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateHotGoods(HotGoodsInfo hotGoodsInfo){
        AppResponse appResponse=AppResponse.success("修改成功！");
        int count = hotGoodsDao.getGoodsCount(hotGoodsInfo);
        if ((count & 1) == 1){
            return AppResponse.bizError("已存在排序，修改失败");
        }
        if ((count & 2) == 2) {
            return AppResponse.bizError("已存在商品，修改失败");
        }
        count = hotGoodsDao.updateHotGoods(hotGoodsInfo);
        if (count == 0){
            appResponse=AppResponse.bizError("服务器异常，修改失败");
        }
        return appResponse;
    }
    /**
     * 查询热门商品展示数量
     * @return
     * @author feng
     * @date 2020-4-15
     */
    public AppResponse getHotGoodsShowNum(){
        GoodsShowNumInfo goodsShowNumInfo = hotGoodsDao.getHotGoodsShowNum();
        if (goodsShowNumInfo.getHotGoodsShowNum() == 0){
            return AppResponse.notFound("展示数量为0或者未找到展示数量");
        }
        return AppResponse.success("查询成功",goodsShowNumInfo);
    }

    /**
     * 修改热门商品展示数量
     * @param goodsShowNumInfo
     * @return
     * @author feng
     * @date 2020-4-15
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateHotGoodsShowNum(GoodsShowNumInfo goodsShowNumInfo){
        AppResponse appResponse=AppResponse.success("修改成功！");
        int count = hotGoodsDao.updateHotGoodsShowNum(goodsShowNumInfo);
        if (count == 0){
            appResponse=AppResponse.bizError("服务器异常，修改失败");
        }
        return appResponse;
    }

    /**
     * 删除热门商品
     * @param hotGoodsId
     * @param loginId
     * @return
     * @author feng
     * @date 2020-4-15
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteHotGoods(String hotGoodsId,String loginId){
        List<String> listCode = Arrays.asList(hotGoodsId.split(","));
        int count = hotGoodsDao.deleteHotGoods(listCode,loginId);
        AppResponse appResponse = AppResponse.success("删除成功！");
        if(0 == count) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }
}
