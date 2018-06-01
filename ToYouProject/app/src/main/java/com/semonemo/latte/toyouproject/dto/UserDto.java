package com.semonemo.latte.toyouproject.dto;

import java.io.Serializable;

/**
 * Created by latte on 2018. 3. 1..
 */

public class UserDto implements Serializable {
    private long id;
    private String name;
    private String email;
    private String profileImagePath;
    private String inviteCode;
    private String targetCode;
    private String firebaseToken;

    //firebase datasnapshot 에서 해당 객체를 생성할 때 빈생성자로 생성한  후에 셋터를 통해서 값을 넣는다
    public UserDto() {
    }
    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", profileImagePath='" + profileImagePath + '\'' +
                ", inviteCode='" + inviteCode + '\'' +
                ", targetCode='" + targetCode + '\'' +
                '}';
    }

    public String getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode;
    }

    public UserDto(long id, String name, String email, String profileImagePath, String inviteCode) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profileImagePath = profileImagePath;
        this.inviteCode = inviteCode;
        this.firebaseToken = "";
        this.targetCode ="0";
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

}
