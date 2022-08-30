package com.horaoen.sailor.web.bo.ssc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author horaoen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrgNodeBo{
    private Long id;
    private String orgName;
    private Long parentId;
    private String ancestors;
    private int orderNum;
    
    private List<OrgNodeBo> children;
}
