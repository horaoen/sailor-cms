package com.horaoen.sailor.web.common;

import com.horaoen.sailor.web.model.UserDo;

/**
 * 线程安全的当前登录用户，如果用户为登录，则得到 null
 *
 * @author horaoen
 */
public class LocalUser {

    private static ThreadLocal<UserDo> local = new ThreadLocal<>();

    /**
     * 得到当前登录用户
     *
     * @return user | null
     */
    public static UserDo getLocalUser() {
        return LocalUser.local.get();
    }

    /**
     * 设置登录用户
     *
     * @param user user
     */
    public static void setLocalUser(UserDo user) {
        LocalUser.local.set(user);
    }

    public static <T> T getLocalUser(Class<T> clazz) {
        return (T) local.get();
    }

    /**
     * 清理当前用户
     */
    public static void clearLocalUser() {
        LocalUser.local.remove();
    }
}
