package com.horaoen.sailor.web.service.impl;

import com.horaoen.sailor.sdk.core.util.EncryptUtil;
import com.horaoen.sailor.web.dao.UserIdentityDao;
import com.horaoen.sailor.web.model.UserIdentityDo;
import com.horaoen.sailor.web.service.UserIdentityService;
import org.springframework.stereotype.Service;

/**
 * @author horaoen
 */
@Service
public class UserIdentityServiceImpl implements UserIdentityService {
    private final UserIdentityDao userIdentityDao;

    public UserIdentityServiceImpl(UserIdentityDao userIdentityDao) {
        this.userIdentityDao = userIdentityDao;
    }

    @Override
    public boolean changePassword(Long userId, String password) {
        String encrypted = EncryptUtil.encrypt(password);
        return userIdentityDao.updatePasswordById(userId, encrypted) > 0;
    }

    @Override
    public boolean deleteUserIdentity(Long userId) {
        return userIdentityDao.deleteById(userId) > 0;
    }
}
