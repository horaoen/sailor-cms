package com.horaoen.sailor.web.service.scc.impl;

import com.horaoen.sailor.autoconfigure.exception.ForbiddenException;
import com.horaoen.sailor.web.bo.scc.OrgNodeBo;
import com.horaoen.sailor.web.dao.scc.OrgDao;
import com.horaoen.sailor.web.dto.org.TopOrgDto;
import com.horaoen.sailor.web.model.scc.OrgDo;
import com.horaoen.sailor.web.service.scc.OrgService;
import com.horaoen.sailor.web.vo.scc.OrgVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author horaoen
 */
@Service
public class OrgServiceImpl implements OrgService {
    private final OrgDao orgDao;

    public OrgServiceImpl(OrgDao orgDao) {
        this.orgDao = orgDao;
    }

    @Override
    public List<OrgNodeBo> getAllRegion() {
        List<OrgDo> orgList = orgDao.getAllRegion();
        List<OrgNodeBo> orgNodeList = orgList.stream()
                .filter(org -> org.getParentId() == 1)
                .map(org -> convertToOrganNodeBo(org, orgList)).collect(Collectors.toList());
        return orgNodeList;
    }

    @Override
    public Long addTopOrg(TopOrgDto dto) {
        throwOrgNameExistException(1L, dto.getOrgName());
        OrgDo orgDo = new OrgDo();
        BeanUtils.copyProperties(dto, orgDo);
        orgDo.setParentId(1L);
        orgDo.setAncestors(dto.getOrgName());
        orgDao.addTopOrg(orgDo);
        return orgDo.getId();
    }

    @Override
    public OrgVo getOrgByOrgId(Long orgId) {
        // TODO 检查orgId是否存在
        OrgDo orgDo = orgDao.selectOrgById(orgId);
        OrgVo orgVo = new OrgVo();
        BeanUtils.copyProperties(orgDo, orgVo);
        return orgVo;
    }

    private OrgNodeBo convertToOrganNodeBo(OrgDo organ, List<OrgDo> organList) {
        OrgNodeBo organNode = new OrgNodeBo();
        BeanUtils.copyProperties(organ, organNode);
        List<OrgNodeBo> children = organList.stream()
                .filter(organItem -> organ.getId().equals(organItem.getParentId()) && organItem.getId() != 1)
                .map(organItem -> convertToOrganNodeBo(organItem, organList)).collect(Collectors.toList());
        organNode.setChildren(children);
        return organNode;
    }

    private void throwOrgNameExistException(Long parentId, String orgName) {
        OrgDo orgDo = orgDao.selectByParentIdAndOrgName(parentId, orgName);
        if (orgDo != null) {
            throw new ForbiddenException(10204);
        }
    }
}
