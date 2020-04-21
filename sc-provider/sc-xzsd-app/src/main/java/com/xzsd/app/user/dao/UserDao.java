package com.xzsd.app.user.dao;

import com.xzsd.app.user.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    /**
     * 查询个人信息接口
     * @param userId
     * @return
     */
    UserInfo getUser(String userId);

    /**
     * 修改个人密码接口
     * @param userInfo
     * @return
     */
    int updateUserPassword(@Param("userInfo") UserInfo userInfo);

    /**
     * 查找个人密码
     */
    String getUserPassword(String userId);
}
