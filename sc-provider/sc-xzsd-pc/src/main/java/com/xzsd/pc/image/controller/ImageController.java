package com.xzsd.pc.image.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.image.service.ImageServices;
import com.xzsd.pc.user.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;


@RestController
@RequestMapping("/imageUpload")
public class ImageController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private ImageServices imageServices;

    @PostMapping("uploadImage")
    public AppResponse uploadImage(@RequestParam MultipartFile imageFile) throws Exception {
        try{
            String key = "image/"+ StringUtil.getCommonCode(2)+".jpg";
            System.out.println(key);

            return imageServices.uploadImage(key, imageFile);
        } catch (Exception e) {
            logger.error("用户新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
