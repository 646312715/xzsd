package com.xzsd.app.driverHome.dao;

import com.xzsd.app.driverHome.entity.DriverHomeInfo;

import java.util.List;

public interface DriverHomeDao {
    /**
     * 查询司机个人信息
     * @param userId
     * @return
     */
    DriverHomeInfo checkDriver(String userId);

    /**
     * 查询司机负责的门店信息接口
     * @param driverHomeInfo
     * @return
     */
    List<DriverHomeInfo> listDriverStores(DriverHomeInfo driverHomeInfo);
}
