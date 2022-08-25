package com.horaoen.sailor.web.dao.cms;

import com.horaoen.sailor.web.common.enumeration.GroupLevelEnum;
import com.horaoen.sailor.web.model.cms.GroupDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author horaoen
 */
@Mapper
public interface GroupDao {
    /**
     * 根据分组级别获取所有分组数据
     *
     * @param level 分组级别
     * @return 分组数据
     */
    List<GroupDo> selectGroupByLevel(GroupLevelEnum level);

    /**
     * 根据用户id查询用户组信息
     *
     * @param userId 组织id
     * @return 用户的组集合
     */
    List<GroupDo> selectUserGroupsByUserId(Long userId);

    /**
     * 获取所有用户组信息
     * @return 用户组集合
     */ 
    List<GroupDo> selectAllGroups();

    /**
     * 根据组id查询组信息
     * @param id 组id
     * @return 组信息
     */
    GroupDo selectGroupById(Long id);

    /**
     * 根据id删除组信息
     * @param id 组id
     * @return 删除结果
     */
    int deleteGroup(Long id);

    /**
     * 插入分组信息
     * @param groupName 组名称
     * @param groupInfo 分组描述
     * @return 插入结果
     */
    Long insertGroup(@Param("groupName") String groupName, @Param("groupInfo") String groupInfo);

    /**
     * 通过分组名获取分组
     * @param name 分组名      
     * @return 分组信息
     */
    List<GroupDo> selectGroupByName(String name);

    /**
     * 更新分组信息
     * @param groupDo 分组信息
     * @return 更新结果
     */
    int updateGroup(GroupDo groupDo);
}
