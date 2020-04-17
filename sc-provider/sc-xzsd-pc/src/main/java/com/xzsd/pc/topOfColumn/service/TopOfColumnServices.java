package com.xzsd.pc.topOfColumn.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.topOfColumn.dao.TopOfColumnDao;
import com.xzsd.pc.topOfColumn.entity.TopOfColumnInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TopOfColumnServices {

    @Resource
    private TopOfColumnDao topOfColumnDao;

    /**
     * 顶部栏接口
     * @return
     * @Author feng
     * @Date 2020-04-16
     */
    public AppResponse getTopOfColumn(String userId){
        TopOfColumnInfo topOfColumnInfo = topOfColumnDao.getTopOfColumn(userId);
        return AppResponse.success("顶部栏查询成功",topOfColumnInfo);
    }
}
