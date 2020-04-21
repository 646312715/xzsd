package com.xzsd.app.user.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.user.dao.UserDao;
import com.xzsd.app.user.entity.UserInfo;
import com.xzsd.app.utils.PasswordUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static com.xzsd.app.utils.PasswordUtils.equalPassword;

@Service
public class UserServices {
    @Resource
    private UserDao userDao;

    /**
     * 查询个人信息接口
     * @param userId
     * @return
     * @author feng
     * @date 2020-04-20
     */
    public AppResponse getUser(String userId){
        UserInfo userInfo = userDao.getUser(userId);
        if (userInfo == null||userInfo.getRole().equals("0")||userInfo.getRole().equals("1")){
            return AppResponse.notFound("未找到数据");
        }
        if (userInfo.getRole().equals("3")){
            userInfo.setDriverName(userInfo.getUserName());
        }
        return AppResponse.success("查询个人信息成功",userInfo);
    }
    /**
     * 修改密码接口
     * @param userInfo
     * @return
     * @author feng
     * @date 2020-04-20
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateUserPassword(UserInfo userInfo){
        //密码校验
        if (equalPassword(userInfo.getUserPassword(),userDao.getUserPassword(userInfo.getUserId()))){
            int count = userDao.updateUserPassword(userInfo);
            if(count == 0){
                return AppResponse.bizError("修改失败，请重试");
            }
        }else{
            return AppResponse.bizError("修改失败，密码错误");
        }
        return AppResponse.success("修改成功");
    }
}
