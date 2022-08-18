package com.horaoen.sailor.web.service.impl;

import com.horaoen.sailor.web.common.enumeration.GroupLevelEnum;
import com.horaoen.sailor.web.dao.UserDao;
import com.horaoen.sailor.web.dao.UserGroupDao;
import com.horaoen.sailor.web.dto.user.RegisterDto;
import com.horaoen.sailor.web.model.UserDo;
import com.horaoen.sailor.web.service.GroupService;
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

    public UserServiceImpl(UserDao userDao, GroupService groupService, UserGroupDao userGroupDao) {
        this.userDao = userDao;
        this.groupService = groupService;
        this.userGroupDao = userGroupDao;
    }

    @Override
    public UserDo createUser(RegisterDto validator) {
        return null;
    }

    @Override
    public UserDo getUserByUsername(String username) {
        return null;
    }

    @Override
    public boolean checkUserExistByUsername(String username) {
        int rows = userDao.selectCountByUsername(username);
        return rows > 0;
    }

    @Override
    public boolean checkUserExistById(Long id) {
        int rows = userDao.selectCountById(id);
        return rows > 0;
    }

    @Override
    public boolean checkUserExistByEmail(String email) {
        int rows = userDao.selectCountByEmail(email);
        return rows > 0;
    }

    @Override
    public List<UserDo> getUserByGroupId(Long groupId) {
        Long rootGroupId = groupService.getParticularGroupIdByLevel(GroupLevelEnum.ROOT);
        return userDao.selectUsersByGroupExcludedRoot(groupId, rootGroupId);
    }
}
