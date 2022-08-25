package com.horaoen.sailor.web.service.cms.impl;

import cn.hutool.core.bean.BeanUtil;
import com.horaoen.sailor.web.dao.cms.PermissionDao;
import com.horaoen.sailor.web.model.cms.PermissionDo;
import com.horaoen.sailor.web.service.cms.PermissionService;
import com.horaoen.sailor.web.vo.cms.PermissionVo;
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

    @Override
    public List<PermissionDo> getPermissionByGroupIds(List<Long> userGroupIds) {
        return null;
    }

    @Override
    public List<PermissionVo> getPermissionByGroupId(Long id) {
        List<PermissionDo> permissionDos = permissionDao.selectPermissionsByGroupId(id);
        List<PermissionVo> permissionVos = permissionDos.stream().map(permissionDo -> {
            PermissionVo permissionVo = new PermissionVo();
            BeanUtil.copyProperties(permissionDo, permissionVo);
            return permissionVo;
        }).collect(Collectors.toList());
        return permissionVos;
    }

    @Override
    public boolean checkPermissionExistById(Long id) {
        return permissionDao.selectPermissionById(id) != null;
    }
}
