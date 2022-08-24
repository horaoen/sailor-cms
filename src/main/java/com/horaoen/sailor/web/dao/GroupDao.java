package com.horaoen.sailor.web.dao;

import com.horaoen.sailor.web.common.enumeration.GroupLevelEnum;
import com.horaoen.sailor.web.model.GroupDo;
import com.horaoen.sailor.web.vo.GroupVo;
import org.apache.ibatis.annotations.Mapper;

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
}
