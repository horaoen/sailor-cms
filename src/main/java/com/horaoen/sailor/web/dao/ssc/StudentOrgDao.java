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
}
