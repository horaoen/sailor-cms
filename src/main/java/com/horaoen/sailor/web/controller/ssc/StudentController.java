package com.horaoen.sailor.web.controller.ssc;

import com.horaoen.sailor.web.dto.student.StudentDto;
import com.horaoen.sailor.web.service.ssc.OrgService;
import com.horaoen.sailor.web.service.ssc.StudentService;
import com.horaoen.sailor.web.vo.message.CreatedVo;
import com.horaoen.sailor.web.vo.message.DeletedVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author horaoen
 */
@RestController
@RequestMapping("/ssc/student")
@Tag(name = "学生管理")
public class StudentController {
    private final StudentService studentService;
    private final OrgService orgService;

    public StudentController(StudentService studentService, OrgService orgService) {
        this.studentService = studentService;
        this.orgService = orgService;
    }
    
    @PostMapping
    @Operation(summary = "学生管理-录入信息")
    public CreatedVo<?> addStudent(@RequestBody @Validated StudentDto dto) {
        studentService.addStudent(dto);
        return new CreatedVo<>(20);
    }
    
    @DeleteMapping("/{studentId}")
    @Operation(summary = "学生管理-删除信息")
    public DeletedVo<?> deleteStudent(@PathVariable String studentId) {
        studentService.deleteStudent(studentId);
        return new DeletedVo<>(18);
    }
}
