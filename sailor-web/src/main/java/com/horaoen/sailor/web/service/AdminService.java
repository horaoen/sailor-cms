package com.horaoen.sailor.web.service;

import com.horaoen.sailor.web.vo.UserInfoVo;

import java.util.List;

/**
 * @author horaoen
 */
public interface AdminService {

    /**
     * 通过分组id分页获取含有用户组信息的用户数据
     *
     * @param groupId 分组id
     * @return 用户数据
     */
    List<UserInfoVo> getUserByGroupId(Long groupId);
}
