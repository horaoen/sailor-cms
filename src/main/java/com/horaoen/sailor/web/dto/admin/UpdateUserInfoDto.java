package com.horaoen.sailor.web.dto.admin;

import com.horaoen.sailor.autoconfigure.validator.LongList;
import lombok.Data;

import java.util.List;

/**
 * @author horaoen
 */
@Data
public class UpdateUserInfoDto {
    @LongList(min = 1, message = "{group.ids.long-list}")
    private List<Long> groupIds;
}
