package com.xzsd.app.managerInformation.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.managerInformation.entity.ManagerInformationInfo;
import com.xzsd.app.managerInformation.service.ManagerInformationSerivces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/clientInformation")
public class ManagerInformationController {

    private static final Logger logger = LoggerFactory.getLogger(ManagerInformationController.class);

    @Resource
    private ManagerInformationSerivces managerInformationSerivces;
    /**
     * 查询司机负责的门店信息
     * @return
     * @date 2020-04-23
     * @author feng
     */
    @PostMapping("listManagerDrivers")
    public AppResponse listManagerDrivers(){
        try{
            String userId = SecurityUtils.getCurrentUserId();
            //String userId = "2020040922283411448";
            return managerInformationSerivces.listManagerDrivers(userId);
        }catch (Exception e){
            logger.error("查询司机负责的门店信息失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
