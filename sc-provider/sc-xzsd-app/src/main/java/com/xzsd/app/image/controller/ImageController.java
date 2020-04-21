package com.xzsd.app.image.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.app.image.service.ImageServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;


@RestController
@RequestMapping("/image")
public class ImageController {
    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Resource
    private ImageServices imageServices;

    @PostMapping("uploadImage")
    public AppResponse uploadImage(String userId, @RequestParam("multipartFile") MultipartFile multipartFile) throws Exception {
        try{
            String key = "image/"+userId+"/user/"+ StringUtil.getCommonCode(2)+".jpg";
            System.out.println(key);

            return imageServices.uploadImage(key, multipartFile);
        } catch (Exception e) {
            logger.error("用户新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
