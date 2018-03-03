package com.semonemo.latte.toyouproject.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by latte on 2018. 3. 4..
 */

public class LetterDto implements Serializable {
    private long letterId;
    private String letterContent;
    private String fromUser;
    private String toUser;
    private Date regDate;

    @Override
    public String toString() {
        return "LetterDto{" +
                "letterId=" + letterId +
                ", letterContent='" + letterContent + '\'' +
                ", fromUser='" + fromUser + '\'' +
                ", toUser='" + toUser + '\'' +
                ", regDate=" + regDate +
                '}';
    }

    public LetterDto(long letterId, String letterContent, String fromUser, String toUser, Date regDate) {
        this.letterId = letterId;
        this.letterContent = letterContent;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.regDate = regDate;
    }

    public long getLetterId() {
        return letterId;
    }

    public void setLetterId(long letterId) {
        this.letterId = letterId;
    }

    public String getLetterContent() {
        return letterContent;
    }

    public void setLetterContent(String letterContent) {
        this.letterContent = letterContent;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}
