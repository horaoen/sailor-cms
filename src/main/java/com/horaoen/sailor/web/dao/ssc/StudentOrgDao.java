package com.horaoen.sailor.web.dao.ssc;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author horaoen
 */
@Mapper
public interface StudentOrgDao {
    /**
     * 添加关系
     * @param orgId 组织id
     * @param studentId 学生id
     */
    void insert(@Param("orgId") Long orgId, @Param("studentId") String studentId);

    /**
     * 删除关系
     * @param studentId
     */
    void deleteByStudentId(String studentId);

    /**
     * 根据orgId计算学生数量
     * @param orgId 组织id
     * @return 学生数量
     */
    int countStudentByOrgId(Long orgId);

    /** 获取组织id根据学生id
     * @param studentId 学生id
     * @return 组织id
     */
    Long selectOrgIdByStudentId(String studentId);

    /**
     * 更新学生部门
     * @param studentId 学生id
     * @param orgId 组织id
     */
    void update(String studentId, Long orgId);
}
