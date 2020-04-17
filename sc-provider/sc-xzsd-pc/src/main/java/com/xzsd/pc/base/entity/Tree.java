package com.xzsd.pc.base.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门：软件开发事业部
 * 描述：略
 * 作成者：印政权
 * 作成时间：2018/3/12
 */
public class Tree {

    private String menuId;
    private String menuName;
    private String menuPath;
    private String version;
    private List<Tree> children = new ArrayList<Tree>();
    private String pid;
    private String code;
    private String type;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
