package com.horaoen.sailor.web.dto.user;

import com.horaoen.sailor.sdk.autoconfigure.validator.EqualField;
import com.horaoen.sailor.sdk.autoconfigure.validator.LongList;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author horaoen
 */
@Data
@NoArgsConstructor
@EqualField(srcField = "password", dstField = "confirmPassword", message = "{password.equal-field}")
public class RegisterDto {
    @NotBlank(message = "{username.not-blank}")
    @Length(min = 2, max = 10, message = "{username.length}")
    private String username;

    @LongList(allowBlank = true, message = "{group.ids.long-list}")
    private List<Long> groupIds;

    @Email(message = "{email}")
    private String email;

    @NotBlank(message = "{password.new.not-blank}")
    @Pattern(regexp = "^[A-Za-z0-9_*&$#@]{6,22}$", message = "{password.new.pattern}")
    private String password;

    @NotBlank(message = "{password.confirm.not-blank}")
    private String confirmPassword;
}
