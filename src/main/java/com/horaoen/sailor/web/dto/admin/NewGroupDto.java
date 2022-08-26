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
public class NewGroupDto {
    @NotBlank(message = "{group.name.not-blank}")
    @Length(min = 1, max = 60, message = "{group.name.length}")
    private String name;
    
    @Length(min = 1, max = 255, message = "{group.info.length}")
    private String info;
    
    @LongList(allowBlank = true, message = "{permission.ids.long-list}")
    private List<Long> permissions;
}
