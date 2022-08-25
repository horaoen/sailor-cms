package com.horaoen.sailor.web.dao;

import com.horaoen.sailor.web.model.PermissionDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author horaoen
 */
@Mapper
public interface PermissionDao {
    /**
     * 获取
     * @param mounted 过滤条件
     * @return
     */
    List<PermissionDo> getAllPermissions(boolean mounted);

    /**
     * 根据groupId获取权限
     * @param id 组id
     * @return 权限集合
     */
    List<PermissionDo> selectPermissionsByGroupId(Long id);


    /**
     * 根据id获取权限
     * @param id 权限id
     * @return 权限
     */
    PermissionDo selectPermissionById(Long id);
}
