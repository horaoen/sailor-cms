package com.horaoen.sailor.web.dao.cms;

import com.horaoen.sailor.web.model.cms.UserIdentityDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 通过用户id获取凭证信息
     * @param userId 用户id
     * @return 凭证信息
     */
    List<UserIdentityDo> selectUserIdentityByUserId(Long userId);
}
