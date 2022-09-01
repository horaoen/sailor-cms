package com.horaoen.sailor.web.vo.ssc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author horaoen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentForEditVo {
    private String studentId;
    private String idCard;
    private String guardianName;
    private String guardianPhone;
    private String guardianIdCard;
    private String studentName;
    private String orgId;
}
