package com.horaoen.sailor.web.dao.cms;

import com.horaoen.sailor.web.model.cms.UserGroupDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserGroupDao {
    List<UserGroupDo> selectUserByGroupId(Long groupId);
    
}
