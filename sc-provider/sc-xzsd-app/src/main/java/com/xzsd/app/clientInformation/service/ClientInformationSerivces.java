package com.xzsd.app.clientInformation.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.clientInformation.dao.ClientInformationDao;
import com.xzsd.app.clientInformation.entity.ClientInformationInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ClientInformationSerivces {

    @Resource
    private ClientInformationDao clientInformationDao;

    /**
     * 修改邀请码
     * @param clientInformationInfo
     * @return
     * @date 2020-04-23
     * @author feng
     */
    public AppResponse updateClientInvite(ClientInformationInfo clientInformationInfo){
        int count = clientInformationDao.checkInviteCode(clientInformationInfo.getInviteCode());
        if (count == 0){
            return AppResponse.bizError("未找到店铺");
        }
        count = clientInformationDao.updateClientInvite(clientInformationInfo);
        if(count == 0){
            return AppResponse.bizError("修改邀请码失败");
        }
        return AppResponse.success("修改邀请码成功");
    }
}
