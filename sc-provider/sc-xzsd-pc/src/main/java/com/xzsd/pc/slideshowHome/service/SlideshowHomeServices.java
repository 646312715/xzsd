package com.xzsd.pc.slideshowHome.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.slideshowHome.dao.SlideshowHomeDao;
import com.xzsd.pc.slideshowHome.entity.SlideshowHomeInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class SlideshowHomeServices {

    @Resource
    private SlideshowHomeDao slideshowHomeDao;


    /**
     * 新增轮播图
     * @param slideshowHomeInfo
     * @return
     * @Author feng
     * @Date 2020-04-12
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addSlideshowHome (SlideshowHomeInfo slideshowHomeInfo){
        AppResponse appResponse=AppResponse.success("新增成功！");
        int count = slideshowHomeDao.getGoodsCount(slideshowHomeInfo.getGoodsId());
        if (count != 0){
            return AppResponse.bizError("已存在商品，请重新输入！");
        }

        count = slideshowHomeDao.addSlideshowHome(slideshowHomeInfo);
        if (count==0) {
            appResponse = AppResponse.bizError("新增轮播图失败，请重试！");
        }
        return appResponse;
    }
}
