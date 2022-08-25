package com.horaoen.sailor.web.service;

import com.horaoen.sailor.web.model.PermissionDo;
import com.horaoen.sailor.web.vo.PermissionVo;

import java.util.List;

/**
 * @author horaoen
 */
public interface PermissionService {
    /**
     * 获取全部机构化权限
     * @param mounted 根据mounted获取权限
     * @return 权限数据
     */
    List<PermissionVo> getAllPermissions(boolean mounted);

    
    List<PermissionDo> getPermissionByGroupIds(List<Long> userGroupIds);

    /**
     * 根据groupIds获取权限
     * @param id 组id
     * @return 权限集合
     */
    List<PermissionVo> getPermissionByGroupId(Long id);

    /**
     * 根据id检查权限是否存在
     * @param id 权限id
     * @return 是否存在
     */
    boolean checkPermissionExistById(Long id);
}
