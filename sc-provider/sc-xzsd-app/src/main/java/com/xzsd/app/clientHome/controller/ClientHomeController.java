package com.xzsd.app.clientHome.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.clientHome.service.ClientHomeServices;
import com.xzsd.app.register.controller.RegisterController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/clientHome")
public class ClientHomeController {

    private static final Logger logger = LoggerFactory.getLogger(ClientHomeController.class);
    @Resource
    private ClientHomeServices clientHomeServices;
    /**
     * 查询首页轮播图
     * @return
     * @date 2020-04-20
     * @author feng
     */
    @PostMapping("listRotationCharHome")
    public AppResponse listRotationCharHome(){
        try {
            AppResponse appResponse = clientHomeServices.listRotationCharHome();
            return appResponse;
        } catch (Exception e) {
            logger.error("轮播图查询失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 查询热门商品位
     * @return
     * @date 2020-04-20
     * @author feng
     */
    @PostMapping("listHotGoods")
    public AppResponse listHotGoods(){
        try {
            AppResponse appResponse = clientHomeServices.listHotGoods();
            return appResponse;
        } catch (Exception e) {
            logger.error("热门商品查询失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
