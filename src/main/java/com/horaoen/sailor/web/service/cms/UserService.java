package com.horaoen.sailor.web.service.cms;

import com.horaoen.sailor.web.dto.user.RegisterDto;
import com.horaoen.sailor.web.model.cms.PermissionDo;
import com.horaoen.sailor.web.model.cms.UserDo;
import com.horaoen.sailor.web.vo.cms.UserInfoForEditVo;
import com.horaoen.sailor.web.vo.cms.UserInfoVo;

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


    /**
     * 获得用户所有权限
     *
     * @param userId 用户id
     * @return 权限
     */
    List<PermissionDo> getUserPermissions(long userId);

    /**
     * 更具用户昵称获取信息
     * @param nickname 用户昵称
     * @return 用户信息
     */
    UserInfoVo selectByNickname(String nickname);

    /**
     * 添加用户信息
     * @param dto 用户信息
     */
    void add(RegisterDto dto);

    /**
     * 更新用户信息
     * @param userDo 用户信息
     */
    void updateUser(UserDo userDo);

    /**
     * 获取用于更新的用户信息
     * @param userId 用户id
     * @return 用户信息
     */
    UserInfoForEditVo getUserInfoForEdit(Long userId);

    /**
     * 根据组织和角色筛选用户
     * @param orgId 组织id
     * @param groupId 角色id
     * @return 用户列表
     */
    List<UserInfoForEditVo> getUserByOrgIdAndGroupId(Long orgId, Long groupId);
}
