package com.xzsd.pc.store.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.store.service.StoreServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/selectCombox")
public class selectComboxController {

    private static final Logger logger = LoggerFactory.getLogger(selectComboxController.class);

    @Resource
    private StoreServices storeServices;

    /**
     * 查询省市区下拉框
     * @param areaId
     * @return
     * @Author feng
     * @Date 2020-04-15
     */
    @PostMapping("listArea")
    public AppResponse listArea(String areaId){
        try{
            return storeServices.listArea(areaId);
        }catch (Exception e){
            logger.error("查询省市区下拉框失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }


}
