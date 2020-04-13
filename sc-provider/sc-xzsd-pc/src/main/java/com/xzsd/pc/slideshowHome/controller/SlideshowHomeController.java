package com.xzsd.pc.slideshowHome.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.slideshowHome.entity.SlideshowHomeInfo;
import com.xzsd.pc.slideshowHome.service.SlideshowHomeServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author 64631
 */
@RestController
@RequestMapping("/SlideshowHome")
public class SlideshowHomeController {
    private static final Logger logger = LoggerFactory.getLogger(SlideshowHomeController.class);

    @Resource
    private SlideshowHomeServices slideshowHomeServices;


    /**
     * 新增轮播图
     * @param slideshowHomeInfo
     * @return AppResponse
     * @author feng
     */
    @PostMapping("addSlideshowHome")
    public AppResponse addSlideshowHome(SlideshowHomeInfo slideshowHomeInfo, String imagePath) {
        try {
            String userId = SecurityUtils.getCurrentUserId();
            slideshowHomeInfo.setCreateUser(userId);
            slideshowHomeInfo.setUpdateUser(userId);
            slideshowHomeInfo.setSlideshowPath(imagePath);
            AppResponse appResponse = slideshowHomeServices.addSlideshowHome(slideshowHomeInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("轮播图新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

}
