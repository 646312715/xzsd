package com.xzsd.app.user.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.user.entity.UserInfo;
import com.xzsd.app.user.service.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/userInformation")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserServices userServices;

    /**
     * 查询个人信息接口
     * @return
     * @author feng
     * @date 2020-04-20
     */
    @PostMapping("getUser")
    public AppResponse getUser(){
        try{
            String userId = SecurityUtils.getCurrentUserId();
            return userServices.getUser(userId);
        }catch (Exception e){
            logger.error("用户查询失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    @PostMapping("updateUserPassword")
    public AppResponse updateUserPassword(UserInfo userInfo){
        try{
            String userId = SecurityUtils.getCurrentUserId();
            userInfo.setUserId(userId);
            return userServices.updateUserPassword(userInfo);
        }catch (Exception e){
            logger.error("用户查询失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
