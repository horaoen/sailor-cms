package com.horaoen.sailor.web.dao.ssc;

import com.horaoen.sailor.web.model.ssc.OrgDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author horaoen
 */
@Mapper
public interface OrgDao {

    /**
     * 获取组织架构
     * @return 组织架构
     */
    List<OrgDo> getAllRegion();

    /**
     * 创建一级部门
     * @param orgDo 部门信息
     */
    void addTopOrg(OrgDo orgDo);

    /**
     * 根据orgId获取部门
     * @param orgId 组织id
     * @return 部门信息
     */
    OrgDo selectOrgById(Long orgId);

    /**
     * 根据上级部门id和部门名称获取部门信息
     * @param parentId 上级部门id
     * @param orgName 部门名称
     * @return 部门信息
     */
    OrgDo selectByParentIdAndOrgName(Long parentId, String orgName);

    /**
     * 根据上级部门id和部门名称获取部门信息
     * @param orgDo 部门信息
     */
    void addSubOrg(OrgDo orgDo);

    /**
     * 更新部门信息
     * @param orgDo 部门信息
     */
    void updateOrg(OrgDo orgDo);

    /**
     * 删除部门
     * @param ids 部门id
     */
    void deleteByIds(ArrayList<Long> ids);

    /**
     * 根据userId获取部门信息
     * @param userId 用户id
     * @return 部门信息
     */
    OrgDo selectOrgByUserId(Long userId);
}
