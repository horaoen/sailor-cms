package com.horaoen.sailor.web.dto.org;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author horaoen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrgDto {
    @NotBlank(message = "{org.name.not-blank}")
    @JsonProperty("orgName")
    private String orgName;
    
    @JsonProperty("orderNum")
    private int orderNum;
}
