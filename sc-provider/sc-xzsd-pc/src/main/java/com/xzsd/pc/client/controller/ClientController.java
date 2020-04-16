package com.xzsd.pc.client.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.client.entity.ClientInfo;
import com.xzsd.pc.client.service.ClientServices;
import com.xzsd.pc.user.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/client")
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Resource
    private ClientServices clientServices;

/**
 * 查询客户列表
 * @param clientInfo
 * @return
 * @Author feng
 * @Date 2020-04-14
 */
@PostMapping("listClients")
public AppResponse listClients(ClientInfo clientInfo){
    try {
        String userId = SecurityUtils.getCurrentUserId();
        return clientServices.listClients(clientInfo,userId);
    } catch (Exception e) {
        logger.error("查询客户列表异常", e);
        System.out.println(e.toString());
        throw e;
    }
}
}
