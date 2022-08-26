package com.horaoen.sailor.web.controller.scc;

import com.horaoen.sailor.web.bo.scc.OrgNodeBo;
import com.horaoen.sailor.web.dto.org.TopOrgDto;
import com.horaoen.sailor.web.service.scc.OrgService;
import com.horaoen.sailor.web.vo.message.UnifyResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author horaoen
 */
@RestController
@RequestMapping("/scc/org")
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
    
//    @PostMapping
//    @Operation(summary = "创建一级部门")
//    public UnifyResponseVo<?> addTopOrg(TopOrgDto dto) {
//        boolean addResult = orgService.addTopOrg(dto);
//        
//    }
    
    
}
