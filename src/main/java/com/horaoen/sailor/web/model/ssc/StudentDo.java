package com.horaoen.sailor.web.model.ssc;

import com.horaoen.sailor.web.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author horaoen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDo extends BaseModel {
    private String studentId;
    private String idCard;
    private String guardianName;
    private String guardianPhone;
    private String guardianIdCard;
    private String studentName;
}
