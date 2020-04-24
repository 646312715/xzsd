package com.xzsd.pc.user.service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.user.dao.UserDao;
import com.xzsd.pc.user.entity.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import com.xzsd.pc.utils.PasswordUtils;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServices {
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
        userInfo.setUserId(StringUtil.getCommonCode(2));
        userInfo.setIsDelete(0);
        //校验账号是否存在
        int check = userDao.countUserAcct(userInfo);
        if ((check & 1) == 1){
            return AppResponse.bizError("新增用户失败，账号已存在！");
        }
        if ((check & 2) == 2){
            return AppResponse.bizError("新增用户失败，手机号码已存在！");
        }
        userInfo.setPassword(userInfo.getUserPassword());
        String password = PasswordUtils.generatePassword(userInfo.getUserPassword());
        userInfo.setUserPassword(password);
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
    public AppResponse getUser(String userId) {
        UserInfo userInfo = userDao.getUser(userId);
        if(userInfo == null){
            return AppResponse.notFound("未找到用户数据");
        }
        return AppResponse.success("查询成功！",userInfo);
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
        int check = userDao.countUserAcct(userInfo);
        if ((check & 1) == 1){
            return AppResponse.bizError("修改用户失败，账号已存在！");
        }
        if ((check & 2) == 2){
            return AppResponse.bizError("修改用户失败，手机号码已存在！");
        }
        // 修改用户信息
        userInfo.setPassword(userInfo.getUserPassword());
        String password = PasswordUtils.generatePassword(userInfo.getUserPassword());
        userInfo.setUserPassword(password);
        userInfo.setOldVersion(userInfo.getVersion());
        userInfo.setVersion(String.valueOf(Integer.parseInt(userInfo.getVersion())+1));
        int count = userDao.updateUser(userInfo);
        if (0 == count) {
            appResponse = AppResponse.versionError("用户数据有变化，请刷新！");
            return appResponse;
        }
        return appResponse;
    }
    /**
     * demo 删除用户
     * @param userId
     * @param loginId
     * @return
     * @Author feng
     * @Date 2020-03-21
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteUser(String userId,String loginId) {
        List<String> listCode = Arrays.asList(userId.split(","));
        AppResponse appResponse = AppResponse.success("删除成功！");
        for (String str : listCode) {
            if (str.equals(loginId)){
                return AppResponse.bizError("无法删除自己,请重试！");
            }
        }
        // 删除用户
        int count = userDao.deleteUser(listCode,loginId);
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
        System.out.println(userInfoList);
        if (userInfoList.size() == 0){
            return AppResponse.notFound("未找到用户列表数据");
        }
        // 包装Page对象
        PageInfo<UserInfo> pageData = new PageInfo<UserInfo>(userInfoList);
        return AppResponse.success("查询用户列表成功！",pageData);
    }

}
