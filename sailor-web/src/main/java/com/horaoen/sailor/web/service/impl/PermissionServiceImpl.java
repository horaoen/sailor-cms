package com.horaoen.sailor.web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.horaoen.sailor.web.dao.PermissionDao;
import com.horaoen.sailor.web.model.PermissionDo;
import com.horaoen.sailor.web.service.PermissionService;
import com.horaoen.sailor.web.vo.PermissionVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author horaoen
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionDao permissionDao;

    public PermissionServiceImpl(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    @Override
    public List<PermissionVo> getAllPermissions(boolean mounted) {
        List<PermissionDo> allPermissions = permissionDao.getAllPermissions(mounted);
        return allPermissions.stream().map(permissionDo -> {
            PermissionVo permissionVo = new PermissionVo();
            BeanUtil.copyProperties(permissionDo, permissionVo);
            return permissionVo;
        }).collect(Collectors.toList());
    }
}
