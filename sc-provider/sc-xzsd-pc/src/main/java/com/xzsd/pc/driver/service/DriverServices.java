package com.xzsd.pc.driver.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.driver.dao.DriverDao;
import com.xzsd.pc.driver.entity.DriverInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class DriverServices {

    @Resource
    private DriverDao driverDao;

    /**
     * 新增司机
     * @param driverInfo
     * @return
     * @Author feng
     * @Date 2020-04-16
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addDriver(DriverInfo driverInfo){
        //校验账号是否存在
        int check = driverDao.countUserAcct(driverInfo);
        if ((check & 1) == 1){
            return AppResponse.bizError("新增失败，账号已存在！");
        }
        if ((check & 2) == 2){
            return AppResponse.bizError("新增失败，手机号码已存在！");
        }
        driverInfo.setDriverId(StringUtil.getCommonCode(2));
        driverInfo.setIsDelete(0);
        driverInfo.setVersion("0");
        driverInfo.setRole("3");
        //新增司机信息
        int count = driverDao.addDriver(driverInfo);
        if(0 == count) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * 查询司机信息详情
     * @param driverId
     * @return
     * @Author feng
     * @Date 2020-04-16
     */
    public AppResponse getDriver(String driverId){
        DriverInfo driverInfo = driverDao.getDriver(driverId);
        if (driverInfo.getDriverId() == null){
            return AppResponse.notFound("司机信息详情无数据");
        }
        return AppResponse.success("查询司机成功",driverInfo);
    }
    /**
     * 查询司机信息分页列表
     * @param driverInfo
     * @return
     * @Author feng
     * @Date 2020-04-16
     */
    public AppResponse listDrivers(DriverInfo driverInfo){
        PageHelper.startPage(driverInfo.getPageNum(), driverInfo.getPageSize());
        List<DriverInfo> driverInfos = driverDao.listDrivers(driverInfo);
        // 包装Page对象
        if(driverInfos.size() == 0){
            return AppResponse.notFound("司机分页列表无数据");
        }
        PageInfo<DriverInfo> pageData = new PageInfo<DriverInfo>(driverInfos);
        return AppResponse.success("查询成功！",pageData);
    }
    /**
     * 修改司机信息
     * @param driverInfo
     * @return
     * @Author feng
     * @Date 2020-04-16
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateDriver(DriverInfo driverInfo){
        AppResponse appResponse = AppResponse.success("修改成功");
        // 校验账号是否存在
        int check = driverDao.countUserAcct(driverInfo);
        if ((check & 1) == 1){
            return AppResponse.bizError("修改失败，账号已存在！");
        }
        if ((check & 2) == 2){
            return AppResponse.bizError("修改失败，手机号码已存在！");
        }
        // 修改用户信息
        int count = driverDao.updateDriver(driverInfo);
        if (0 == count) {
            appResponse = AppResponse.bizError("数据有变化，请刷新！");
            return appResponse;
        }
        return appResponse;
    }
    /**
     * 删除司机信息
     * @param driverId
     * @param loginId
     * @return
     * @Author feng
     * @Date 2020-04-16
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteDriver(String driverId,String loginId){
        List<String> listCode = Arrays.asList(driverId.split(","));
        int count = driverDao.deleteDriver(listCode,loginId);
        AppResponse appResponse = AppResponse.success("删除成功！");
        if(0 == count) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }

}
