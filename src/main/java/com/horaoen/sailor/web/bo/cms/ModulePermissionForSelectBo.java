package com.horaoen.sailor.web.bo.cms;

import com.horaoen.sailor.web.vo.cms.PermissionForSelectVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * @author horaoen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModulePermissionForSelectBo {
    private UUID id;
    private String name;
    private List<PermissionForSelectVo> permissions;
    
    public ModulePermissionForSelectBo(ModulePermissionBo modulePermissionBo, List<PermissionForSelectVo> permissions) {
        this.id = modulePermissionBo.getId();
        this.name = modulePermissionBo.getName();
        this.permissions = permissions;
    }
}
