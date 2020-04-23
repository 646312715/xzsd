package com.xzsd.app.clientInformation.dao;

import com.xzsd.app.clientInformation.entity.ClientInformationInfo;

public interface ClientInformationDao {

    /**
     * 修改邀请码接口
     * @param clientInformationInfo
     * @return
     */
    int updateClientInvite(ClientInformationInfo clientInformationInfo);

    /**
     * 邀请码校验
     * @param inviteCode
     * @return
     */
    int checkInviteCode(String inviteCode);
}
