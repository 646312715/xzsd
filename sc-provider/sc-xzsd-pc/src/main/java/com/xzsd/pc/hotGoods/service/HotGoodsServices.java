package com.xzsd.pc.hotGoods.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.hotGoods.dao.HotGoodsDao;
import com.xzsd.pc.hotGoods.entity.GoodsShowNumInfo;
import com.xzsd.pc.hotGoods.entity.HotGoodsInfo;
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
        List<HotGoodsInfo> hotGoodsInfos = hotGoodsDao.getGoodsCount(hotGoodsInfo);
        for (int index=0 ; index<hotGoodsInfos.size() ; index++){
            if(hotGoodsInfos.get(index).getGoodsId().equals(hotGoodsInfo.getGoodsId())){
                return AppResponse.bizError("已存在商品，请重试");
            }
            if(hotGoodsInfos.get(index).getHotGoodsNum().equals(hotGoodsInfo.getHotGoodsNum())){
                return AppResponse.bizError("已存在排序，请重试");
            }
        }
        hotGoodsInfo.setIsDelete(0);
        hotGoodsInfo.setVersion("0");
        hotGoodsInfo.setHotGoodsId(StringUtil.getCommonCode(2));
        AppResponse appResponse = AppResponse.success("新增热门位成功");
        int count = hotGoodsDao.addHotGoods(hotGoodsInfo);
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
        PageInfo<HotGoodsInfo> pageData = new PageInfo<HotGoodsInfo>(slideshowHomeInfos);
        return AppResponse.success("查询成功",slideshowHomeInfos);
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
        List<HotGoodsInfo> hotGoodsInfos = hotGoodsDao.getGoodsCount(hotGoodsInfo);
        for (int index=0 ; index<hotGoodsInfos.size() ; index++){
            if(hotGoodsInfos.get(index).getGoodsId().equals(hotGoodsInfo.getGoodsId())){
                return AppResponse.bizError("已存在商品，请重试");
            }
            if(hotGoodsInfos.get(index).getHotGoodsNum().equals(hotGoodsInfo.getHotGoodsNum())){
                return AppResponse.bizError("已存在排序，请重试");
            }
        }
        hotGoodsInfo.setOldVersion(hotGoodsInfo.getVersion());
        hotGoodsInfo.setVersion(String.valueOf(Integer.parseInt(hotGoodsInfo.getVersion())+1));
        int count = hotGoodsDao.updateHotGoods(hotGoodsInfo);
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
        goodsShowNumInfo.setOldVersion(goodsShowNumInfo.getVersion());
        goodsShowNumInfo.setVersion(String.valueOf(Integer.parseInt(goodsShowNumInfo.getVersion())+1));
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
