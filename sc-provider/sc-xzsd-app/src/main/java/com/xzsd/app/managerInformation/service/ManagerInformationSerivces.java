package com.xzsd.app.managerInformation.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.managerInformation.dao.ManagerInformationDao;
import com.xzsd.app.managerInformation.entity.ManagerInformationInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManagerInformationSerivces {

    @Resource
    private ManagerInformationDao managerInformationDao;

    /**
     * 查询司机负责的门店信息
     * @param userId
     * @return
     * @date 2020-04-23
     * @author feng
     */
    public AppResponse listManagerDrivers(String userId){
        ManagerInformationInfo managerInformationInfo = managerInformationDao.checkStore(userId);
        if (managerInformationInfo == null){
            return AppResponse.notFound("未找到店铺");
        }
        List<ManagerInformationInfo> managerInformationInfoList = managerInformationDao.listManagerDrivers(managerInformationInfo);
        if (managerInformationInfoList.size() == 0){
            return AppResponse.notFound("未找到司机");
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("list",managerInformationInfoList);
        return AppResponse.success("查找司机成功",map);
    }
}
