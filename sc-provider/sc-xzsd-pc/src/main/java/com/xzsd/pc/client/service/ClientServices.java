package com.xzsd.pc.client.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.client.dao.ClientDao;
import com.xzsd.pc.client.entity.ClientInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClientServices {

    @Resource
    private ClientDao clientDao;
    /**
     * 查询客户列表
     * @param clientInfo
     * @return
     * @Author feng
     * @Date 2020-04-14
     */
    public AppResponse listClients(ClientInfo clientInfo,String loginId){
        PageHelper.startPage(clientInfo.getPageNum(), clientInfo.getPageSize());
        List<ClientInfo> clientInfoList = clientDao.listClients(clientInfo,loginId);
        if(clientInfoList.size()==0){
            return AppResponse.notFound("客户列表无数据");
        }
        // 包装Page对象
        PageInfo<ClientInfo> pageData = new PageInfo<ClientInfo>(clientInfoList);
        return AppResponse.success("客户列表查询成功！",pageData);
    }
}
