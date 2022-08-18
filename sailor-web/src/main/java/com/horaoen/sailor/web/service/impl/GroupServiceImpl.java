package com.horaoen.sailor.web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.horaoen.sailor.web.common.enumeration.GroupLevelEnum;
import com.horaoen.sailor.web.dao.GroupDao;
import com.horaoen.sailor.web.dao.UserGroupDao;
import com.horaoen.sailor.web.model.GroupDo;
import com.horaoen.sailor.web.service.GroupService;
import com.horaoen.sailor.web.service.PermissionService;
import com.horaoen.sailor.web.vo.GroupVo;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author horaoen
 */
@Service
public class GroupServiceImpl implements GroupService {
    private final GroupDao groupDao;

    public GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public Long getParticularGroupIdByLevel(GroupLevelEnum level) {
        GroupDo group = this.getParticularGroupByLevel(level);
        return group == null ? null : group.getId();
    }

    @Override
    public GroupDo getParticularGroupByLevel(GroupLevelEnum level) {
        if (GroupLevelEnum.USER.getValue().equals(level.getValue())) {
            return null;
        } else {
            List<GroupDo> groupDos = groupDao.selectGroupByLevel(level);
            return groupDos.get(0);
        }
    }

    @Override
    public List<GroupVo> getUserGroupsByUserId(Long userId) {
        List<GroupDo> groupDos = groupDao.selectUserGroupsByUserId(userId);
        return groupDos.stream().map(groupDo -> {
            GroupVo groupVo = new GroupVo();
            BeanUtil.copyProperties(groupDo, groupVo);
            return groupVo;
        }).collect(Collectors.toList());
    }
}
