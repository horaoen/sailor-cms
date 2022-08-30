package com.horaoen.sailor.web.service.ssc.impl;

import com.horaoen.sailor.autoconfigure.exception.HttpException;
import com.horaoen.sailor.autoconfigure.exception.NotFoundException;
import com.horaoen.sailor.web.dao.ssc.StudentDao;
import com.horaoen.sailor.web.dto.student.StudentDto;
import com.horaoen.sailor.web.model.ssc.StudentDo;
import com.horaoen.sailor.web.service.ssc.OrgService;
import com.horaoen.sailor.web.service.ssc.StudentService;
import com.horaoen.sailor.web.vo.ssc.OrgVo;
import com.horaoen.sailor.web.vo.ssc.StudentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author horaoen
 */
@Service
public class StudentServiceImpl implements StudentService {
    
    private final StudentDao studentDao;
    private final OrgService orgService;

    public StudentServiceImpl(StudentDao studentDao, OrgService orgService) {
        this.studentDao = studentDao;
        this.orgService = orgService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addStudent(StudentDto dto) {
        // 检查orgId是否是
        // 检查studentId 是否已经存在
        // 检查id_card 是否已经存在
        throwOrgNotClassByOrgId(dto.getOrgId());
        throwStudentExistByStudentId(dto.getStudentId());
        throwStudentExistByIdCard(dto.getIdCard());
        
        // 添加学生信息
        StudentDo studentDo = new StudentDo();
        BeanUtils.copyProperties(dto, studentDo);
        studentDao.insertStudent(studentDo);
        // 添加学生 部门关系
        orgService.addOrgStudentRelation(dto.getOrgId(), dto.getStudentId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteStudent(String studentId) {
        // 检查studentId是否存在
        throwStudentNotExistByStudentId(studentId);
        // 删除学生信息
        studentDao.deleteStudent(studentId);
        // 删除学生 部门关系
        orgService.deleteOrgStudentRelation(studentId);
    }

    @Override
    public void updateStudent(String studentId, StudentDto dto) {
        
    }

    @Override
    public StudentVo getStudent(String studentId) {
        throwStudentNotExistByStudentId(studentId);
        StudentDo studentDo = studentDao.selectByStudentId(studentId);
        StudentVo studentVo = new StudentVo();
        BeanUtils.copyProperties(studentDo, studentVo);
        return studentVo;
    }

    private void throwStudentNotExistByStudentId(String studentId) {
        if(studentDao.selectByStudentId(studentId) == null) {
            throw new NotFoundException(10209);
        }
    }

    private void throwStudentExistByIdCard(String idCard) {
        if (studentDao.selectByIdCard(idCard) != null) {
            throw new HttpException(10206);
        }
    }

    private void throwStudentExistByStudentId(String studentId) {
        if (studentDao.selectByStudentId(studentId) != null) {
            throw new HttpException(10207);
        }
    }

    private void throwOrgNotClassByOrgId(Long orgId) {
        OrgVo org = orgService.getOrgByOrgId(orgId);
        String delimiter = "-";
        if (org == null || org.getAncestors().split(delimiter).length != 3) {
            throw new HttpException(10205);
        }
    }


}
