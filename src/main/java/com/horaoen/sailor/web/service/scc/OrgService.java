package com.horaoen.sailor.web.service.scc;

import com.horaoen.sailor.web.bo.scc.OrgNodeBo;

import java.util.List;

/**
 * @author horaoen
 */
public interface OrgService {
    /**
     * 获取组织架构
     * @return 组织架构
     */
    List<OrgNodeBo> getAllRegion();
}
