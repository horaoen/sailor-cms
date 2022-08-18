package com.horaoen.sailor.web.service;

import com.horaoen.sailor.web.dto.user.RegisterDto;
import com.horaoen.sailor.web.model.UserDo;

import java.util.List;

/**
 * @author horaoen
 */
public interface UserService {
    UserDo createUser(RegisterDto validator);

    UserDo getUserByUsername(String username);

    boolean checkUserExistByUsername(String username);

    boolean checkUserExistById(Long id);

    boolean checkUserExistByEmail(String email);
    
    List<UserDo> getUserByGroupId(Long groupId);
}
