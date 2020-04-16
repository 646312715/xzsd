package com.xzsd.pc.store.dao;

import com.xzsd.pc.store.entity.AreaInfo;
import com.xzsd.pc.store.entity.StoreInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StoreDao {
    /**
     * 查询省市区下拉框接口
     * @param areaId
     * @return
     */
    List<AreaInfo> listArea(String areaId);

    /**
     * 营业执照编码查重
     * @param storeInfo
     * @return
     */
    int getBusinessCode(StoreInfo storeInfo);

    /**
     * 新增门店信息接口
     * @param storeInfo
     * @return
     */
    int addStore(StoreInfo storeInfo);

    /**
     * 查询门店信息详情接口
     * @param storeId
     * @return
     */
    StoreInfo getStore(String storeId);

    /**
     * 分页查询门店信息
     * @param storeInfo
     * @return
     */
    List<StoreInfo> listStores(StoreInfo storeInfo);

    /**
     * 修改门店信息接口
     * @param storeInfo
     * @return
     */
    int updateStore(StoreInfo storeInfo);

    /**
     * 删除门店信息接口
     * @param listStoreId
     * @param loginId
     * @return
     */
    int deleteStore(@Param("listStoreId")List<String> listStoreId, @Param("loginId")String loginId);

}
