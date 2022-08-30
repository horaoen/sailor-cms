package com.horaoen.sailor.web.model.ssc;

import com.horaoen.sailor.web.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author horaoen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrgDo extends BaseModel {
    private String orgName;
    private Long parentId;
    private String ancestors;
    private int orderNum;
}
