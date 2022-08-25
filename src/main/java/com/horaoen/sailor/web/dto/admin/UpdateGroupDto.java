package com.horaoen.sailor.web.dto.admin;

import com.horaoen.sailor.autoconfigure.validator.LongList;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author horaoen
 */
@Data
public class UpdateGroupDto {
    @NotBlank(message = "{group.name.not-blank}")
    private String name;
    
    @Length(min = 1, max = 60, message = "{group.name.length}")
    private String info;

    @LongList(allowBlank = true, message = "{permission.ids.long-list}")
    List<Long> permissions;
}
