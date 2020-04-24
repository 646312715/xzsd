package com.xzsd.pc.topOfColumn.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.topOfColumn.service.TopOfColumnServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/topOfColumn")
public class TopOfColumnController {

    private static final Logger logger = LoggerFactory.getLogger(TopOfColumnController.class);
    @Resource
    private TopOfColumnServices topOfColumnServices;

    /**
     * 顶部栏接口
     * @return
     * @Author feng
     * @Date 2020-04-16
     */
    @PostMapping("getTopOfColumn")
    public AppResponse getTopOfColumn(){
        try{
            String userId = SecurityUtils.getCurrentUserId();
            return topOfColumnServices.getTopOfColumn(userId);
        }catch (Exception e){
            logger.error("用户查询失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
