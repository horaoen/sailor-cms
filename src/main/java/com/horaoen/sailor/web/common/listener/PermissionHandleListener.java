package com.horaoen.sailor.web.common.listener;

import com.horaoen.sailor.autoconfigure.bean.PermissionMetaCollector;
import com.horaoen.sailor.web.service.cms.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author pedro@TaleLin
 * @author colorful@TaleLin
 */
@Component
public class PermissionHandleListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PermissionMetaCollector metaCollector;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        addNewPermissions();
        removeUnusedPermissions();
    }

    private void addNewPermissions() {
//        metaCollector.getMetaMap().values().forEach(meta -> {
//            String module = meta.getModule();
//            String permission = meta.getPermission();
//            createPermissionIfNotExist(permission, module);
//        });
    }

    private void removeUnusedPermissions() {
//        List<PermissionDo> allPermissions = permissionService.list();
//        Map<String, MetaInfo> metaMap = metaCollector.getMetaMap();
//        for (PermissionDo permission : allPermissions) {
//            boolean stayedInMeta = metaMap
//                    .values()
//                    .stream()
//                    .anyMatch(meta -> meta.getModule().equals(permission.getModule())
//                            && meta.getPermission().equals(permission.getName()));
//            if (!stayedInMeta) {
//                permission.setMount(false);
//                permissionService.updateById(permission);
//            }
//        }
    }

    private void createPermissionIfNotExist(String name, String module) {
//        QueryWrapper<PermissionDo> wrapper = new QueryWrapper<>();
//        wrapper.lambda().eq(PermissionDO::getName, name).eq(PermissionDO::getModule, module);
//        PermissionDO permission = permissionService.getOne(wrapper);
//        if (permission == null) {
//            permissionService.save(PermissionDO.builder().module(module).name(name).build());
//        }
//        if (permission != null && !permission.getMount()) {
//            permission.setMount(true);
//            permissionService.updateById(permission);
//        }
    }
}
