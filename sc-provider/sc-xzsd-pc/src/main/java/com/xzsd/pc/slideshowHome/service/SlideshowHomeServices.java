package com.xzsd.pc.slideshowHome.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.goods.entity.GoodsInfo;
import com.xzsd.pc.slideshowHome.dao.SlideshowHomeDao;
import com.xzsd.pc.slideshowHome.entity.SlideshowHomeInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.neusoft.util.time.TimestampUtils.now;

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
        count = slideshowHomeDao.getSlideshowNum(slideshowHomeInfo.getSlideshowNum());
        if (count != 0){
            return AppResponse.bizError("已存在排序，请重新输入！");
        }
        //判别是否在有效期内
        String strNow = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();
//        Integer year = Integer.parseInt(strNow[0]);
//        Integer month = Integer.parseInt(strNow[1]);
//        Integer day = Integer.parseInt(strNow[2]);
//        String nowDate = year.toString()+month.toString()+day.toString();

        if (slideshowHomeInfo.getStartTime().compareTo(strNow)<0 && slideshowHomeInfo.getEndTime().compareTo(strNow)>0){
            slideshowHomeInfo.setSlideshowStateId("1");
        }else{
            slideshowHomeInfo.setSlideshowStateId("0");
        }
        slideshowHomeInfo.setSlideshowId(StringUtil.getCommonCode(2));
        slideshowHomeInfo.setVersion("0");
        count = slideshowHomeDao.addSlideshowHome(slideshowHomeInfo);
        if (count==0) {
            appResponse = AppResponse.bizError("新增轮播图失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 轮播图列表查询
     * @param slideshowHomeInfo
     * @return
     * @Author feng
     * @Date 2020-04-13
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse listSlideshowHome(SlideshowHomeInfo slideshowHomeInfo){
        PageHelper.startPage(slideshowHomeInfo.getPageNum(), slideshowHomeInfo.getPageSize());
        List<SlideshowHomeInfo> slideshowHomeInfos = slideshowHomeDao.listSlideshowHome(slideshowHomeInfo);
        PageInfo<SlideshowHomeInfo> pageData = new PageInfo<SlideshowHomeInfo>(slideshowHomeInfos);
        return AppResponse.success("查询成功",slideshowHomeInfos);
    }

    /**
     * 商品列表查询
     * @param goodsInfo
     * @return
     * @Author feng
     * @Date 2020-04-13
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse listGoods(GoodsInfo goodsInfo){
        PageHelper.startPage(goodsInfo.getPageNum(), goodsInfo.getPageSize());
        List<GoodsInfo> goodsInfos = slideshowHomeDao.listGoods(goodsInfo);
        PageInfo<GoodsInfo> pageData = new PageInfo<GoodsInfo>(goodsInfos);
        return AppResponse.success("mysql查询成功！",pageData);
    }

    /**
     * 修改状态信息
     * @param slideshowHomeInfo
     * @return
     * @Author feng
     * @Date 2020-04-13
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateSlideshowHomeState(SlideshowHomeInfo slideshowHomeInfo){
        AppResponse appResponse = AppResponse.success("修改状态成功！");
        List<String> listSlideshowId = Arrays.asList(slideshowHomeInfo.getSlideshowId().split(","));
        List<String> listVersion = Arrays.asList(slideshowHomeInfo.getVersion().split(","));
        List<SlideshowHomeInfo> slideshowHomeInfos = new ArrayList<>();
        for (int index=0;index<listSlideshowId.size();index++){
            SlideshowHomeInfo slideshowHomeInfo1 = new SlideshowHomeInfo();
            slideshowHomeInfo1.setSlideshowId(listSlideshowId.get(index));
            slideshowHomeInfo1.setOldVersion(listVersion.get(index));
            slideshowHomeInfo1.setVersion(String.valueOf(Integer.parseInt(listVersion.get(index))+1));
            slideshowHomeInfo1.setUpdateUser(slideshowHomeInfo.getUpdateUser());
            slideshowHomeInfo1.setSlideshowStateId(slideshowHomeInfo.getSlideshowStateId());
            slideshowHomeInfos.add(slideshowHomeInfo1);
        }
        System.out.println(slideshowHomeInfos);
        int count = slideshowHomeDao.updateSlideshowHomeState(slideshowHomeInfos);
        System.out.println(count);
        if(0 == count) {
            appResponse = AppResponse.bizError("数据已更新，请重试！");
        }
        return appResponse;
    }

    /**
     * 删除轮播图
     * @param slideshowIds
     * @param userId
     * @return
     * @Author feng
     * @Date 2020-04-13
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteSlideshowHome(String slideshowIds,String userId){
        AppResponse appResponse = AppResponse.success("删除轮播图成功！");
        List<String> listSlideshowId = Arrays.asList(slideshowIds.split(","));
        int count = slideshowHomeDao.deleteSlideshowHome(listSlideshowId,userId);
        if(0 == count) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }
}
