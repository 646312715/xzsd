package com.xzsd.pc.topOfColumn.entity;

public class TopOfColumnInfo {
    /**
     * 用户名字
     */
    private String userName;
    /**
     * 用户头像图片路径
     */
    private String userImage;
    /**
     * 用户编号
     */
    private String userId;
    /**
     * 用户角色
     */
    private String role;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "TopOfColumnInfo{" +
                "userName='" + userName + '\'' +
                ", userImage='" + userImage + '\'' +
                ", userId='" + userId + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
