package com.neusoft.webauth.user.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.neusoft.util.UUIDUtils;
import com.neusoft.webauth.user.dao.UserDao;
import com.neusoft.webauth.user.entity.UserInfo;
import com.neusoft.webauth.user.entity.UserSettingDTO;
import com.neusoft.webauth.utils.PasswordUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import static com.neusoft.core.page.PageUtils.getPageInfo;

/**
 * @ClassName UserService
 * @Description 用户管理
 * @Author zhu.qf@neusoft.com
 * @Date 2018/11/28
 */
@Service
public class UserService {
    @Resource
    private UserDao userDao;

    /**
     * 新增用户
     * @param userInfo
     * @return
     * @Author feng
     * @Date 2020-03-21
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addUser(UserInfo userInfo) {

        userInfo.setIsDelete("0");

        // 新增用户
        int count = userDao.addUser(userInfo);
        if(0 == count) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * demo 查询用户详情
     * @param userId
     * @return
     * @Author feng
     * @Date 2020-03-21
     */
    public UserInfo findUserById(String userId) {
        UserInfo userInfo = userDao.findUserById(userId);
        return userInfo;
    }

    /**
     * demo 修改用户
     * @param userInfo
     * @return
     * @Author feng
     * @Date 2020-03-21
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateUser(UserInfo userInfo) {
        AppResponse appResponse = AppResponse.success("修改成功");
        // 校验账号是否存在
        int countUserAcct = userDao.countUserAcct(userInfo);
        if(0 != countUserAcct) {
            return AppResponse.bizError("用户账号已存在，请重新输入！");
        }
        // 修改用户信息
        int count = userDao.updateUser(userInfo);
        if (0 == count) {
            appResponse = AppResponse.versionError("数据有变化，请刷新！");
            return appResponse;
        }
        return appResponse;
    }

    /**
     * demo 删除用户
     * @param userId
     * @param linkUserId
     * @return
     * @Author feng
     * @Date 2020-03-21
     */
    public AppResponse deleteUser(UserInfo userInfo) {
        List<String> listCode = Arrays.asList(userInfo.getUserId().split(","));
        AppResponse appResponse = AppResponse.success("删除成功！");
        for (String str : listCode) {
            if (str.equals(userInfo.getUpdateUser())){
                return AppResponse.bizError("无法删除自己,请重试！");
            }
        }
        // 删除用户
        int count = userDao.deleteUser(listCode,userInfo.getUpdateUser());
        if(0 == count) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }

    /**
     * demo 查询用户列表（分页）
     * @param userInfo
     * @return
     * @Author feng
     * @Date 2020-03-21
     */
    public AppResponse listUsers(UserInfo userInfo) {
        PageHelper.startPage(userInfo.getPageNum(), userInfo.getPageSize());
        List<UserInfo> userInfoList = userDao.listUsers(userInfo);
        // 包装Page对象
        PageInfo<UserInfo> pageData = new PageInfo<UserInfo>(userInfoList);
        return AppResponse.success("查询成功！",pageData);
    }
}
