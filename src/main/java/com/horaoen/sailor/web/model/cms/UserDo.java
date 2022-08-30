package com.horaoen.sailor.web.model.cms;

import com.horaoen.sailor.web.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author horaoen
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDo extends BaseModel {
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户昵称, 唯一
     */
    private String nickname;
    
    /**
     * 头像url
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 手机号
     */
    private String phone;
}
