package com.horaoen.sailor.web.service.ssc;

import com.horaoen.sailor.web.dto.student.StudentDto;
import com.horaoen.sailor.web.vo.ssc.StudentVo;

/**
 * @author horaoen
 */
public interface StudentService {

    /**
     * 添加学生信息
     * @param dto 学生信息
     */
    void addStudent(StudentDto dto);

    /**
     * 删除学生信息
     * @param studentId 学生id
     */
    void deleteStudent(String studentId);

    /**
     * 更新学生信息
     * @param studentId 学生id
     * @param dto 学生信息
     */
    void updateStudent(String studentId, StudentDto dto);

    /**
     * 通过id获取学生信息
     * @param studentId 学生id
     * @return 学生信息
     */
    StudentVo getStudent(String studentId);
}