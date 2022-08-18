package com.horaoen.sailor.web.dao;

import com.horaoen.sailor.web.model.UserGroupDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserGroupDao {
    List<UserGroupDo> selectUserByGroupId(Long groupId);
    
}
