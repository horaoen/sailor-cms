package com.horaoen.sailor.web.bo.cms;

import cn.hutool.core.bean.BeanUtil;
import com.horaoen.sailor.web.model.cms.GroupDo;
import com.horaoen.sailor.web.vo.cms.PermissionVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author horaoen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupPermissionBo {
    private Long id;

    private String name;

    private String info;

    private List<PermissionVo> permissions;

    public GroupPermissionBo(GroupDo group, List<PermissionVo> permissions) {
        BeanUtil.copyProperties(group, this);
        this.permissions = permissions;
    }
}
