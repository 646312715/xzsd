package com.xzsd.app.clientInformation.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.clientInformation.entity.ClientInformationInfo;
import com.xzsd.app.clientInformation.service.ClientInformationSerivces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/clientInformation")
public class ClientInformationController {

    private static final Logger logger = LoggerFactory.getLogger(ClientInformationController.class);

    @Resource
    private ClientInformationSerivces clientInformationSerivces;

    @PostMapping("updateClientInvite")
    public AppResponse updateClientInvite(ClientInformationInfo clientInformationInfo){
        try{
            return clientInformationSerivces.updateClientInvite(clientInformationInfo);
        }catch (Exception e){
            logger.error("新增订单失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
