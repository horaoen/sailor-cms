package com.horaoen.sailor.web.service.cms;

import com.horaoen.sailor.web.bo.cms.ModulePermissionBo;
import com.horaoen.sailor.web.bo.cms.ModulePermissionForSelectBo;
import com.horaoen.sailor.web.dto.admin.NewGroupDto;
import com.horaoen.sailor.web.dto.admin.ResetPasswordDto;
import com.horaoen.sailor.web.dto.admin.UpdateGroupDto;
import com.horaoen.sailor.web.dto.user.UpdateUserInfoDto;
import com.horaoen.sailor.web.dto.user.RegisterDto;
import com.horaoen.sailor.web.vo.cms.GroupVo;
import com.horaoen.sailor.web.vo.cms.PermissionVo;
import com.horaoen.sailor.web.vo.cms.UserInfoForEditVo;
import com.horaoen.sailor.web.vo.cms.UserInfoVo;

import java.util.List;

/**
 * @author horaoen
 */
public interface AdminService {

    /**
     * 通过分组id分页获取含有用户组信息的用户数据
     *
     * @param groupId 分组id
     * @return 用户数据
     */
    List<UserInfoVo> getUserByGroupId(Long groupId);

    /**
     * 获取全部机构化权限
     *
     * @return 权限数据
     */
    List<ModulePermissionBo> getAllStructuralPermissions();

    /**
     * 获取全部权限列表
     * 
     * @return 权限列表
     */
    List<PermissionVo> getAllMountedPermissions();

    /**
     * 重置用户密码
     *
     * @param dto 重置密码数据
     * @param userId       用户id
     * @return 是否重置成功
     */
    boolean changeUserPassword(Long userId, ResetPasswordDto dto);

    /**
     * 检查用户id是否存在
     * @param userId 用户id
     */
    void checkUserExistById(Long userId);

    /**
     * 删除用户
     *
     * @param userId 用户id
     * @return 是否删除成功
     */
    boolean deleteUser(Long userId);

    /**
     * 管理员更新用户信息（用户组）
     * @param id 用户id
     * @param dto 更新数据
     * @return 是否更新成功
     */
    boolean updateUserInfo(Long id, UpdateUserInfoDto dto);

    /**
     * 获取所有用户组
     * @return 用户组列表
     */
    List<GroupVo> getAllGroups();

    /**
     * 删除用户组
     * @param id 用户组id
     * @return 是否删除成功
     */
    boolean deleteGroup(Long id);

    /**
     * 获得分组数据
     * @param id 分组id
     * @return 分组数据
     */
    List<ModulePermissionForSelectBo> getGroup(Long id);

    /**
     * 创建分组数据
     * @param dto 分组数据
     * @return 是否创建成功
     */
    boolean createGroup(NewGroupDto dto);

    /**
     * 更新分组信息
     * @param id 分组id
     * @param dto 更新数据
     * @return 是否更新成功
     */
    boolean updateGroup(Long id, UpdateGroupDto dto);

    /**
     * 添加用户
     * @param dto 用户数据
     */
    void createUser(RegisterDto dto);

    /**
     * 根据userId获取用于编辑的用户信息
     * @param userId 用户id
     * @return 用户信息
     */
    UserInfoForEditVo getUserInfoForEdit(Long userId);
}
