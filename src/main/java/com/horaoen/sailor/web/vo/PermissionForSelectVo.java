package com.horaoen.sailor.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author horaoen
 */
@Data
public class PermissionForSelectVo extends PermissionVo{
    private boolean has;
    public PermissionForSelectVo(PermissionVo permissionVo, boolean has) {
        super(permissionVo.getId(), permissionVo.getName(), permissionVo.getModule(), permissionVo.getMount());
        this.has = has;
    }
}
