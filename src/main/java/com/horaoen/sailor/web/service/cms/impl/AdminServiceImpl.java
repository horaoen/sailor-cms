package com.horaoen.sailor.web.service.cms.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.util.StringUtil;
import com.horaoen.sailor.autoconfigure.exception.ForbiddenException;
import com.horaoen.sailor.autoconfigure.exception.NotFoundException;
import com.horaoen.sailor.web.bo.cms.ModulePermissionBo;
import com.horaoen.sailor.web.bo.cms.ModulePermissionForSelectBo;
import com.horaoen.sailor.web.common.enumeration.GroupLevelEnum;
import com.horaoen.sailor.web.dao.cms.GroupPermissionDao;
import com.horaoen.sailor.web.dto.admin.NewGroupDto;
import com.horaoen.sailor.web.dto.admin.ResetPasswordDto;
import com.horaoen.sailor.web.dto.admin.UpdateGroupDto;
import com.horaoen.sailor.web.dto.admin.UpdateUserInfoDto;
import com.horaoen.sailor.web.dto.user.RegisterDto;
import com.horaoen.sailor.web.model.cms.GroupDo;
import com.horaoen.sailor.web.model.cms.UserDo;
import com.horaoen.sailor.web.service.cms.*;
import com.horaoen.sailor.web.service.scc.OrgService;
import com.horaoen.sailor.web.vo.cms.GroupVo;
import com.horaoen.sailor.web.vo.cms.PermissionForSelectVo;
import com.horaoen.sailor.web.vo.cms.PermissionVo;
import com.horaoen.sailor.web.vo.cms.UserInfoVo;
import com.horaoen.sailor.web.vo.scc.OrgVo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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
    private final OrgService orgService;
    private final GroupPermissionDao groupPermissionDao;

    public AdminServiceImpl(UserService userService,
                            GroupService groupService,
                            PermissionService permissionService,
                            UserIdentityService userIdentityService, OrgService orgService, GroupPermissionDao groupPermissionDao) {
        this.userService = userService;
        this.groupService = groupService;
        this.permissionService = permissionService;
        this.userIdentityService = userIdentityService;
        this.orgService = orgService;
        this.groupPermissionDao = groupPermissionDao;
    }

    @Override
    public List<UserInfoVo> getUserByGroupId(Long groupId) {
        List<UserDo> userDos = userService.getUserByGroupId(groupId);
        return userDos.stream().map(userDo -> {
            List<GroupVo> groups = groupService.getUserGroupsByUserId(userDo.getId());
            OrgVo orgVo = orgService.getOrgByUserId(userDo.getId());
            return new UserInfoVo(userDo, groups.get(0), orgVo);
        }).collect(Collectors.toList());
    }

    @Override
    public List<ModulePermissionBo> getAllStructuralPermissions() {
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
        
        List<ModulePermissionBo> result = new ArrayList<>(0);
        permissions.forEach((module, permissionArray) -> {
            ModulePermissionBo modulePermissionBo = new ModulePermissionBo(UUID.randomUUID(), module, permissionArray);
            result.add(modulePermissionBo);
        });
        return result;
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
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUserInfo(Long id, UpdateUserInfoDto dto) {
        //校验
        throwUserNotExitById(id);
        UserDo userDo = userService.getUserByUserId(id);
        if(!userDo.getNickname().equals(dto.getNickname())) {
            throwUserExistByNickname(dto.getNickname());
        }
        dto.getGroupIds().forEach(this::throwGroupNotExistById);
        throwOrgNotExistByOrgId(dto.getOrgId());

        //禁止修改成root用户组
        Long rootGroupId = groupService.getParticularGroupIdByLevel(GroupLevelEnum.ROOT);
        boolean anyMatch = dto.getGroupIds().stream().anyMatch(it -> it.equals(rootGroupId));
        if (anyMatch) {
            throw new ForbiddenException(10073);
        }
        
        //更新用户基本信息
        UserDo userdo = new UserDo();
        BeanUtil.copyProperties(dto, userdo);
        userdo.setId(id);
        userService.updateUser(userdo);
        
        //更新部门信息
        orgService.updateUserOrg(id, dto.getOrgId());
        
        //更改组信息
        List<Long> groupIds = dto.getGroupIds();
        groupService.updateUserGroups(id, groupIds);
        

        //更改密码
        if(StringUtil.isNotEmpty(dto.getNewPassword()) && dto.getNewPassword().equals(dto.getConfirmPassword())) {
            userIdentityService.changePassword(id, dto.getNewPassword());
        }
        
        return true;
    }

    private void throwUserNotExitById(Long id) {
    }

    @Override
    public List<GroupVo> getAllGroups() {
        List<GroupVo> allGroups = groupService.getAllGroups();
        return allGroups;
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

    @Override
    public List<ModulePermissionForSelectBo> getGroup(Long id) {
        throwGroupNotExistById(id);
        //结构化的系统权限
        List<ModulePermissionBo> allStructuralPermissions = getAllStructuralPermissions();
        
        //用于返回的供选择的用户组权限
        List<ModulePermissionForSelectBo> result = new ArrayList<>(0);
        
        List<PermissionVo> permissions = groupService.getGroupAndPermissions(id).getPermissions();
        //遍历每一个module
        allStructuralPermissions.forEach(modulePermissionBo -> {
            //替换每一个module的list
            List<PermissionForSelectVo> permissionForSelectVos = new ArrayList<>(0);
            //遍历module下的每一个permission
            modulePermissionBo.getPermissions().forEach(permissionVo -> {
                //如果当前permission在permissions中存在，则设置为true，否则设置为false
                boolean exist = permissions.stream().anyMatch(it -> it.getId().equals(permissionVo.getId()));
                PermissionForSelectVo permissionForSelectVo = new PermissionForSelectVo(permissionVo, exist);
                permissionForSelectVos.add(permissionForSelectVo);
            });
            result.add(new ModulePermissionForSelectBo(modulePermissionBo, permissionForSelectVos));
        });
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createGroup(NewGroupDto dto) {
        throwGroupNameExist(dto.getName());
        GroupDo groupDo = GroupDo.builder().name(dto.getName()).info(dto.getInfo()).build();
        groupService.add(groupDo);
        //检验permissions是否存在
        dto.getPermissions().forEach(this::throwPermissionNotExistById);
        if(dto.getPermissions() != null && !dto.getPermissions().isEmpty()) {
            dto.getPermissions().forEach(permissionId -> groupPermissionDao.insert(groupDo.getId(), permissionId));
        } else {
            return false;
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateGroup(Long id, UpdateGroupDto dto) {
        throwGroupNotExistById(id);
        GroupVo groupById = groupService.getGroupById(id);
        if(!groupById.getName().equals(dto.getName())) {
            throwGroupNameExist(dto.getName());
        }
        GroupDo groupDo = new GroupDo();
        BeanUtil.copyProperties(dto, groupDo);
        groupDo.setId(id);
        List<Long> permissions = dto.getPermissions();
        permissions.forEach(this::throwPermissionNotExistById);
        groupService.update(groupDo);
        groupPermissionDao.deleteByGroupId(id);
        if(!permissions.isEmpty()) {
            permissions.forEach(permissionId -> groupPermissionDao.insert(id, permissionId));
        } 
        return true;
    }

    private void throwGroupNameExist(String name) {
        boolean exist = groupService.checkGroupExistByName(name);
        if (exist) {
            throw new ForbiddenException(10072);
        }
    }

    private void throwGroupNotExistById(Long id) {
        boolean exist = groupService.checkGroupExistById(id);
        if (!exist) {
            throw new NotFoundException(10024);
        }
    }
    
    private void throwPermissionNotExistById(Long id) {
        boolean exist = permissionService.checkPermissionExistById(id);
        if (!exist) {
            throw new NotFoundException(10201);
        }
    }
    

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createUser(RegisterDto dto) {
        throwUserExistByNickname(dto.getUsername());
        throwOrgNotExistByOrgId(dto.getOrgId());
        dto.getGroupIds().forEach(this::throwGroupNotExistById);
        userService.add(dto);
    }


    private void throwOrgNotExistByOrgId(Long orgId) {
        if(!orgService.checkOrgExistById(orgId)) {
            throw new ForbiddenException("组织不存在");
        }
    }

    private void throwUserExistByNickname(String nickname) {
        UserInfoVo user = userService.selectByNickname(nickname);
        if(user != null) {
            throw new ForbiddenException(10071);
        }
    }

}
