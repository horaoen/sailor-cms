package com.horaoen.sailor.web.service;

import com.horaoen.sailor.web.model.UserIdentityDo;

/**
 * @author horaoen
 */
public interface UserIdentityService {

    UserIdentityDo createIdentity(Long userId,
                                  String identityType,
                                  String identifier,
                                  String credential);

    UserIdentityDo createIdentity(UserIdentityDo userIdentity);

    UserIdentityDo createUsernamePasswordIdentity(Long userId,
                                                  String username,
                                                  String password);


    boolean verifyUsernamePassword(Long userId, String username, String password);

    boolean changePassword(Long userId, String password);

    boolean changeUsername(Long userId, String username);

    boolean changeUsernamePassword(Long userId, String username, String password);
}
