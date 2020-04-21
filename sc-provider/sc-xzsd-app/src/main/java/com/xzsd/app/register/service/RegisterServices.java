package com.xzsd.app.register.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.app.register.dao.RegisterDao;
import com.xzsd.app.register.entity.RegisterInfo;
import com.xzsd.app.utils.PasswordUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class RegisterServices {
    @Resource
    private RegisterDao registerDao;

    /**
     * 注册接口
     * @param registerInfo
     * @return
     * @Author feng
     * @Date 2020-03-21
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse clientRegister(RegisterInfo registerInfo){
        String userId = StringUtil.getCommonCode(2);
        registerInfo.setUserId(userId);
        registerInfo.setCreateUser(userId);
        registerInfo.setUpdateUser(userId);
        registerInfo.setIsDelete(0);
        registerInfo.setRole("4");
        registerInfo.setVersion("0");
        //校验账号是否存在
        int check = registerDao.countUserAcct(registerInfo);
        if ((check & 1) == 1){
            return AppResponse.bizError("新增客户失败，账号已存在！");
        }
        if ((check & 2) == 2){
            return AppResponse.bizError("新增客户失败，手机号码已存在！");
        }
        if ((check & 4) == 4){
            return AppResponse.bizError("新增客户失败，邀请码不存在！");
        }
        //密码加密
        String password = PasswordUtils.generatePassword(registerInfo.getUserPassword());
        registerInfo.setUserPassword(password);
        //注册
        int count = registerDao.clientRegister(registerInfo);
        if(0 == count) {
            return AppResponse.bizError("新增客户失败，请重试！");
        }
        return AppResponse.success("新增客户成功！");
    }
}
