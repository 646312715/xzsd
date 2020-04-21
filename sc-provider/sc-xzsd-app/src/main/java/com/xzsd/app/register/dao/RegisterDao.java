package com.xzsd.app.register.dao;

import com.xzsd.app.register.entity.RegisterInfo;

public interface RegisterDao {
    /**
     * 注册客户接口
     * @param registerInfo
     * @return
     */
    int clientRegister(RegisterInfo registerInfo);

    /**
     * 查重
     * @param registerInfo
     * @return
     */
    int countUserAcct(RegisterInfo registerInfo);


}
