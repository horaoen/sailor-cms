package com.horaoen.sailor.web.service.impl;

import com.horaoen.sailor.web.model.UserIdentityDo;
import com.horaoen.sailor.web.service.UserIdentityService;

/**
 * @author horaoen
 */
public class UserIdentityServiceImpl implements UserIdentityService {

    @Override
    public UserIdentityDo createIdentity(Long userId, String identityType, String identifier, String credential) {
        return null;
    }

    @Override
    public UserIdentityDo createIdentity(UserIdentityDo userIdentity) {
        return null;
    }

    @Override
    public UserIdentityDo createUsernamePasswordIdentity(Long userId, String username, String password) {
        return null;
    }

    @Override
    public boolean verifyUsernamePassword(Long userId, String username, String password) {
        return false;
    }

    @Override
    public boolean changePassword(Long userId, String password) {
        return false;
    }

    @Override
    public boolean changeUsername(Long userId, String username) {
        return false;
    }

    @Override
    public boolean changeUsernamePassword(Long userId, String username, String password) {
        return false;
    }
}
