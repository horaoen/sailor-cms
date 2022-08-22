package com.horaoen.sailor.web.service;

import com.horaoen.sailor.web.model.UserDo;

import java.util.List;

/**
 * @author horaoen
 */
public interface UserService {

    /**
     * 通过用户id检查用户是否存在
     * @param userId 用户id
     * @return 是否存在
     */
    boolean checkUserExistById(Long userId);

    /**
     * 通过组id获取root组以外的用户列表
     * @param groupId 组id
     * @return 用户列表
     */
    List<UserDo> getUserByGroupId(Long groupId);

    /**
     * 通过用户id删除用户
     * @param userId 用户id
     * @return 是否删除成功
     */
    boolean deleteUser(Long userId);

    /**
     * 通过用户姓名获取用户
     * @param username 用户姓名
     * @return 
     */
    UserDo getUserByUsername(String username);
    
    /**
     * 通过用户id获取用户
     * @param userId 用户id
     * @return 用户数据
     */
    UserDo getUserByUserId(Long userId);
}
