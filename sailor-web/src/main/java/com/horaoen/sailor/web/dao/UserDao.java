package com.horaoen.sailor.web.dao;

import com.horaoen.sailor.web.model.UserDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author horaoen
 */
@Mapper
public interface UserDao {
    int selectCountById(@Param("id") Long id);
    int selectCountByUsername(@Param("username") String username);
    int selectCountByEmail(@Param("email") String email);

    void insert(@Param("user") UserDo user);

    List<UserDo> selectUsersByGroupExcludedRoot(@Param("groupId") Long groupId, @Param("rootGroupId") Long rootGroupId);
    
     int deleteById(Long userId);
}
