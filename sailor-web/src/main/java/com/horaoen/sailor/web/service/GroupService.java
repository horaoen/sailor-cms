package com.horaoen.sailor.web.service;

import com.horaoen.sailor.web.common.enumeration.GroupLevelEnum;
import com.horaoen.sailor.web.model.GroupDo;
import com.horaoen.sailor.web.vo.GroupVo;

import java.util.List;

/**
 * @author horaoen
 */
public interface GroupService {

    /**
     * 通过分组级别获取超级管理员分组或游客分组的id
     *
     * @param level GroupLevelEnum 枚举类
     * @return 用户组id
     */
    Long getParticularGroupIdByLevel(GroupLevelEnum level);

    /**
     * 通过分组级别获取超级管理员分组或游客分组
     *
     * @param level GroupLevelEnum 枚举类
     * @return 用户组
     */
    GroupDo getParticularGroupByLevel(GroupLevelEnum level);

    /**
     * 获得用户的所有分组id
     *
     * @param userId 用户id
     * @return 所有分组id
     */
    List<GroupVo> getUserGroupsByUserId(Long userId);
}
