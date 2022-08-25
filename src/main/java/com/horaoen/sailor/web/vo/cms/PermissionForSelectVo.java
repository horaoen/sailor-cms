package com.horaoen.sailor.web.vo.cms;

import lombok.Data;

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
