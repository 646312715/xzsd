package com.xzsd.pc.store.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.store.entity.StoreInfo;
import com.xzsd.pc.store.service.StoreServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/store")
public class StoreController {

    private static final Logger logger = LoggerFactory.getLogger(StoreController.class);
    @Resource
    private StoreServices storeServices;
    @PostMapping("addStore")
    public AppResponse addStore(StoreInfo storeInfo){
        try{
            String userId = SecurityUtils.getCurrentUserId();
            storeInfo.setCreateUser(userId);
            storeInfo.setUpdateUser(userId);
            AppResponse appResponse = storeServices.addStore(storeInfo);
            return appResponse;
        }catch (Exception e){
            logger.error("查询省市区下拉框失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询门店信息详情
     * @param storeId
     * @return
     * @Author feng
     * @Date 2020-04-15
     */
    @PostMapping("getStore")
    public AppResponse getStore(String storeId){
        try{
            return storeServices.getStore(storeId);
        }catch (Exception e){
            logger.error("查询门店信息详情失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 查询门店信息分页
     * @param storeInfo
     * @return
     * @Author feng
     * @Date 2020-04-15
     */
    @PostMapping("listStores")
    public AppResponse listStores(StoreInfo storeInfo){
        try{
            String userId = SecurityUtils.getCurrentUserId();
            //把登录用户放到修改客户记录上，用于查询
            storeInfo.setUpdateUser(userId);
            return storeServices.listStores(storeInfo);
        }catch (Exception e){
            logger.error("查询门店信息列表失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 修改门店信息
     * @param storeInfo
     * @return
     * @Author feng
     * @Date 2020-04-15
     */
    @PostMapping("updateStore")
    public AppResponse updateStore(StoreInfo storeInfo){
        try{
            String userId = SecurityUtils.getCurrentUserId();
            storeInfo.setUpdateUser(userId);
            return storeServices.updateStore(storeInfo);
        }catch (Exception e){
            logger.error("查询门店信息列表失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 删除门店信息
     * @param storeId
     * @return
     * @Author feng
     * @Date 2020-04-15
     */
    @PostMapping("deleteStore")
    public AppResponse deleteStore(String storeId){
        try{
            String userId = SecurityUtils.getCurrentUserId();
            return storeServices.deleteStore(storeId,userId);
        }catch (Exception e){
            logger.error("删除门店信息失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
