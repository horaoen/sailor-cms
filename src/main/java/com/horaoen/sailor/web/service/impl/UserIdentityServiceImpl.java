package com.horaoen.sailor.web.service.impl;

import com.horaoen.sailor.core.util.EncryptUtil;
import com.horaoen.sailor.web.common.constant.IdentityConstant;
import com.horaoen.sailor.web.dao.UserIdentityDao;
import com.horaoen.sailor.web.model.UserIdentityDo;
import com.horaoen.sailor.web.service.UserIdentityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public boolean verifyUsernamePassword(Long id, String username, String password) {
        List<UserIdentityDo> userIdentityDos = userIdentityDao.selectUserIdentityByUserId(id);
        List<UserIdentityDo> identityOfNameAndPassword = userIdentityDos.stream().filter(
                        userIdentityDo -> IdentityConstant.USERNAME_PASSWORD_IDENTITY.equals(userIdentityDo.getIdentityType()))
                .collect(Collectors.toList());
        if(identityOfNameAndPassword.isEmpty()) {
            return false;
        }
        return EncryptUtil.verify(identityOfNameAndPassword.get(0).getCredential(), password);
    }
}
