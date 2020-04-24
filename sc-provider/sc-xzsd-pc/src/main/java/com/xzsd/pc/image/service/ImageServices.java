package com.xzsd.pc.image.service;

import com.neusoft.core.restful.AppResponse;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImageServices {

    @Transactional(rollbackFor = Exception.class)
    public AppResponse uploadImage(String key, MultipartFile multipartFile) throws Exception {
        // 1 初始化用户身份信息（secretId, secretKey）。
        String secretId = "AKIDZyCEdq61KcicL4Kv56tVv8D4czDGeqMr";
        String secretKey = "eqrhombh7OzrcK1iG7sjVAh7NUoxxmSz";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
// clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region("ap-guangzhou");
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        // 指定要上传的文件

        File localFile = null;
        System.out.println(multipartFile.getSize());
        try {
            localFile = File.createTempFile("temp", null);
            multipartFile.transferTo(localFile);

        } catch (IOException e){
            System.out.println(e.toString());
            throw e;
        }
        // 指定要上传到的存储桶
        String bucketName = "feng-1301694557";
        // 指定要上传到 COS 上对象键
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        // 关闭客户端(关闭后台线程)
        delteTempFile(localFile);
        cosClient.shutdown();
        String imagePath = "https://"+bucketName+".cos.ap-guangzhou.myqcloud.com/"+key;
        System.out.println(imagePath);
        Map<String,String> map = new HashMap<String, String>();
        map.put("imagePath",imagePath);
        return AppResponse.success("图片上传成功",map);
    }

//    public static File multipartFileToFile(MultipartFile file) throws Exception {
//
//        File toFile = null;
//        if (file.equals("") || file.getSize() <= 0) {
//            file = null;
//        } else {
//            InputStream ins = null;
//            ins = file.getInputStream();
//            toFile = new File(file.getOriginalFilename());
//            inputStreamToFile(ins, toFile);
//            ins.close();
//        }
//        return toFile;
//    }
//
//    //获取流文件
//    private static void inputStreamToFile(InputStream ins, File file) {
//        try {
//            OutputStream os = new FileOutputStream(file);
//            int bytesRead = 0;
//            byte[] buffer = new byte[8192];
//            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
//                os.write(buffer, 0, bytesRead);
//            }
//            os.close();
//            ins.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
    /**
     * 删除本地临时文件
     * @param file
     */
    public static void delteTempFile(File file) {
        if (file != null) {
            File del = new File(file.toURI());
            del.delete();
        }
    }
}
