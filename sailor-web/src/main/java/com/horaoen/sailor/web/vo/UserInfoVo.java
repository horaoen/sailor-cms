package com.horaoen.sailor.web.vo;

import cn.hutool.core.bean.BeanUtil;
import com.horaoen.sailor.web.model.UserDo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author horaoen
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo {
    private Long id;

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

    /**
     * 分组
     */
    private List<GroupVo> groups;

    public UserInfoVo(UserDo user, List<GroupVo> groups) {
        BeanUtil.copyProperties(user, this);
        this.groups = groups;
    }
}
