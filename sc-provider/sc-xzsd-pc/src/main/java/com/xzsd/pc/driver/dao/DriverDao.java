package com.xzsd.pc.driver.dao;

import com.xzsd.pc.driver.entity.DriverInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;

import java.util.List;

public interface DriverDao {
    /**
     * 新增司机信息
     * @param driverInfo
     * @return
     */
    int addDriver(DriverInfo driverInfo);

    /**
     * 查询司机信息详情接口
     * @param driverId
     * @return
     */
    DriverInfo getDriver(String driverId);

    /**
     * 分页查询司机接口
     * @param driverInfo
     * @return
     */
    List<DriverInfo> listDrivers(DriverInfo driverInfo);

    /**
     * 修改司机信息接口
     * @param driverInfo
     * @return
     */
    int updateDriver(DriverInfo driverInfo);

    /**
     * 删除司机信息接口
     * @param driverIds
     * @param loginId
     * @return
     */
    int deleteDriver(@Param("driverIds") List<String> driverIds, @Param("loginId") String loginId);

    /**
     * 统计用户账号数量
     * @param driverInfo 用户信息
     * @return 账号数量
     */
    List<DriverInfo> countUserAcct(DriverInfo driverInfo);

}
