package com.horaoen.sailor.web.controller.cms;

import com.github.pagehelper.PageHelper;
import com.horaoen.sailor.web.common.util.PageUtil;
import com.horaoen.sailor.web.dto.admin.ResetPasswordDto;
import com.horaoen.sailor.web.dto.admin.UpdateUserInfoDto;
import com.horaoen.sailor.web.service.AdminService;
import com.horaoen.sailor.web.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Map;

/**
 * @author horaoen
 */
@RestController
@RequestMapping("/cms/admin")
@Tag(name = "admin", description = "管理员")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @GetMapping("/permission")
    @Operation(summary = "获取全部权限")
    public UnifyResponseVo<Map<String, List<PermissionVo>>> getAllPermissions() {
        Map<String, List<PermissionVo>> result = adminService.getAllStructuralPermissions();
        return new UnifyResponseVo<>(result);
    }
    
    @GetMapping("/users")
    @Operation(
            summary = "管理员查询用户",
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

    @PutMapping("/user/{id}/password")
    @Operation(summary = "修改用户密码")
    public UpdatedVo<?> changeUserPassword(
            @PathVariable @Positive(message = "{id.positive}") Long id, 
            @RequestBody @Validated ResetPasswordDto dto) {
        boolean res = adminService.changeUserPassword(id, dto);
        if(!res) {
            return new UpdatedVo<>(10011);
        }
        return new UpdatedVo<>(4);
    }

    @DeleteMapping("/user/{userId}")
    @Operation(summary = "删除用户")
    public DeletedVo<?> deleteUser(@PathVariable @Positive(message = "{id.positive}") Long userId) {
        boolean res = adminService.deleteUser(userId);
        if(!res) {
            return new DeletedVo<>(10200);
        }
        return new DeletedVo<>(5);
    }

    @PutMapping("/user/{id}")
    public UpdatedVo<?> updateUser(@PathVariable @Positive(message = "{id.positive}") Long id, 
                                   @RequestBody @Validated UpdateUserInfoDto dto) {
        boolean res = adminService.updateUserInfo(id, dto);
        if(!res) {
            return new UpdatedVo<>(10200);
        }
        return new UpdatedVo<>(6);
    }
    
    @GetMapping("/group")
    @Operation(summary = "获取所有角色", description = "角色和用户组是同一概念")
    public UnifyResponseVo<List<GroupVo>> getAllGroup() {
        List<GroupVo> groups = adminService.getAllGroups();
        return new UnifyResponseVo<>(groups);
    }

    @DeleteMapping("/group/{id}")
    @Operation(summary = "删除角色")
    public DeletedVo<?> deleteGroup(@PathVariable @Positive(message = "{id.positive}") Long id) {
        adminService.deleteGroup(id);
        return new DeletedVo<>(8);
    }
    
    
    
    
    
    
    
    
    
}
