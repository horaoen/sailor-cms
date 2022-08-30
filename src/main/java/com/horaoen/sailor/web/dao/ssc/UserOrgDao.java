package com.horaoen.sailor.web.dao.ssc;

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

    /**
     * 更新
     * @param userId 用户id
     * @param orgId 组织id
     */
    void update(@Param("userId") Long userId, @Param("orgId") Long orgId);
}
