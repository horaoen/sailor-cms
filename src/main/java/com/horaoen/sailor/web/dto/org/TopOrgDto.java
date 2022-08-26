package com.horaoen.sailor.web.dto.org;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author horaoen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopOrgDto {
    private String organName;
    private int orderField = 1;
}
