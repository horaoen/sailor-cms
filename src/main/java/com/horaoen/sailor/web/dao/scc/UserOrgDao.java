package com.horaoen.sailor.web.dao.scc;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author horaoen
 */
@Mapper
public interface UserOrgDao {
    /**
     * 添加
     * @param orgId 组织id
     * @param userId 用户id
     */
    void insert(@Param("orgId") Long orgId, @Param("userId") Long userId);
}
