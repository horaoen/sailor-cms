package com.horaoen.sailor.web.dao.cms;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author horaoen
 */
@Mapper
public interface GroupPermissionDao {
    /**
     * 插入组和权限的关系
     * @param groupId 组id
     * @param permissionId 权限id
     */
    void insert(@Param("groupId") Long groupId, @Param("permissionId") Long permissionId);

    /**
     * 根据组id获取权限id列表1
     * @param id 组id
     * @return 权限id列表
     */
    List<Long> selectByGroupId(Long id);


    /**
     * 根据组id删除组和权限的关系
     * @param id 组id
     * @return 删除结果
     */
    int deleteByGroupId(Long id);
}
