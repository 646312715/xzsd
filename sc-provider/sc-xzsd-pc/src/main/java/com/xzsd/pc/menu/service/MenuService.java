package com.xzsd.pc.menu.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.neusoft.util.UUIDUtils;
import com.xzsd.pc.base.constant.GlobalConstant;
import com.xzsd.pc.base.entity.Tree;
import com.xzsd.pc.menu.dao.MenuDao;
import com.xzsd.pc.menu.entity.Menu;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.*;

/**
 * @ClassName MenuService
 * @Description 菜单管理
 * @Author zhu.qf@neusoft.com
 * @Date 2018/11/28
 */
@Service
public class MenuService {

    @Resource
    private MenuDao menuDao;


    /**
     * 部门：南京软件研发中心
     * 功能：查询全部菜单
     * 描述：查询全部菜单，如果角色代码不为空，则查询角色已授权的菜单
     * 作成者：朱庆锋
     * 作成时间：2018/11/28
     */
    public Map<String,Object> listMenus(String rootId,String role) {
        Map<String, Object> map = new HashMap<String, Object>();
//        根据角色代码查询已授权的菜单代码
        if(null != role && !"".equals(role)) {
            List<Menu> menuCodeList = menuDao.listRoleMenusBy(role);
            map.put("menuList", menuCodeList);
        }else{
            // 查询全部菜单
            List<Menu> allMenus = menuDao.listMenus();
            Tree rootTree = new Tree();
            // 格式化菜单成树结构
            initTree(rootTree, allMenus, rootId);
            map.put("menuList",rootTree.getChildren());
        }
        return map;
    }

    /**
     * 部门：南京软件研发中心
     * 功能：初始化菜单树
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/28
     */
    private void initTree(Tree rootTree, List<Menu> allMenus, String selfCode){
        Iterator<Menu> menuIterator = allMenus.iterator();
        while(menuIterator.hasNext()){
            Menu temp = menuIterator.next();
            //初始化自身节点
            if(temp.getMenuId().equals(selfCode)){
                menuToTree(temp,rootTree);
            }else if(temp.getParentMenu().equals(selfCode)){
                //初始化子节点
                Tree children = new Tree();
                menuToTree(temp,children);
                rootTree.getChildren().add(children);
                //递归
                initTree(children,allMenus,temp.getMenuId());
            }
        }
    }

    /**
     * 部门：南京软件研发中心
     * 功能：将菜单格式化成树
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/28
     */
    private void menuToTree(Menu menu, Tree tree){
        tree.setMenuId(menu.getMenuId());
        tree.setMenuPath(menu.getMenuPath());
        tree.setPid(menu.getParentMenu());
        tree.setMenuName(menu.getMenuName());
        tree.setVersion(menu.getVersion());
    }

    /**
     * 部门：南京软件研发中心
     * 功能：删除菜单
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/8/30
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteMenu(Menu menu) {
        // 删除菜单
        menuDao.deleteMenu(menu);

    }
//
    /**
     * 部门：南京软件研发中心
     * 功能：新增菜单
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/28
     */
    public AppResponse insertMenu(Menu menu) {
        AppResponse appResponse = AppResponse.success("新增成功！");
        // 菜单代码
        String menuId = StringUtil.getCommonCode(2);
        // 根菜单就是菜单代码，其他新建子菜单时，获取父菜单的范围代码+‘3位自增长数’。
        if(null == menu.getParentMenu() || "".equals(menu.getParentMenu())) {
            menu.setParentMenu(GlobalConstant.MENU_ROOT);
        }
        menu.setMenuId(menuId);
        menu.setIsDelete(0);
        //获取可点击角色
        List<String> roles = null;
        if(menu.getRole() != null) {
            roles = Arrays.asList(menu.getRole().split(","));
        }else {
            roles = new ArrayList<String>();
            roles.add("0");
            roles.add("1");
            roles.add("2");
        }
        int count = menuDao.insertMenu(menu, roles);
        if(0 == count) {
            appResponse = AppResponse.bizError("新增失败，请重试！");
        }
        // 新增菜单

        return appResponse;
    }
    /**
     * 部门：南京软件研发中心
     * 功能：修改菜单
     * 描述：略
     * 作成者：朱庆锋
     * 作成时间：2018/11/28
     */
    public AppResponse updateMenu(Menu menu) {
        AppResponse appResponse = AppResponse.success("修改成功！");
        int count = menuDao.updateMenu(menu);
        if(0 == count) {
            appResponse = AppResponse.bizError("修改失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 查询菜单详情
     * @param menuId
     * @return
     * @author feng
     * @date 2020-4-17
     */
    public AppResponse getMenu(String menuId){
        Menu menu = menuDao.getMenu(menuId);
        if (menu == null){
            return AppResponse.notFound("未找到数据");
        }
        return AppResponse.success("查询成功",menu);
    }
//    /**
//     * 部门：南京软件研发中心
//     * 功能：查询用户菜单
//     * 描述：略
//     * 作成者：朱庆锋
//     * 作成时间：2018/8/6
//     */
//    public Tree listUserMenus(String userCode, String authCode) {
//        Menu menu = new Menu();
//        //menu.setAuthCode(authCode);
//        menu.setUserCode(userCode);
//
//        // 如果没有传菜单范围代码，则查询所有的一级菜单
//    /*if (null == menu.getAuthCode() || "".equals(menu.getAuthCode())) {
//        menu.setParentMenuCode(GlobalConstant.MENU_ROOT);
//    }*/
//
//        // 获取所有菜单
//        List<Menu> allMenus = menuDao.listUserMenus(menu);
//
//        Tree rootTree = new Tree();
//        // 处理一级菜单数据
//    /*if (null == menu.getAuthCode() || "".equals(menu.getAuthCode())) {
//        initTree(rootTree, allMenus, menu.getParentMenuCode());
//
//    } else {// 处理一级菜单下的子菜单
//        initUserMenuTree(rootTree, allMenus, menu.getParentMenuCode());
//    }*/
//        initUserMenuTree(rootTree, allMenus, GlobalConstant.MENU_ROOT);
//        return rootTree;
//    }
//    /**
//     * 部门：南京软件研发中心
//     * 功能：初始化用户菜单树
//     * 描述：略
//     * 作成者：朱庆锋
//     * 作成时间：2018/11/28
//     */
//    private void initUserMenuTree(Tree rootTree, List<Menu> allMenus, String selfCode) {
//        Iterator<Menu> menuIterator = allMenus.iterator();
//        while (menuIterator.hasNext()) {
//            Menu tmp = menuIterator.next();
//            // 初始化自身节点
//            if (tmp.getMenuCode().equals(selfCode)) {
//                menuToTree(tmp, rootTree);
//                // 如果是二级节点或者二级节点下的子节点，则追加到nodes
//            } else if (null == selfCode && (tmp.getMenuCode().length() + 3) == tmp.getAuthCode().length()
//                    || tmp.getParentMenuCode().equals(selfCode)) {
//                // 初始化子节点
//                Tree children = new Tree();
//                menuToTree(tmp, children);
//                if (null != children.getId()) {
//                    if (null == rootTree.getChildren()) {
//                        rootTree.setChildren(new ArrayList<Tree>());
//                    }
//                    rootTree.getChildren().add(children);
//                }
//                // 递归
//                initTree(children, allMenus, tmp.getMenuCode());
//            }
//        }
//    }
}
