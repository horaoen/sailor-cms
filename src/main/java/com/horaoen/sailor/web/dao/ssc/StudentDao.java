package com.horaoen.sailor.web.dao.ssc;

import com.horaoen.sailor.web.model.ssc.StudentDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author horaoen
 */
@Mapper
public interface StudentDao {
    /**
     * 根据身份证查询学生信息
     * @param idCard 身份证号
     * @return 学生信息
     */
    StudentDo selectByIdCard(String idCard);

    /**
     * 根据学号查询学生信息
     * @param studentId 学号
     * @return 学生信息
     */
    StudentDo selectByStudentId(String studentId);

    /**
     * 添加学生信息
     * @param studentDo 学生信息
     */
    void insertStudent(StudentDo studentDo);

    /**
     * 删除学生信息
     * @param studentId 学生id
     */
    void deleteStudent(String studentId);

    /**
     * 获取全部学生信息
     * @return 学生信息列表
     */
    List<StudentDo> selectAll();

    /**
     * 更新学生信息
     * @param studentDo 学生信息
     */
    void updateStudent(StudentDo studentDo);
}
