package com.horaoen.sailor.web.service.impl;

import com.horaoen.sailor.web.model.UserDo;
import com.horaoen.sailor.web.service.AdminService;
import com.horaoen.sailor.web.service.GroupService;
import com.horaoen.sailor.web.service.UserService;
import com.horaoen.sailor.web.vo.GroupVo;
import com.horaoen.sailor.web.vo.UserInfoVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author horaoen
 */
@Repository
public class AdminServiceImpl implements AdminService {

    private final UserService userService;
    private final GroupService groupService;

    public AdminServiceImpl(UserService userService, GroupService groupService) {
        this.userService = userService;
        this.groupService = groupService;
    }

    @Override
    public List<UserInfoVo> getUserByGroupId(Long groupId) {
        List<UserDo> userDos = userService.getUserByGroupId(groupId);
        return userDos.stream().map(userDo -> {
            List<GroupVo> groups = groupService.getUserGroupsByUserId(userDo.getId());
            return new UserInfoVo(userDo, groups);
        }).collect(Collectors.toList());
    }
}
