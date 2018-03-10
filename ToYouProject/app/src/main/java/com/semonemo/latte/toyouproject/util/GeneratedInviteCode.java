package com.semonemo.latte.toyouproject.util;

/**
 * Created by latte on 2018. 3. 8..
 */

public class GeneratedInviteCode {
    public static String getInviteCode(long userId){
        return String.valueOf(userId-600000000);
    }

    public static long getUserIdFromInviteCode(String inviteCode){

        return Long.parseLong(inviteCode)+600000000;
    }
}
