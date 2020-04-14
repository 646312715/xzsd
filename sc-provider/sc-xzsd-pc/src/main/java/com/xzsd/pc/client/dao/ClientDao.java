package com.xzsd.pc.client.dao;

import com.xzsd.pc.client.entity.ClientInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClientDao {
    /**
     * 客户列表
     * @param clientInfo
     * @param loginId
     * @return
     */
    List<ClientInfo> listClients(@Param("clientInfo") ClientInfo clientInfo,@Param("loginId") String loginId);
}
