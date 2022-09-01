package com.horaoen.sailor.web.vo.ssc;

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
    private String studentName;
    private String studentId;
    private String idCard;
    private String orgName;
    private String guardianName;
    private String guardianPhone;
    private String guardianIdCard;
    private String headTeacher;
    private Date createTime;
    
}
