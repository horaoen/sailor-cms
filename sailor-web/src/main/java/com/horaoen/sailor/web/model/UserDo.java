package com.horaoen.sailor.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDo extends BaseModel {
    /**
     * 用户名，唯一
     */
    private String username;

    /**
     * 用户昵称
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
}
