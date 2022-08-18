package com.horaoen.sailor.web.model;

import lombok.Data;


@Data
public class UserGroupDo {
    
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 分组id
     */
    private Long groupId;

    public UserGroupDo(Long userId, Long groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }
}
