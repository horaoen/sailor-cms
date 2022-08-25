package com.horaoen.sailor.web.service.cms.impl;

import com.horaoen.sailor.web.common.enumeration.GroupLevelEnum;
import com.horaoen.sailor.web.dao.cms.UserDao;
import com.horaoen.sailor.web.dao.cms.UserGroupDao;
import com.horaoen.sailor.web.model.cms.PermissionDo;
import com.horaoen.sailor.web.model.cms.UserDo;
import com.horaoen.sailor.web.service.cms.GroupService;
import com.horaoen.sailor.web.service.cms.PermissionService;
import com.horaoen.sailor.web.service.cms.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author horaoen
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final GroupService groupService;
    private final UserGroupDao userGroupDao;
    private final PermissionService permissionService;

    public UserServiceImpl(UserDao userDao,
                           GroupService groupService,
                           UserGroupDao userGroupDao, 
                           PermissionService permissionService) {
        this.userDao = userDao;
        this.groupService = groupService;
        this.userGroupDao = userGroupDao;
        this.permissionService = permissionService;
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

}
