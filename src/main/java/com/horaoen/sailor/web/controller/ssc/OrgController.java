package com.horaoen.sailor.web.controller.ssc;

import com.horaoen.sailor.web.bo.ssc.OrgNodeBo;
import com.horaoen.sailor.web.dto.org.OrgDto;
import com.horaoen.sailor.web.dto.org.TopOrgDto;
import com.horaoen.sailor.web.service.ssc.OrgService;
import com.horaoen.sailor.web.vo.message.CreatedVo;
import com.horaoen.sailor.web.vo.message.DeletedVo;
import com.horaoen.sailor.web.vo.message.UnifyResponseVo;
import com.horaoen.sailor.web.vo.message.UpdatedVo;
import com.horaoen.sailor.web.vo.ssc.OrgVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author horaoen
 */
@RestController
@RequestMapping("/ssc/org")
@Tag(name = "组织架构管理")
public class OrgController {
    private final OrgService orgService;

    public OrgController(OrgService orgService) {
        this.orgService = orgService;
    }
    
    @GetMapping
    @Operation(summary = "获取组织架构")
    public UnifyResponseVo<List<OrgNodeBo>> getAllRegion() {
        return new UnifyResponseVo<>(orgService.getAllRegion());
    }
    
    @PostMapping
    @Operation(summary = "创建一级部门")
    public CreatedVo<?> addTopOrg(@RequestBody @Validated TopOrgDto dto) {
        orgService.addTopOrg(dto);
        return new CreatedVo<>(16);
    }
    
    @GetMapping("{orgId}")
    @Operation(summary = "根据orgId获取子部门")
    public UnifyResponseVo<OrgVo> getOrgByOrgId(@PathVariable @Positive(message = "{id.positive}") Long orgId) {
        return new UnifyResponseVo<>(orgService.getOrgByOrgId(orgId));
    }
    
    @PostMapping("{parentId}")
    @Operation(summary = "根据parentId创建子部门")
    public CreatedVo<?> addSubOrg(@PathVariable @Positive(message = "{id.positive}") Long parentId, 
                                  @RequestBody @Validated OrgDto dto) {
        orgService.addSubOrg(parentId, dto);
        return new CreatedVo<>(17);
    }
    
    @PutMapping("{orgId}")
    @Operation(summary = "根据orgId更新部门")
    public UpdatedVo<?> updateOrg(@PathVariable @Positive(message = "{id.positive}") Long orgId, 
                                  @RequestBody @Validated OrgDto dto) {
        orgService.updateOrg(orgId, dto);
        return new UpdatedVo<>(18);
    }
    
    @DeleteMapping()
    @Operation(summary = "根据organIds批量删除部门", description = "organIds为逗号分隔的字符串")
    public DeletedVo<?> deleteOrg(@RequestParam String organIds) {
        List<Long> ids = Arrays.stream(organIds.split(",")).map(Long::parseLong).collect(Collectors.toList());
        orgService.deleteOrg(ids); 
        return new DeletedVo<>(19);
    }
}
