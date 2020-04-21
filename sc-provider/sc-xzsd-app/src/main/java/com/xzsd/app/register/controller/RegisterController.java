package com.xzsd.app.register.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.register.entity.RegisterInfo;
import com.xzsd.app.register.service.RegisterServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    @Resource
    private RegisterServices registerServices;

    /**
     * 注册
     * @param registerInfo
     * @param imagePath
     * @return
     */
    @PostMapping("clientRegister")
    public AppResponse clientRegister(RegisterInfo registerInfo,String imagePath){
        try {
            registerInfo.setUserImage(imagePath);

            AppResponse appResponse = registerServices.clientRegister(registerInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("用户新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
