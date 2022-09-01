package com.horaoen.sailor.web.controller.ssc;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.github.pagehelper.PageHelper;
import com.horaoen.sailor.web.common.util.PageUtil;
import com.horaoen.sailor.web.dto.student.StudentDto;
import com.horaoen.sailor.web.dto.student.StudentForUpdateDto;
import com.horaoen.sailor.web.service.ssc.OrgService;
import com.horaoen.sailor.web.service.ssc.StudentService;
import com.horaoen.sailor.web.vo.message.*;
import com.horaoen.sailor.web.vo.ssc.StudentForEditVo;
import com.horaoen.sailor.web.vo.ssc.StudentVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

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
        return new DeletedVo<>(22);
    }
    
    @PutMapping("/{studentId}")
    @Operation(summary = "学生管理-更新信息")
    public UpdatedVo<?> updateStudent(@PathVariable String studentId,
                                      @RequestBody @Validated StudentForUpdateDto dto) {
        studentService.updateStudent(studentId, dto);
        return new UpdatedVo<>(23);
    }
    
    @GetMapping("/{studentId}")
    @Operation(summary = "学生管理-根据studentId查询信息") 
    public UnifyResponseVo<StudentForEditVo> getStudent(@PathVariable String studentId) {
        return new UnifyResponseVo<>(studentService.getStudent(studentId));
    }
    
    @GetMapping
    @Operation(summary = "学生管理-查询所有学生信息")
    public PageResponseVo<StudentVo> getPageStudent(
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") int count,
            @RequestParam(name = "page", required = false, defaultValue = "1")
            @Min(value = 0, message = "{page.number.min}") int page
    ) {
        PageHelper.startPage(page, count);
        return PageUtil.build(studentService.getAllStudent());
    }
    
    @GetMapping("excel")
    @Operation(summary = "学生管理-导出excel")
    public void exportStudentExcel(HttpServletResponse response) throws IOException {
        List<StudentVo> allStudent = studentService.getAllStudent();
        setExcelRespProp(response, "学生信息");
        EasyExcel.write(response.getOutputStream())
                .head(StudentVo.class)
                .excelType(ExcelTypeEnum.XLSX)
                .sheet("学生信息")
                .doWrite(allStudent);
    }

    private void setExcelRespProp(HttpServletResponse response, String rawFileName) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(rawFileName, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
    }
    
    
    
}
