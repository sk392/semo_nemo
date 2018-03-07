package com.semonemo.latte.toyouproject.view.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by latte on 2018. 3. 5..
 */

public class SignupResult {

    private String userName ;

    private String userProfileImageUrl;

    private long userId;

    private long userInviteCode;

    @Override
    public String toString() {
        return "SignupResult{" +
                "userName='" + userName + '\'' +
                ", userProfileImageUrl='" + userProfileImageUrl + '\'' +
                ", userId=" + userId +
                ", userInviteCode=" + userInviteCode +
                '}';
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserProfileImageUrl() {
        return userProfileImageUrl;
    }

    public void setUserProfileImageUrl(String userProfileImageUrl) {
        this.userProfileImageUrl = userProfileImageUrl;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserInviteCode() {
        return userInviteCode;
    }

    public void setUserInviteCode(long userInviteCode) {
        this.userInviteCode = userInviteCode;
    }
}
