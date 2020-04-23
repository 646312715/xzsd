package com.xzsd.app.driverHome.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.driverHome.service.DriverHomeServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
@RestController
@RequestMapping("driverHome")
public class DriverHomeController {
    private static final Logger logger = LoggerFactory.getLogger(DriverHomeController.class);

    @Resource
    private DriverHomeServices driverHomeServices;
    /**
     * 查询司机负责的门店信息
     * @return
     * @date 2020-04-23
     * @author feng
     */
    @PostMapping("listDriverStores")
    public AppResponse listDriverStores(){
        try{
            String userId = SecurityUtils.getCurrentUserId();
            //String userId = "2020041610571840424";
            return driverHomeServices.listDriverStores(userId);
        }catch (Exception e){
            logger.error("查询司机负责的门店信息失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
