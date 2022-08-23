package com.horaoen.sailor.web.dao;

import com.horaoen.sailor.web.model.PermissionDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author horaoen
 */
@Mapper
public interface PermissionDao {
    /**获取
     * @param mounted 过滤条件
     * @return
     */
    List<PermissionDo> getAllPermissions(boolean mounted);
}
