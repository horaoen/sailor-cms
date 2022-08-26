package com.horaoen.sailor.web.service.scc;

import com.horaoen.sailor.web.bo.scc.OrgNodeBo;
import com.horaoen.sailor.web.dto.org.TopOrgDto;

import java.util.List;

/**
 * @author horaoen
 */
public interface OrgService {
    /**
     * 获取组织架构
     * @return 组织架构
     */
    List<OrgNodeBo> getAllRegion();

    /**
     * 创建一级部门
     * @param dto 部门信息
     * @return 是否创建成功
     */
    boolean addTopOrg(TopOrgDto dto);
}
