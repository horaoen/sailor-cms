package com.horaoen.sailor.web.controller.cms;

import com.horaoen.sailor.sdk.autoconfigure.exception.NotFoundException;
import com.horaoen.sailor.sdk.core.token.DoubleJWT;
import com.horaoen.sailor.sdk.core.token.Tokens;
import com.horaoen.sailor.web.dto.user.LoginDto;
import com.horaoen.sailor.web.model.UserDo;
import com.horaoen.sailor.web.service.UserIdentityService;
import com.horaoen.sailor.web.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author horaoen
 */
@RestController
@Tag(name = "用户管理")
@RequestMapping("/cms/user")
public class UserController {
    private final UserService userService;
    private final DoubleJWT jwt;
    private final UserIdentityService userIdentityService;
    
    public UserController(UserService userService, 
                          DoubleJWT jwt, 
                          UserIdentityService userIdentityService) {
        this.userService = userService;
        this.jwt = jwt;
        this.userIdentityService = userIdentityService;
    }

    /**
     * 用户登陆
     */
    @PostMapping("/login")
    public Tokens login(@RequestBody @Validated LoginDto validator) {
        UserDo user = userService.getUserByUsername(validator.getUsername());
        if (user == null) {
            throw new NotFoundException(10021);
        }
        boolean valid = userIdentityService.verifyUsernamePassword(
                user.getId(),
                user.getUsername(),
                validator.getPassword());
        if (!valid) {
            throw new ParameterException("username or password is fault", 10031);
        }
        return jwt.generateTokens(user.getId());
    }
    
    
}
