package com.xzsd.app.clientHome.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.clientHome.dao.ClientHomeDao;
import com.xzsd.app.clientHome.entity.HotGoodsInfo;
import com.xzsd.app.clientHome.entity.SlideshowHomeInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientHomeServices {

    @Resource
    private ClientHomeDao clientHomeDao;

    /**
     * 查询首页轮播图
     * @return
     * @date 2020-04-20
     * @author feng
     */
    public AppResponse listRotationCharHome(){
        List<SlideshowHomeInfo> slideshowHomeInfos = clientHomeDao.listRotationCharHome();
        if (slideshowHomeInfos.size() == 0){
            return AppResponse.notFound("未找到数据");
        }
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("slideshowList",slideshowHomeInfos);
        return AppResponse.success("查询轮播图成功",map);
    }
    /**
     * 查询热门商品位
     * @return
     * @date 2020-04-20
     * @author feng
     */
    public AppResponse listHotGoods(){
        List<HotGoodsInfo> hotGoodsInfos = clientHomeDao.listHotGoods();
        if (hotGoodsInfos.size() == 0){
            return AppResponse.notFound("未找到数据");
        }
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("list",hotGoodsInfos);
        return AppResponse.success("查询轮播图成功",map);
    }
}
