package com.xzsd.pc.driver.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.driver.entity.DriverInfo;
import com.xzsd.pc.driver.service.DriverServices;
import com.xzsd.pc.user.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/driver")
public class DriverController {

    private static final Logger logger = LoggerFactory.getLogger(DriverController.class);
    @Resource
    private DriverServices driverServices;
    /**
     * 新增用户
     * @param driverInfo
     * @return
     * @Author feng
     * @Date 2020-04-16
     */
    @PostMapping("addDriver")
    public AppResponse addDriver(DriverInfo driverInfo,String imagePath){
        try{
            String userId = SecurityUtils.getCurrentUserId();
            driverInfo.setUserImage(imagePath);
            driverInfo.setCreateUser(userId);
            driverInfo.setUpdateUser(userId);
            AppResponse appResponse = driverServices.addDriver(driverInfo);
            return appResponse;
        }catch (Exception e){
            logger.error("司机新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 查询司机信息详情
     * @param driverId
     * @return
     * @Author feng
     * @Date 2020-04-16
     */
    @PostMapping("getDriver")
    public AppResponse getDriver(String driverId){
        try{
            return driverServices.getDriver(driverId);
        }catch (Exception e){
            logger.error("查询司机详情失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询司机信息分页列表
     * @param driverInfo
     * @return
     * @Author feng
     * @Date 2020-04-16
     */
    @PostMapping("listDrivers")
    public AppResponse listDrivers(DriverInfo driverInfo){
        try{
            return driverServices.listDrivers(driverInfo);
        }catch (Exception e){
            logger.error("查询司机分页失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改司机信息
     * @param driverInfo
     * @return
     * @Author feng
     * @Date 2020-04-16
     */
    @PostMapping("updateDriver")
    public AppResponse updateDriver(DriverInfo driverInfo,String imagePath){
        try{
            String userId = SecurityUtils.getCurrentUserId();
            driverInfo.setUserImage(imagePath);
            driverInfo.setCreateUser(userId);
            driverInfo.setUpdateUser(userId);
            return driverServices.updateDriver(driverInfo);
        }catch (Exception e){
            logger.error("查询司机分页失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 删除司机信息
     * @param driverId
     * @return
     * @Author feng
     * @Date 2020-04-16
     */
    @PostMapping("deleteDriver")
    public AppResponse deleteDriver(String driverId){
        try{
            String userId = SecurityUtils.getCurrentUserId();
            return driverServices.deleteDriver(driverId,userId);
        }catch (Exception e){
            logger.error("删除司机信息失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
