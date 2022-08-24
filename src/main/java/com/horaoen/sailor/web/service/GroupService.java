package com.horaoen.sailor.web.service;

import com.horaoen.sailor.web.bo.GroupPermissionBo;
import com.horaoen.sailor.web.common.enumeration.GroupLevelEnum;
import com.horaoen.sailor.web.model.GroupDo;
import com.horaoen.sailor.web.vo.GroupVo;

import java.util.List;

/**
 * @author horaoen
 */
public interface GroupService {

    /**
     * 通过分组级别获取超级管理员分组或游客分组的id
     *
     * @param level GroupLevelEnum 枚举类
     * @return 用户组id
     */
    Long getParticularGroupIdByLevel(GroupLevelEnum level);

    /**
     * 通过分组级别获取超级管理员分组或游客分组
     *
     * @param level GroupLevelEnum 枚举类
     * @return 用户组
     */
    GroupDo getParticularGroupByLevel(GroupLevelEnum level);

    /**
     * 通过分组id获取分组
     *
     * @param userId 用户id
     * @return 分组
     */
    List<GroupVo> getUserGroupsByUserId(Long userId);
    
    /**
     * 获得用户的所有分组id
     *
     * @param userId 用户id
     * @return 所有分组id
     */
    List<Long> getUserGroupIdsByUserId(long userId);

    boolean checkIsRootByUserId(Long id);

    /**
     * 获取所有用户组
     * @return 用户组列表
     */
    List<GroupVo> getAllGroups();

    /**
     * 检查组是否存在
     * @param id 组id
     * @return 是否存在
     */
    boolean checkGroupExistById(Long id);

    /**
     * 删除组
     * @param id 组id
     * @return 是否删除成功
     */
    boolean deleteGroup(Long id);

    /**
     * 更据组id获取组
     * @param id 组id
     * @return 组
     */
    GroupVo getGroupById(Long id);

    /**
     * 获得分组及其权限
     *
     * @param id 分组id
     * @return 分组及权限
     */
    GroupPermissionBo getGroupAndPermissions(Long id);
}
