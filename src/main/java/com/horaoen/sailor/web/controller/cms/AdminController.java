package com.horaoen.sailor.web.controller.cms;

import com.github.pagehelper.PageHelper;
import com.horaoen.sailor.web.bo.cms.ModulePermissionBo;
import com.horaoen.sailor.web.bo.cms.ModulePermissionForSelectBo;
import com.horaoen.sailor.web.common.util.PageUtil;
import com.horaoen.sailor.web.dto.admin.NewGroupDto;
import com.horaoen.sailor.web.dto.admin.ResetPasswordDto;
import com.horaoen.sailor.web.dto.admin.UpdateGroupDto;
import com.horaoen.sailor.web.dto.user.UpdateUserInfoDto;
import com.horaoen.sailor.web.dto.user.RegisterDto;
import com.horaoen.sailor.web.service.cms.AdminService;
import com.horaoen.sailor.web.vo.cms.GroupVo;
import com.horaoen.sailor.web.vo.cms.UserInfoVo;
import com.horaoen.sailor.web.vo.cms.UserInfoForEditVo;
import com.horaoen.sailor.web.vo.message.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * @author horaoen
 */
@RestController
@RequestMapping("/cms/admin")
@Tag(name = "管理员接口")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @GetMapping("/permission")
    @Operation(summary = "角色管理-获取全部权限")
    public UnifyResponseVo<List<ModulePermissionBo>> getAllPermissions() {
        List<ModulePermissionBo> result = adminService.getAllStructuralPermissions();
        return new UnifyResponseVo<>(result);
    }
    
    @GetMapping("/user")
    @Operation(
            summary = "用户管理-管理员查询用户",
            description = "根据分组查询root用户以外的所有用户, 如果groupId不填默认查询所有用户组"
    )
    public PageResponseVo<UserInfoVo> getUsers(
            @RequestParam(name = "group_id", required = false)
            @Min(value = 1, message = "{group.id.positive}") Long groupId,
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") int count,
            @RequestParam(name = "page", required = false, defaultValue = "1")
            @Min(value = 0, message = "{page.number.min}") int page) {
        PageHelper.startPage(page, count);
        List<UserInfoVo> userInfos = adminService.getUserByGroupId(groupId);
        return PageUtil.build(userInfos); 
    }

    @DeleteMapping("/user/{userId}")
    @Operation(summary = "用户管理-删除用户")
    public DeletedVo<?> deleteUser(@PathVariable @Positive(message = "{id.positive}") Long userId) {
        boolean res = adminService.deleteUser(userId);
        if(!res) {
            return new DeletedVo<>(10200);
        }
        return new DeletedVo<>(5);
    }

    @PutMapping("/user/{id}")
    @Operation(summary = "用户管理-修改用户信息")
    public UpdatedVo<?> updateUser(@PathVariable @Positive(message = "{id.positive}") Long id, 
                                   @RequestBody @Validated UpdateUserInfoDto dto) {
        boolean res = adminService.updateUserInfo(id, dto);
        if(!res) {
            return new UpdatedVo<>(10200);
        }
        return new UpdatedVo<>(6);
    }
    
    @GetMapping("/group")
    @Operation(summary = "角色管理-获取所有角色", description = "角色和用户组是同一概念")
    public UnifyResponseVo<List<GroupVo>> getAllGroup() {
        List<GroupVo> groups = adminService.getAllGroups();
        return new UnifyResponseVo<>(groups);
    }

    @DeleteMapping("/group/{id}")
    @Operation(summary = "角色管理-删除角色")
    public DeletedVo<?> deleteGroup(@PathVariable @Positive(message = "{id.positive}") Long id) {
        adminService.deleteGroup(id);
        return new DeletedVo<>(8);
    }

    @GetMapping("/group/{id}")
    @Operation(summary = "角色管理-获取角色权限信息")
    public UnifyResponseVo<List<ModulePermissionForSelectBo>> getGroup(@PathVariable @Positive(message = "{id.positive}") Long id) {
        List<ModulePermissionForSelectBo> group = adminService.getGroup(id);
        return new UnifyResponseVo<>(group);
    }

    @PostMapping("/group")
    @Operation(summary = "角色管理-新增角色")
    public CreatedVo<?> createGroup(@RequestBody @Validated NewGroupDto dto) {
        boolean res = adminService.createGroup(dto);
        if(!res) {
            return new CreatedVo<>(10202);
        }
        return new CreatedVo<>(15);
    }
    
    @PutMapping("/group/{id}")
    @Operation(summary = "角色管理-修改角色")
    public UpdatedVo<?> updateGroup(@PathVariable @Positive(message = "{id.positive}") Long id, 
                                    @RequestBody @Validated UpdateGroupDto dto) {
        boolean res = adminService.updateGroup(id, dto);
        if(!res) {
            return new UpdatedVo<>(10200);
        }
        return new UpdatedVo<>(7);
    }

    @PostMapping("/register")
    @Operation(summary = "用户管理-添加用户")
    public CreatedVo<?> register(@RequestBody @Validated RegisterDto dto) {
        adminService.createUser(dto);
        return new CreatedVo<>(11);
    }
    
    @GetMapping("/user/{userId}")
    @Operation(summary = "用户管理-获取用户信息")
    public UnifyResponseVo<UserInfoForEditVo> getUser(@PathVariable @Positive(message = "{id.positive}") Long userId) {
        UserInfoForEditVo userInfo = adminService.getUserInfoForEdit(userId);
        return new UnifyResponseVo<>(userInfo);
    }
}
