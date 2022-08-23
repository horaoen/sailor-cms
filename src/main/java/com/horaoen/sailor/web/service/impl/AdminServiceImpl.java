package com.horaoen.sailor.web.service.impl;

import com.horaoen.sailor.autoconfigure.exception.ForbiddenException;
import com.horaoen.sailor.autoconfigure.exception.NotFoundException;
import com.horaoen.sailor.web.common.enumeration.GroupLevelEnum;
import com.horaoen.sailor.web.dto.admin.ResetPasswordDto;
import com.horaoen.sailor.web.dto.admin.UpdateUserInfoDto;
import com.horaoen.sailor.web.model.UserDo;
import com.horaoen.sailor.web.service.*;
import com.horaoen.sailor.web.vo.GroupVo;
import com.horaoen.sailor.web.vo.PermissionVo;
import com.horaoen.sailor.web.vo.UserInfoVo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author horaoen
 */
@Repository
public class AdminServiceImpl implements AdminService {

    private final UserService userService;
    private final GroupService groupService;
    private final PermissionService permissionService;
    private final UserIdentityService userIdentityService;

    public AdminServiceImpl(UserService userService, 
                            GroupService groupService, 
                            PermissionService permissionService, 
                            UserIdentityService userIdentityService) {
        this.userService = userService;
        this.groupService = groupService;
        this.permissionService = permissionService;
        this.userIdentityService = userIdentityService;
    }

    @Override
    public List<UserInfoVo> getUserByGroupId(Long groupId) {
        List<UserDo> userDos = userService.getUserByGroupId(groupId);
        return userDos.stream().map(userDo -> {
            List<GroupVo> groups = groupService.getUserGroupsByUserId(userDo.getId());
            return new UserInfoVo(userDo, groups);
        }).collect(Collectors.toList());
    }

    @Override
    public Map<String, List<PermissionVo>> getAllStructuralPermissions() {
        List<PermissionVo> allPermissions = getAllMountedPermissions();
        Map<String, List<PermissionVo>> permissions = new HashMap<>(0);
        allPermissions.forEach(permissionVo -> {
            if (permissions.containsKey(permissionVo.getModule())) {
                permissions.get(permissionVo.getModule()).add(permissionVo);
            } else {
                List<PermissionVo> permissionArray = new ArrayList<>(0);
                permissionArray.add(permissionVo);
                permissions.put(permissionVo.getModule(), permissionArray);
            }
        });
        return permissions;
    }
    

    @Override
    public List<PermissionVo> getAllMountedPermissions() {
        return permissionService.getAllPermissions(true);
    }

    @Override
    public boolean changeUserPassword(Long userId, ResetPasswordDto dto) {
        checkUserExistById(userId);
        return userIdentityService.changePassword(userId, dto.getNewPassword());
    }

    @Override
    public void checkUserExistById(Long userId) {
        boolean exist = userService.checkUserExistById(userId);
        if(!exist) {
            throw new NotFoundException(10021);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteUser(Long userId) {
        checkUserExistById(userId);
        boolean userRemoved = userService.deleteUser(userId);
        boolean userIdentityRemoved = userIdentityService.deleteUserIdentity(userId);
        return userRemoved && userIdentityRemoved;
    }

    @Override
    public boolean updateUserInfo(Long id, UpdateUserInfoDto dto) {
//        List<Long> groupIds = dto.getGroupIds();
//        if(groupIds == null || groupIds.isEmpty()) {
//            return false;
//        }
//        
//        Long rootGroupId = groupService.getParticularGroupIdByLevel(GroupLevelEnum.ROOT);
//        boolean anyMatch = groupIds.stream().anyMatch(it -> it.equals(rootGroupId));
//        if (anyMatch) {
//            throw new ForbiddenException(10073);
//        }
//        List<Long> existGroupIds = groupService.getUserGroupIdsByUserId(id);
//        // 删除existGroupIds有，而groupIds没有的
//        List<Long> deleteIds = existGroupIds.stream().filter(it -> !groupIds.contains(it)).collect(Collectors.toList());
//        // 添加newGroupIds有，而existGroupIds没有的
//        List<Long> addIds = groupIds.stream().filter(it -> !existGroupIds.contains(it)).collect(Collectors.toList());
//        return groupService.deleteUserGroupRelations(id, deleteIds) && groupService.addUserGroupRelations(id, addIds);
            return true;
    }

    @Override
    public List<GroupVo> getAllGroups() {
        return groupService.getAllGroups();
    }

    @Override
    public boolean deleteGroup(Long id) {
        Long rootGroupId = groupService.getParticularGroupIdByLevel(GroupLevelEnum.ROOT);
        Long guestGroupId = groupService.getParticularGroupIdByLevel(GroupLevelEnum.GUEST);
        if (id.equals(rootGroupId)) {
            throw new ForbiddenException(10074);
        }
        if (id.equals(guestGroupId)) {
            throw new ForbiddenException(10075);
        }
        throwGroupNotExistById(id);
        return groupService.deleteGroup(id);
    }

    private void throwGroupNotExistById(Long id) {
        boolean exist = groupService.checkGroupExistById(id);
        if (!exist) {
            throw new NotFoundException(10024);
        }
    }


}
