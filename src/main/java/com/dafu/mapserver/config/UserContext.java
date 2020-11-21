package com.dafu.mapserver.config;


import com.dafu.mapserver.domain.SUser;

public class UserContext {
    private static ThreadLocal<SUser> activeUser = new ThreadLocal<>();

    public static SUser getActiveUser() {
        return activeUser.get();
    }

    public static void setActiveUser(SUser user) {
        activeUser.set(user);
    }
}
