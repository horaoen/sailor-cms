package com.horaoen.sailor.web.dao.cms;

import com.horaoen.sailor.web.model.cms.UserGroupDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author horaoen
 */
@Mapper
public interface UserGroupDao {
    List<UserGroupDo> selectUserByGroupId(Long groupId);

    /**
     * 添加relation
     * @param userId 用户id
     * @param groupId 组id
     */
    void insert(@Param("userId") Long userId, @Param(("groupId")) Long groupId);

    /**
     * 删除relation
     * @param userId 用户id
     */
    void deleteUserGroupsByUserId(Long userId);

    /**
     * 插入relation
     * @param userId 用户id
     * @param groupIds 组id集合
     */
    void insertUserGroups(@Param("userId") Long userId, @Param("groupIds") List<Long> groupIds);
}
