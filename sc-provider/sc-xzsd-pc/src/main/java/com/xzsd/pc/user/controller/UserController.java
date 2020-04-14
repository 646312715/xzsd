package com.xzsd.pc.user.controller;


import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.user.entity.UserInfo;
import com.xzsd.pc.user.service.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserServices userServices;
    /**
     *
     *新增用户
     * @param userInfo
     * @return AppResponse
     * @author feng
     * @Date 2020-03-21
     */
    @PostMapping("addUser")
    public AppResponse addUser(UserInfo userInfo,String imagePath) {
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            userInfo.setUserImage(imagePath);
            userInfo.setCreateUser(userId);
            userInfo.setUpdateUser(userId);
            userInfo.setVersion("0");
            AppResponse appResponse = userServices.addUser(userInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("用户新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * demo 查询用户详情
     *
     * @param userId
     * @return AppResponse
     * @author feng
     * @Date 2020-03-21
     */
    @RequestMapping(value = "findUserById")
    public AppResponse getUser(String userId) {
        try {
            return userServices.getUser(userId);
        } catch (Exception e) {
            logger.error("用户查询错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 修改用户
     * @param userInfo
     * @return AppResponse
     * @author feng
     * @Date 2020-03-21
     */
    @PostMapping("updateUser")
    public AppResponse updateUser(UserInfo userInfo,String imagePath) {
        try {
            //获取用户id
            String userId = SecurityUtils.getCurrentUserId();
            userInfo.setUserImage(imagePath);
            userInfo.setUpdateUser(userId);
            return userServices.updateUser(userInfo);
        } catch (Exception e) {
            logger.error("修改用户信息错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 删除用户
     * @param userId
     * @return AppResponse
     * @author feng
     * @Date 2020-03-21
     */
    @PostMapping("deleteUser")
    public AppResponse deleteUser(String userId) {
        try {
            //获取用户id
            String loginId = SecurityUtils.getCurrentUserId();
            return userServices.deleteUser(userId, loginId);
        } catch (Exception e) {
            logger.error("用户删除错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * demo 用户列表(分页)
     *
     * @param userInfo
     * @return AppResponse
     * @author feng
     * @Date 2020-03-21
     */
    @RequestMapping(value = "listUsers")
    public AppResponse listUsers(UserInfo userInfo) {
        try {
            return userServices.listUsers(userInfo);

        } catch (Exception e) {
            logger.error("查询用户列表异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
