package com.xzsd.pc.store.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.store.dao.StoreDao;
import com.xzsd.pc.store.entity.AreaDO;
import com.xzsd.pc.store.entity.AreaInfo;
import com.xzsd.pc.store.entity.StoreInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class StoreServices {

    @Resource
    private StoreDao storeDao;

    /**
     * 查询省市区下拉框
     * @param areaId
     * @return
     * @Author feng
     * @Date 2020-04-15
     */
    public AppResponse listArea(String areaId){
        List<AreaInfo> areaInfos = storeDao.listArea(areaId);
        if(areaInfos.size() == 0){
            return AppResponse.notFound("未找到数据");
        }
        AreaDO areaDO = new AreaDO();
        areaDO.setAreaList(areaInfos);
        return AppResponse.success("查询省市区成功",areaDO);
    }

    /**
     * 新增门店信息
     * @param storeInfo
     * @return
     * @Author feng
     * @Date 2020-04-15
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addStore(StoreInfo storeInfo){
        AppResponse appResponse=AppResponse.success("新增成功！");
        int count = storeDao.getBusinessCode(storeInfo);
        if (count != 0){
            return AppResponse.bizError("已存在该店铺，请重新输入！");
        }
        storeInfo.setStoreId(StringUtil.getCommonCode(2));
        storeInfo.setIsDelete(0);
        storeInfo.setVersion("0");
        storeInfo.setInviteCode(generateShortUuid());
        count = storeDao.addStore(storeInfo);
        if (count==0) {
            appResponse = AppResponse.bizError("新增失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 查询门店信息详情
     * @param storeId
     * @return
     * @Author feng
     * @Date 2020-04-15
     */
    public AppResponse getStore(String storeId){
        StoreInfo storeInfo = storeDao.getStore(storeId);
        if(storeInfo == null){
            return AppResponse.notFound("未找到数据");
        }
        return AppResponse.success("查询门店信息详情",storeInfo);
    }
    /**
     * 查询门店信息分页
     * @param storeInfo
     * @return
     * @Author feng
     * @Date 2020-04-15
     */
    public AppResponse listStores(StoreInfo storeInfo){
        PageHelper.startPage(storeInfo.getPageNum(), storeInfo.getPageSize());
        List<StoreInfo> goodsInfoList = storeDao.listStores(storeInfo);
        if(goodsInfoList.size() == 0){
            return AppResponse.notFound("未找到数据");
        }
        // 包装Page对象
        PageInfo<StoreInfo> pageData = new PageInfo<StoreInfo>(goodsInfoList);
        return AppResponse.success("查询成功！",pageData);
    }
    /**
     * 修改门店信息
     * @param storeInfo
     * @return
     * @Author feng
     * @Date 2020-04-15
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateStore(StoreInfo storeInfo){
        AppResponse appResponse=AppResponse.success("修改成功！");
        int count = storeDao.getBusinessCode(storeInfo);
        if (count != 0){
            return AppResponse.bizError("已存在该店铺，请重新输入！");
        }
        storeInfo.setOldVersion(storeInfo.getVersion());
        storeInfo.setVersion(String.valueOf(Integer.parseInt(storeInfo.getVersion())+1));
        count = storeDao.updateStore(storeInfo);
        if (count==0) {
            appResponse = AppResponse.bizError("新增失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 删除门店信息
     * @param storeIds
     * @param loginId
     * @return
     * @Author feng
     * @Date 2020-04-15
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteStore(String storeIds,String loginId){
        List<String> listCode = Arrays.asList(storeIds.split(","));
        int count = storeDao.deleteStore(listCode,loginId);
        AppResponse appResponse = AppResponse.success("删除成功！");
        if(0 == count) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }
    /**
     * Java生成8位随机邀请码,不重复
     */
    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };

    /**
     * Java生成8位随机邀请码,不重复
     */
    public static String generateShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();

    }
}
