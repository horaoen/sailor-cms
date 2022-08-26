package com.horaoen.sailor.web.dto.org;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("orgName")
    private String orgName;
    @JsonProperty("orderNum")
    private int orderNum = 1;
}
