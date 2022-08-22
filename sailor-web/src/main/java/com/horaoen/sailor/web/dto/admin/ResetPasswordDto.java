package com.horaoen.sailor.web.dto.admin;

import com.horaoen.sailor.sdk.autoconfigure.validator.EqualField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author horaoen
 */
@Data
@EqualField(srcField = "newPassword", dstField = "confirmPassword", message = "{password.equal-field}")
public class ResetPasswordDto {
    @NotBlank(message = "{password.new.not-blank}")
    @Pattern(regexp = "^[A-Za-z0-9_*&$#@]{6,22}$", message = "{password.new.pattern}")
    private String newPassword;
    
    @NotBlank(message = "{password.confirm.not-blank}")
    private String confirmPassword;
}
