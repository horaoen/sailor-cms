package com.horaoen.sailor.web.service.ssc.impl;

import com.horaoen.sailor.autoconfigure.exception.ForbiddenException;
import com.horaoen.sailor.web.bo.ssc.OrgNodeBo;
import com.horaoen.sailor.web.dao.ssc.OrgDao;
import com.horaoen.sailor.web.dao.ssc.StudentOrgDao;
import com.horaoen.sailor.web.dao.ssc.UserOrgDao;
import com.horaoen.sailor.web.dto.org.OrgDto;
import com.horaoen.sailor.web.dto.org.TopOrgDto;
import com.horaoen.sailor.web.model.ssc.OrgDo;
import com.horaoen.sailor.web.service.ssc.OrgService;
import com.horaoen.sailor.web.vo.ssc.OrgVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author horaoen
 */
@Service
public class OrgServiceImpl implements OrgService {
    private final OrgDao orgDao;
    private final UserOrgDao userOrgDao;
    private final StudentOrgDao studentOrgDao;

    public OrgServiceImpl(OrgDao orgDao, UserOrgDao userOrgDao, StudentOrgDao studentOrgDao) {
        this.orgDao = orgDao;
        this.userOrgDao = userOrgDao;
        this.studentOrgDao = studentOrgDao;
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

    @Override
    public Long addSubOrg(Long parentId, OrgDto dto) {
        throwOrgNameExistException(parentId, dto.getOrgName());
        OrgDo orgDo = new OrgDo();
        BeanUtils.copyProperties(dto, orgDo);
        orgDo.setParentId(parentId);
        orgDo.setAncestors(getAncestors(parentId, orgDo.getOrgName()));
        orgDao.addSubOrg(orgDo);
        return orgDo.getId();
    }

    @Override
    public void updateOrg(Long orgId, OrgDto dto) {
        throwOrgNotExistById(orgId);
        OrgDo orgDo = orgDao.selectOrgById(orgId);
        throwOrgNameExistException(orgDo.getParentId(), dto.getOrgName());
        orgDo.setOrgName(dto.getOrgName());
        orgDo.setAncestors(getAncestors(orgDo.getParentId(), dto.getOrgName()));
        orgDo.setOrderNum(dto.getOrderNum());
        orgDao.updateOrg(orgDo);
    }

    @Override
    public void deleteOrg(List<Long> ids) {
        ids.forEach(this::throwOrgNotExistById);

        List<OrgDo> organList = orgDao.getAllRegion();
        Set<Long> toDeleteIds = new HashSet<>(ids);
        Set<OrgDo> organs = organList.stream()
                .filter(organItem -> ids.contains(organItem.getId()))
                .collect(Collectors.toSet());
        for (OrgDo organ: organs) {
            toDeleteIds.addAll(getAllSubOrgansId(organ, organList));
        }
        throwOrgDeleteException(toDeleteIds);
        orgDao.deleteByIds(new ArrayList<>(toDeleteIds));
    }

    private void throwOrgDeleteException(Set<Long> toDeleteIds) {
        toDeleteIds.forEach(id -> {
            if (userOrgDao.countUserByOrgId(id) > 0) {
                throw new ForbiddenException("该组织下存在用户，不能删除");
            }
            if (studentOrgDao.countStudentByOrgId(id) > 0) {
                throw new ForbiddenException("该组织下存在学生，不能删除");
            }
        });
    }

    @Override
    public OrgVo getOrgByUserId(Long userId) {
        OrgDo orgDo = orgDao.selectOrgByUserId(userId);
        OrgVo orgVo = new OrgVo();
        BeanUtils.copyProperties(orgDo, orgVo);
        return orgVo;
    }

    @Override
    public boolean checkOrgExistById(Long orgId) {
        return orgDao.selectOrgById(orgId) != null;
    }

    @Override
    public void addOrgUserRelation(Long orgId, Long userId) {
        userOrgDao.insert(orgId, userId);
    }

    @Override
    public void updateUserOrg(Long userId, Long orgId) {
        userOrgDao.update(userId, orgId);
    }

    @Override
    public void addOrgStudentRelation(Long orgId, String studentId) {
        studentOrgDao.insert(orgId, studentId);
    }

    @Override
    public void deleteOrgStudentRelation(String studentId) {
        studentOrgDao.deleteByStudentId(studentId);
    }

    @Override
    public OrgVo geOrgByStudentId(String studentId) {
        OrgDo orgDo = orgDao.selectOrgByStudentId(studentId);
        OrgVo orgVo = new OrgVo();
        BeanUtils.copyProperties(orgDo, orgVo);
        return orgVo;
    }

    private Set<Long> getAllSubOrgansId(OrgDo organ, List<OrgDo> organList) {
        Set<Long> subOrgansId = new HashSet<>();
        subOrgansId.add(organ.getId());
        List<OrgDo> subOrgans = organList.stream()
                .filter(organItem -> organ.getId().equals(organItem.getParentId()))
                .collect(Collectors.toList());
        for (OrgDo subOrgan: subOrgans) {
            subOrgansId.addAll(getAllSubOrgansId(subOrgan, organList));
        }
        return subOrgansId;
    }

    private void throwOrgNotExistById(Long orgId) {
        OrgDo orgDo = orgDao.selectOrgById(orgId);
        if(orgDo == null) {
            throw new ForbiddenException("组织id不存在");
        }
    }

    private String getAncestors(Long parentId, String orgName) {
        OrgDo orgDo = orgDao.selectOrgById(parentId);
        if (orgDo == null) {
            return orgName;
        }
        return orgDo.getAncestors() + "-" + orgName;
    }

    private OrgNodeBo convertToOrganNodeBo(OrgDo organ, List<OrgDo> organList) {
        OrgNodeBo organNode = new OrgNodeBo();
        BeanUtils.copyProperties(organ, organNode);
        List<OrgNodeBo> children = organList.stream()
                .filter(organItem -> organ.getId().equals(organItem.getParentId()) && organItem.getId() != 1)
                .map(organItem -> convertToOrganNodeBo(organItem, organList)).collect(Collectors.toList());
        
        // 前端需求，如果children empty, 返回null;
        if(!children.isEmpty()) {
            organNode.setChildren(children);
        }
        return organNode;
    }

    private void throwOrgNameExistException(Long parentId, String orgName) {
        OrgDo orgDo = orgDao.selectByParentIdAndOrgName(parentId, orgName);
        if (orgDo != null) {
            throw new ForbiddenException(10204);
        }
    }
}
