package com.horaoen.sailor.web.service.ssc;

import com.horaoen.sailor.web.bo.ssc.OrgNodeBo;
import com.horaoen.sailor.web.dto.org.OrgDto;
import com.horaoen.sailor.web.dto.org.TopOrgDto;
import com.horaoen.sailor.web.vo.ssc.OrgVo;

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

    /**
     * 根据orgId获取子部门
     * @param userId 用户id
     * @return 部门信息
     */
    OrgVo getOrgByUserId(Long userId);

    /**
     * 根据orgId检查部门是否存在
     * @param orgId 组织id
     * @return 是否存在
     */
    boolean checkOrgExistById(Long orgId);

    /**
     * 添加user org关系
     * @param orgId 组织id
     * @param userId 用户id
     */
    void addOrgUserRelation(Long orgId, Long userId);

    /**
     * 更新user org关系
     * @param userId 用户id
     * @param orgId 组织id
     */
    void updateUserOrg(Long userId, Long orgId);

    /**
     * 添加student org关系
     * @param orgId 组织id
     * @param studentId 学生id
     */
    void addOrgStudentRelation(Long orgId, String studentId);

    /**
     * 删除student org关系
     * @param studentId 学号
     */
    void deleteOrgStudentRelation(String studentId);

    /**
     * 获取学生所在班级
     * @param studentId 学号
     * @return 所在部门（班级）
     */
    OrgVo geOrgByStudentId(String studentId);

    /**
     * 更新学生 部门关系
     * @param studentId 学号
     * @param orgId 部门id
     */
    void updateStudentOrgRelation(String studentId, Long orgId);
}
