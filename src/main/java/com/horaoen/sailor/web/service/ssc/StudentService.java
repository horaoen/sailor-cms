package com.horaoen.sailor.web.service.ssc;

import com.horaoen.sailor.web.dto.student.StudentDto;
import com.horaoen.sailor.web.dto.student.StudentForUpdateDto;
import com.horaoen.sailor.web.vo.ssc.StudentForEditVo;
import com.horaoen.sailor.web.vo.ssc.StudentVo;

import java.util.List;

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
    void updateStudent(String studentId, StudentForUpdateDto dto);

    /**
     * 通过id获取学生信息
     * @param studentId 学生id
     * @return 学生信息
     */
    StudentForEditVo getStudent(String studentId);

    /**
     * 获取学生列表
     * @return 学生列表
     */
    List<StudentVo> getAllStudent();
}
