package com.horaoen.sailor.web.vo.scc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author horaoen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrgVo {
    private Long id;
    private String orgName;
    private Long parentId;
    private String ancestors;
    private int orderNum;
}
