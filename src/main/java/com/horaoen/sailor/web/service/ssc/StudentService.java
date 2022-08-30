package com.horaoen.sailor.web.service.ssc;

import com.horaoen.sailor.web.dto.student.StudentDto;

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
}
