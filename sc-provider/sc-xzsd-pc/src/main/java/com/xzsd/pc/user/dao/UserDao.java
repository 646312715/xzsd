package com.xzsd.pc.user.dao;


import com.xzsd.pc.user.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 64631
 */
public interface UserDao {
    /**
     * 统计用户账号数量
     * @param userInfo 用户信息
     * @return 账号数量
     */
    List<UserInfo> countUserAcct(UserInfo userInfo);
    /**
     * 新增用户
     * @param userInfo 用户信息
     * @return 新增结果
     */
    int addUser(UserInfo userInfo);
    /**
     * 用户详情
     * @param userId 用户编号
     * @return 用户信息
     */
    UserInfo getUser(String userId);

    /**
     * 修改用户信息
     * @param userInfo 用户信息
     * @return 修改结果
     */
    int updateUser(UserInfo userInfo);

    /**
     * 删除用户信息
     * @param listCode
     * @param loginId
     * @return 删除结果
     */
    int deleteUser(@Param("listCode") List<String> listCode, @Param("loginId") String loginId);

    /**
     * 获取所有用户信息
     * @param userInfo 用户信息
     * @return 所有用户信息
     */
    List<UserInfo> listUsers(UserInfo userInfo);

}
