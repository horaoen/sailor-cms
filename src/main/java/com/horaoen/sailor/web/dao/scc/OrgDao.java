package com.horaoen.sailor.web.dao.scc;

import com.horaoen.sailor.web.model.scc.OrgDo;
import com.horaoen.sailor.web.vo.scc.OrgVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author horaoen
 */
@Mapper
public interface OrgDao {

    /**
     * 获取组织架构
     * @return 组织架构
     */
    List<OrgDo> getAllRegion();
}
