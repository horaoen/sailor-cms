package com.horaoen.sailor.web.vo.cms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author horaoen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoForEditVo {
    private Long id;
    
    private String username;
    
    private String nickname;

    private String phone;

    private Long groupId;
    
    private Long orgId;
}
