package com.horaoen.sailor.web.service.cms.impl;

import cn.hutool.core.bean.BeanUtil;
import com.horaoen.sailor.web.bo.cms.GroupPermissionBo;
import com.horaoen.sailor.web.common.enumeration.GroupLevelEnum;
import com.horaoen.sailor.web.dao.cms.GroupDao;
import com.horaoen.sailor.web.model.cms.GroupDo;
import com.horaoen.sailor.web.service.cms.GroupService;
import com.horaoen.sailor.web.service.cms.PermissionService;
import com.horaoen.sailor.web.vo.cms.GroupVo;
import com.horaoen.sailor.web.vo.cms.PermissionVo;
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
    private final PermissionService permissionService;

    public GroupServiceImpl(GroupDao groupDao, PermissionService permissionService) {
        this.groupDao = groupDao;
        this.permissionService = permissionService;
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
    public List<Long> getUserGroupIdsByUserId(long userId) {
        List<GroupDo> groupDos = groupDao.selectUserGroupsByUserId(userId);
        return groupDos.stream().map(GroupDo::getId).collect(Collectors.toList());
    }

    @Override
    public boolean checkIsRootByUserId(Long id) {
        return false;
    }

    @Override
    public List<GroupVo> getAllGroups() {
        List<GroupDo> groupDos = groupDao.selectAllGroups();
        return groupDos.stream()
                .filter(groupDo -> !groupDo.getLevel().equals(GroupLevelEnum.ROOT.getValue()))
                .map(groupDo -> {
                    val groupVo = new GroupVo();
                    BeanUtil.copyProperties(groupDo, groupVo);
                    return groupVo;
                })
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkGroupExistById(Long id) {
        return groupDao.selectGroupById(id) != null;
    }

    @Override
    public boolean deleteGroup(Long id) {
        return groupDao.deleteGroup(id) > 0;
    }

    @Override
    public GroupVo getGroupById(Long id) {
        GroupDo groupDo = groupDao.selectGroupById(id);
        GroupVo groupVo = new GroupVo();
        BeanUtil.copyProperties(groupDo, groupVo);
        return groupVo;
    }

    @Override
    public GroupPermissionBo getGroupAndPermissions(Long id) {
        GroupDo groupDo = groupDao.selectGroupById(id);
        List<PermissionVo> permissions = permissionService.getPermissionByGroupId(groupDo.getId());
        return new GroupPermissionBo(groupDo, permissions);
    }

    @Override
    public boolean checkGroupExistByName(String name) {
        List<GroupDo> groupDos = groupDao.selectGroupByName(name);
        return groupDos.size() > 0;
    }

    @Override
    public Long add(GroupDo groupDo) {
        return groupDao.insertGroup(groupDo.getName(), groupDo.getInfo());
    }

    @Override
    public boolean update(GroupDo groupDo) {
        return groupDao.updateGroup(groupDo) > 0;
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
