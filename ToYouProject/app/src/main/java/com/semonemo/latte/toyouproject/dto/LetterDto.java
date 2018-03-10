package com.semonemo.latte.toyouproject.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by latte on 2018. 3. 4..
 */

public class LetterDto implements Serializable {
    private long letterId;
    private String letterContent;
    private long fromUserId;
    private String fromUserName;
    private long toUserId;
    private String toUserName;
    private Date regDate;
    private int delayDay;

    @Override
    public String toString() {
        return "LetterDto{" +
                "letterId=" + letterId +
                ", letterContent='" + letterContent + '\'' +
                ", fromUserId=" + fromUserId +
                ", fromUserName='" + fromUserName + '\'' +
                ", toUserId=" + toUserId +
                ", toUserName='" + toUserName + '\'' +
                ", regDate=" + regDate +
                ", delayDay=" + delayDay +
                '}';
    }

    public LetterDto(long letterId, String letterContent, long fromUserId, String fromUserName, long toUserId, String toUserName, Date regDate, int delayDay) {
        this.letterId = letterId;
        this.letterContent = letterContent;
        this.fromUserId = fromUserId;
        this.fromUserName = fromUserName;
        this.toUserId = toUserId;
        this.toUserName = toUserName;
        this.regDate = regDate;
        this.delayDay = delayDay;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public LetterDto(){

    };

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

    public long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public long getToUserId() {
        return toUserId;
    }

    public void setToUserId(long toUserId) {
        this.toUserId = toUserId;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public int getDelayDay() {
        return delayDay;
    }

    public void setDelayDay(int delayDay) {
        this.delayDay = delayDay;
    }
}
