package com.horaoen.sailor.web.controller.cms;

import com.github.pagehelper.PageHelper;
import com.horaoen.sailor.web.common.util.PageUtil;
import com.horaoen.sailor.web.service.AdminService;
import com.horaoen.sailor.web.vo.PageResponseVo;
import com.horaoen.sailor.web.vo.UserInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

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

    @GetMapping("/users")
    @Operation(
            description = "管理员查询用户",
            summary = "根据分组查询root用户以外的所有用户, 如果groupId不填默认查询所有用户组"
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
    
    
    
    
    
}
