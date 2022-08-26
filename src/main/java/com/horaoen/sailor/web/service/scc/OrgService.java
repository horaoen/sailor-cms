package com.horaoen.sailor.web.service.scc;

import com.horaoen.sailor.web.bo.scc.OrgNodeBo;
import com.horaoen.sailor.web.dto.org.OrgDto;
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
     * @return 部门id
     */
    Long addTopOrg(TopOrgDto dto);

    /**
     * 根据orgId获取部门
     * @param orgId 组织id
     * @return 部门信息
     */
    OrgVo getOrgByOrgId(Long orgId);

    /**
     * 根据parentId创建子部门
     * @param parentId 父部门id
     * @param dto 部门信息
     * @return 部门id
     */
    Long addSubOrg(Long parentId, OrgDto dto);

    /**
     * 更新部门信息
     * @param orgId 部门id
     * @param dto 部门信息
     */
    void updateOrg(Long orgId, OrgDto dto);

    /**
     * 删除部门
     * @param orgIds 部门id
     */
    void deleteOrg(List<Long> orgIds);
}
