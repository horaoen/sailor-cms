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
public class StudentForUpdateDto {
    @JsonProperty("studentName")
    @Schema(description = "学生姓名", example = "张三")
    @NotBlank(message = "{student.name.not-blank}")
    private String studentName;

    @JsonProperty("guardianName")
    @Schema(description = "监护人姓名", example = "李四")
    @NotBlank(message = "{student.guardian-name.not-blank}")
    private String guardianName;

    @JsonProperty("guardianPhone")
    @Schema(description = "监护人电话", example = "15137667148")
    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "{user.phone.pattern}")
    private String guardianPhone;

    @NotNull(message = "{student.org-id.not-null}")
    @Schema(description = "学生所属机构id")
    @JsonProperty("orgId")
    private Long orgId;
}
