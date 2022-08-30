package com.horaoen.sailor.web.dto.student;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author horaoen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    
    @JsonProperty("studentName")
    @Schema(description = "学生姓名", example = "张三")
    @NotBlank(message = "{student.name.not-blank}")
    private String studentName;
    
    @JsonProperty("studentId")
    @Schema(description = "学生学号", example = "A2020008601")
    @Pattern(regexp = "^[A-Z]/d{10}", message = "{student.id.pattern}")
    private String studentId;
    
    @JsonProperty("idCard")
    @Schema(description = "学生身份证号", example = "411526200010284815")
    @Pattern(regexp = "\\d{17}[\\d|x]|\\d{15}", message = "{student.id-card.pattern}")
    private String idCard;
    
    @JsonProperty("guardianName")
    @Schema(description = "监护人姓名", example = "李四")
    @NotBlank(message = "{student.guardian-name.not-blank}")
    private String guardianName;
    
    @JsonProperty("guardianIdCard")
    @Schema(description = "监护人身份证号", example = "411526200010284815")
    private String guardianIdCard;
    
    @JsonProperty("guardianPhone")
    @Schema(description = "监护人电话", example = "15137667148")
    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "{user.phone.pattern}")
    private String guardianPhone;
    
    @NotNull(message = "{student.org-id.not-null}")
    @Schema(description = "学生所属机构id")
    @JsonProperty("orgId")
    private Long orgId;
}
