package com.horaoen.sailor.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author horaoen
 */
@Mapper
public interface UserIdentityDao {

    /**
     * 通过用户id更新用户密码
     * @param userId 用户id
     * @param credential 密码
     * @return 是否更新成功
     */
    int updatePasswordById(@Param("userId") Long userId, @Param("credential") String credential);

    /**
     * 通过用户id删除用户
     * @param userId 用户id
     * @return 是否删除成功
     */
    int deleteById(Long userId);
}
