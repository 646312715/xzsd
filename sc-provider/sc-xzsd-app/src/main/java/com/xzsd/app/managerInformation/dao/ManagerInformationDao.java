package com.xzsd.app.managerInformation.dao;

import com.xzsd.app.managerInformation.entity.ManagerInformationInfo;

import java.util.List;

public interface ManagerInformationDao {

    /**
     * 查询店长门下的司机信息接口
     * @param managerInformationInfo
     * @return
     */
    List<ManagerInformationInfo> listManagerDrivers(ManagerInformationInfo managerInformationInfo);

    /**
     * 查询店铺
     * @param userId
     * @return
     */
    ManagerInformationInfo checkStore(String userId);
}
