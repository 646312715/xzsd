package com.xzsd.app.managerInformation.entity;

public class ManagerInformationInfo {
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户电话
     */
    private String phone;
    /**
     * 省编号
     */
    private String provinceId;
    /**
     * 市编号
     */
    private String cityId;
    /**
     * 区编号
     */
    private String areaId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvinceId() {
        return provinceId;
    }

    @Override
    public String toString() {
        return "ManagerInformationInfo{" +
                "userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", provinceId='" + provinceId + '\'' +
                ", cityId='" + cityId + '\'' +
                ", areaId='" + areaId + '\'' +
                '}';
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }
}

