package com.horaoen.sailor.web.dao;

import com.horaoen.sailor.web.common.enumeration.GroupLevelEnum;
import com.horaoen.sailor.web.model.GroupDo;
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
}
