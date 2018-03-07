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

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", profileImagePath='" + profileImagePath + '\'' +
                ", inviteCode='" + inviteCode + '\'' +
                '}';
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

    public UserDto(long id, String name, String email, String profileImagePath, String inviteCode) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profileImagePath = profileImagePath;
        this.inviteCode = inviteCode;
    }
}
