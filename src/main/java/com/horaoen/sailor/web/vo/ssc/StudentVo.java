package com.horaoen.sailor.web.vo.ssc;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author horaoen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentVo {
    @ExcelProperty("学生姓名")
    @ColumnWidth(20)
    private String studentName;
    
    @ExcelProperty("学号")
    @ColumnWidth(15)
    private String studentId;
    
    @ExcelProperty("学生身份证号")
    @ColumnWidth(25)
    private String idCard;
    
    @ExcelProperty("所在班级")
    @ColumnWidth(40)
    private String orgName;
    
    @ExcelProperty("监护人姓名")
    @ColumnWidth(20)
    private String guardianName;
    
    @ExcelProperty("监护人手机号")
    @ColumnWidth(20)
    private String guardianPhone;
    
    @ExcelProperty("监护人身份证号")
    @ColumnWidth(20)
    private String guardianIdCard;
    
    @ExcelProperty("班主任")
    @ColumnWidth(10)
    private String headTeacher;
    
    @ExcelProperty("创建时间")
    @ColumnWidth(15)
    @DateTimeFormat("yyyy-MM-dd")
    private Date createTime;
    
}
