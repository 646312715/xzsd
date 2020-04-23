package com.xzsd.app.driverHome.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.driverHome.dao.DriverHomeDao;
import com.xzsd.app.driverHome.entity.DriverHomeInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DriverHomeServices {

    @Resource
    private DriverHomeDao driverHomeDao;
    /**
     * 查询司机负责的门店信息
     * @param userId
     * @return
     * @date 2020-04-23
     * @author feng
     */
    public AppResponse listDriverStores(String userId){
        DriverHomeInfo driverHomeInfo = driverHomeDao.checkDriver(userId);
        if (driverHomeInfo == null){
            return AppResponse.notFound("未找到骑手信息");
        }
        List<DriverHomeInfo> driverHomeInfoList = driverHomeDao.listDriverStores(driverHomeInfo);
        if (driverHomeInfoList.size() == 0){
            return AppResponse.notFound("未找到商店数据");
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("list",driverHomeInfoList);
        return AppResponse.success("修改邀请码成功",map);
    }
}
