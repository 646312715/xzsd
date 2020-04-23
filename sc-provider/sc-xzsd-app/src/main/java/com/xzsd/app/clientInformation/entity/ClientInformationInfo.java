package com.xzsd.app.clientInformation.entity;

public class ClientInformationInfo {
    /**
     * 用户编号
     */
    private String userId;
    /**
     * 用户邀请码
     */
    private String inviteCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    @Override
    public String toString() {
        return "ClientInformationInfo{" +
                "userId='" + userId + '\'' +
                ", inviteCode='" + inviteCode + '\'' +
                '}';
    }
}

