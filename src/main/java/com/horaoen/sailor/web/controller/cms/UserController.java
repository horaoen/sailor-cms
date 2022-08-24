package com.horaoen.sailor.web.controller.cms;

import com.horaoen.sailor.autoconfigure.exception.NotFoundException;
import com.horaoen.sailor.autoconfigure.exception.ParameterException;
import com.horaoen.sailor.autoconfigure.interceptor.AuthorizeInterceptor;
import com.horaoen.sailor.core.annotation.RefreshRequired;
import com.horaoen.sailor.core.token.DoubleJWT;
import com.horaoen.sailor.core.token.Tokens;
import com.horaoen.sailor.web.common.LocalUser;
import com.horaoen.sailor.web.dto.user.LoginDto;
import com.horaoen.sailor.web.model.UserDo;
import com.horaoen.sailor.web.service.UserIdentityService;
import com.horaoen.sailor.web.service.UserService;
import com.horaoen.sailor.web.vo.UnifyResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    private final AuthorizeInterceptor authorizeInterceptor;
    
    public UserController(UserService userService,
                          DoubleJWT jwt,
                          UserIdentityService userIdentityService, AuthorizeInterceptor authorizeInterceptor) {
        this.userService = userService;
        this.jwt = jwt;
        this.userIdentityService = userIdentityService;
        this.authorizeInterceptor = authorizeInterceptor;
    }
    
    @PostMapping("/login")
    @Operation(description = "用户登陆")
    public UnifyResponseVo<Tokens> login(@RequestBody @Validated LoginDto validator) {
        UserDo user = userService.getUserByUsername(validator.getUsername());
        if (user == null) {
            throw new NotFoundException(10021);
        }
        boolean valid = userIdentityService.verifyUsernamePassword(
                user.getId(),
                user.getUsername(),
                validator.getPassword());
        if (!valid) {
            throw new ParameterException(10031);
        }
        
        return new UnifyResponseVo<>(jwt.generateTokens(user.getId()));
    }
    
    @GetMapping("/refresh")
    @Operation(summary = "刷新token")
    @RefreshRequired
    public Tokens getRefreshToken() {
        UserDo user = LocalUser.getLocalUser();
        return jwt.generateTokens(user.getId());
    }
    
    
    
    
    
    
}
