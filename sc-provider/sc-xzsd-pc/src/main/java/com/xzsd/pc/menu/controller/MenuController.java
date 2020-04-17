package com.xzsd.pc.menu.controller;

import com.neusoft.core.exception.ScServerException;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;

import com.xzsd.pc.base.constant.GlobalConstant;
import com.xzsd.pc.base.entity.Tree;
import com.xzsd.pc.menu.entity.Menu;
import com.xzsd.pc.menu.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * @ClassName MenuService
 * @Description 菜单管理
 * @Author zhu.qf@neusoft.com
 * @Date 2018/11/28
 */
@RestController
@RequestMapping("/menu")
@Validated
public class MenuController {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Resource
    private MenuService menuService;

    /**
     * 部门：南京软件研发中心
     * 功能：获取菜单
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/28
     */

    @RequestMapping(value = {"listMenu","listMenuHome"} )
    public AppResponse listMenus(String role) {
        try {
            Map<String,Object> map = menuService.listMenus(GlobalConstant.MENU_ROOT,role);
            return AppResponse.success("查询成功", map);
        } catch (Exception e) {
            logger.error("菜单获取异常", e);
            throw new ScServerException("菜单获取失败，请重试");
        }
    }

    /**
     * 部门：南京软件研发中心
     * 功能：删除菜单
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/8/30
     */
    @RequestMapping(value = "deleteMenu")
    public AppResponse deleteMenu(Menu menu) {
        try {
            String userId = SecurityUtils.getCurrentUserId();
            menu.setUpdateUser(userId);
            menuService.deleteMenu(menu);
            return AppResponse.success("菜单删除成功");
        } catch (Exception e) {
            logger.error("菜单删除错误", e);
            return AppResponse.notFound("删除失败");
        }
    }
//
    /**
     * 部门：南京软件研发中心
     * 功能：新增菜单
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/28
     */
    @RequestMapping(value = "addMenu")
    public AppResponse insertMenu(@Valid Menu menu) {
        try {
            String userId =  SecurityUtils.getCurrentUserId();
            menu.setCreateUser(userId);
            menu.setUpdateUser(userId);
            return menuService.insertMenu(menu);
        } catch (Exception e) {
            logger.error("菜单新增异常", e);
            throw new ScServerException("菜单新增失败，请重试！");
        }
    }
    /**
     * 部门：南京软件研发中心
     * 功能：修改菜单
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/28
     */
    @RequestMapping(value = "updateMenu")
    public AppResponse updateMenu(@Valid Menu menu) {
        try {
            String userId = SecurityUtils.getCurrentUserId();
            menu.setUpdateUser(userId);
            return menuService.updateMenu(menu);
        } catch (Exception e) {
            logger.error("菜单修改异常", e);
            throw new ScServerException("菜单修改失败，请重试");
        }
    }

    /**
     * 查询菜单详情
     *
     * @param menuId
     * @return
     */
    @RequestMapping(value = "getMenu")
    public AppResponse getMenu(String menuId) {
        try {
            return menuService.getMenu(menuId);
        } catch (Exception e) {
            logger.error("获取菜单失败", e);
            throw new ScServerException("获取菜单失败，请重试");
        }
    }
}
