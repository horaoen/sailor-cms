package com.horaoen.sailor.web.bo.cms;

import com.horaoen.sailor.web.vo.cms.PermissionVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * @author horaoen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModulePermissionBo {
    private UUID id;
    private String name;
    private List<PermissionVo> permissions;
}
