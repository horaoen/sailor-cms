package com.horaoen.sailor.web.service.cms;


/**
 * @author horaoen
 */
public interface UserIdentityService {

    /**
     * 通过用户id更新用户密码
     *
     * @param userId 用户id
     * @param password 密码              
     * @return 用户数据
     */
    boolean changePassword(Long userId, String password);

    /**
     * 通过用户id删除用户
     * @param userId 用户id
     * @return 是否删除成功
     */
    boolean deleteUserIdentity(Long userId);

    /**
     * 验证用户名密码
     * @param id 用户id
     * @param username 用户名
     * @param password 密码
     * @return 是否验证成功
     */
    boolean verifyUsernamePassword(Long id, String username, String password);
}
