package com.horaoen.sailor.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
}
