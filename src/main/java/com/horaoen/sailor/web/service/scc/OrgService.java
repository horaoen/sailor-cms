package com.horaoen.sailor.web.service.scc;

import com.horaoen.sailor.web.bo.scc.OrgNodeBo;
import com.horaoen.sailor.web.dto.org.TopOrgDto;
import com.horaoen.sailor.web.vo.scc.OrgVo;

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
     * @return 是否创建成功k
     */
    Long addTopOrg(TopOrgDto dto);

    /**
     * 根据orgId获取部门
     * @param orgId 组织id
     * @return 部门信息
     */
    OrgVo getOrgByOrgId(Long orgId);
}
