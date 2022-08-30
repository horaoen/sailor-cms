package com.horaoen.sailor.web.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.horaoen.sailor.autoconfigure.validator.LongList;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author horaoen
 */
@Data
public class UpdateUserInfoDto {
    @NotBlank(message = "{username.not-blank}")
    private String username;
    
    @NotBlank(message = "{nickname.not-blank}")
    private String nickname;
    
    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "{user.phone.pattern}")
    private String phone;
    
    @LongList(min = 1, message = "{group.ids.long-list}")
    @JsonProperty("groupIds")
    private List<Long> groupIds;
    
    @NotNull(message = "{org.id.not-null}")
    @JsonProperty("orgId")
    private Long orgId;
    
    @Pattern(regexp = "^$|^[A-Za-z0-9_*&$#@]{6,22}$", message = "{password.new.pattern}")
    private String newPassword;
    
    private String confirmPassword;
}
