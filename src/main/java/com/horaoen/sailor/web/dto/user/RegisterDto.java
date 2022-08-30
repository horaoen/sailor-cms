package com.horaoen.sailor.web.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.horaoen.sailor.autoconfigure.validator.EqualField;
import com.horaoen.sailor.autoconfigure.validator.LongList;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    
    @NotBlank(message = "{nickname.not-blank}")
    @Length(min = 2, max = 10, message = "{nickname.length}")
    private String nickname;

    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "{user.phone.pattern}")
    private String phone;

    @LongList(message = "{group.ids.long-list}")
    @JsonProperty("groupIds")
    private List<Long> groupIds;

    @NotBlank(message = "{password.new.not-blank}")
    @Pattern(regexp = "^[A-Za-z0-9_*&$#@]{6,22}$", message = "{password.new.pattern}")
    private String password;

    @NotBlank(message = "{password.confirm.not-blank}")
    @JsonProperty("confirmPassword")
    private String confirmPassword;
    
    @NotNull(message = "org.id.not-null")
    @JsonProperty("orgId")
    private Long orgId;
}
