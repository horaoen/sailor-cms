package com.horaoen.sailor.web.service.cms.impl;

import com.horaoen.sailor.web.common.enumeration.GroupLevelEnum;
import com.horaoen.sailor.web.dao.cms.UserDao;
import com.horaoen.sailor.web.dao.cms.UserGroupDao;
import com.horaoen.sailor.web.dto.user.RegisterDto;
import com.horaoen.sailor.web.model.cms.PermissionDo;
import com.horaoen.sailor.web.model.cms.UserDo;
import com.horaoen.sailor.web.service.cms.GroupService;
import com.horaoen.sailor.web.service.cms.PermissionService;
import com.horaoen.sailor.web.service.cms.UserService;
import com.horaoen.sailor.web.service.ssc.OrgService;
import com.horaoen.sailor.web.vo.cms.GroupVo;
import com.horaoen.sailor.web.vo.cms.UserInfoForEditVo;
import com.horaoen.sailor.web.vo.cms.UserInfoVo;
import com.horaoen.sailor.web.vo.ssc.OrgVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author horaoen
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final GroupService groupService;
    private final UserGroupDao userGroupDao;
    private final PermissionService permissionService;
    private final OrgService orgService;

    public UserServiceImpl(UserDao userDao,
                           GroupService groupService,
                           UserGroupDao userGroupDao,
                           PermissionService permissionService, OrgService orgService) {
        this.userDao = userDao;
        this.groupService = groupService;
        this.userGroupDao = userGroupDao;
        this.permissionService = permissionService;
        this.orgService = orgService;
    }
  
    @Override
    public boolean checkUserExistById(Long id) {
        int rows = userDao.selectCountById(id);
        return rows > 0;
    }
    
    @Override
    public List<UserDo> getUserByGroupId(Long groupId) {
        Long rootGroupId = groupService.getParticularGroupIdByLevel(GroupLevelEnum.ROOT);
        return userDao.selectUsersByGroupExcludedRoot(groupId, rootGroupId);
    }

    @Override
    public boolean deleteUser(Long userId) {
        return userDao.deleteById(userId) > 0;
    }

    @Override
    public UserDo getUserByUsername(String username) {
        return userDao.selectByUsername(username);
    }

    @Override
    public UserDo getUserByUserId(Long userId) {
        return userDao.getUserByUserId(userId);
    }

    @Override
    public List<PermissionDo> getUserPermissions(long userId) {
        List<Long> userGroupIds = groupService.getUserGroupIdsByUserId(userId);
        if(userGroupIds == null || userGroupIds.isEmpty()) {
            return null;
        }
        return permissionService.getPermissionByGroupIds(userGroupIds);
    }

    @Override
    public UserInfoVo selectByNickname(String nickname) {
        UserDo userDo = userDao.selectByNickname(nickname);
        if(userDo == null) {
            return null;
        }
        List<GroupVo> groups = groupService.getUserGroupsByUserId(userDo.getId());
        OrgVo org = orgService.getOrgByUserId(userDo.getId());
        return new UserInfoVo(userDo, groups.get(0), org);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(RegisterDto dto) {
        UserDo userDo = new UserDo();
        BeanUtils.copyProperties(dto, userDo);
        userDao.insert(userDo);
        dto.getGroupIds().forEach(groupId -> userGroupDao.insert(userDo.getId(), groupId));
        orgService.addOrgUserRelation(dto.getOrgId(), userDo.getId());
    }

    @Override
    public void updateUser(UserDo userDo) {
        userDao.updateUser(userDo);
    }

    @Override
    public UserInfoForEditVo getUserInfoForEdit(Long userId) {
        UserDo userDo = userDao.getUserByUserId(userId);
        List<GroupVo> groups = groupService.getUserGroupsByUserId(userId);
        OrgVo org = orgService.getOrgByUserId(userId);
        
        UserInfoForEditVo userInfo = new UserInfoForEditVo();
        BeanUtils.copyProperties(userDo, userInfo);
        userInfo.setGroupId(groups.get(0).getId());
        userInfo.setOrgId(org.getId());
        return userInfo;
    }

    @Override
    public List<UserInfoForEditVo> getUserByOrgIdAndGroupId(Long orgId, Long groupId) {
        List<UserDo> userDos = userDao.selectUserByOrgIdAndGroupId(orgId, groupId);
        return userDos.stream().map(userDo -> {
            UserInfoForEditVo userInfo = new UserInfoForEditVo();
            BeanUtils.copyProperties(userDo, userInfo);
            userInfo.setOrgId(orgId);
            userInfo.setGroupId(groupId);
            return userInfo;
        }).collect(Collectors.toList());
    }

}
