package com.horaoen.sailor.web.service.impl;

import com.horaoen.sailor.web.common.enumeration.GroupLevelEnum;
import com.horaoen.sailor.web.dao.UserDao;
import com.horaoen.sailor.web.dao.UserGroupDao;
import com.horaoen.sailor.web.dto.admin.ResetPasswordDto;
import com.horaoen.sailor.web.dto.user.RegisterDto;
import com.horaoen.sailor.web.model.UserDo;
import com.horaoen.sailor.web.service.GroupService;
import com.horaoen.sailor.web.service.UserIdentityService;
import com.horaoen.sailor.web.service.UserService;
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

    public UserServiceImpl(UserDao userDao, 
                           GroupService groupService, 
                           UserGroupDao userGroupDao) {
        this.userDao = userDao;
        this.groupService = groupService;
        this.userGroupDao = userGroupDao;
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

}
