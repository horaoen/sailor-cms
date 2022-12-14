package com.horaoen.sailor.web.common.interceptor;

import com.auth0.jwt.exceptions.*;
import com.auth0.jwt.interfaces.Claim;
import com.horaoen.sailor.autoconfigure.bean.MetaInfo;
import com.horaoen.sailor.autoconfigure.exception.AuthenticationException;
import com.horaoen.sailor.autoconfigure.exception.AuthorizationException;
import com.horaoen.sailor.autoconfigure.exception.NotFoundException;
import com.horaoen.sailor.autoconfigure.exception.TokenInvalidException;
import com.horaoen.sailor.autoconfigure.interfaces.AuthorizeVerifyResolver;
import com.horaoen.sailor.core.token.DoubleJWT;
import com.horaoen.sailor.web.common.LocalUser;
import com.horaoen.sailor.web.model.cms.PermissionDo;
import com.horaoen.sailor.web.model.cms.UserDo;
import com.horaoen.sailor.web.service.cms.GroupService;
import com.horaoen.sailor.web.service.cms.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author horaoen
 */
@Component
class AuthorizeVerifyResolverImpl implements AuthorizeVerifyResolver {

    public final static String AUTHORIZATION_HEADER = "Authorization";

    public final static String BEARER_PATTERN = "^Bearer$";

    @Autowired
    private DoubleJWT jwt;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    //@Value("${sailor.file.domain}")
    private String domain;

    //@Value("${sailor.file.serve-path:assets/**}")
    private String servePath;


    @Override
    public boolean handleLogin(HttpServletRequest request, HttpServletResponse response, MetaInfo meta) {
        String tokenStr = verifyHeader(request, response);
        Map<String, Claim> claims;
        try {
            claims = jwt.decodeAccessToken(tokenStr);
        } catch (TokenExpiredException e) {
            throw new TokenInvalidException(e.getMessage(), 10051);
        } catch (AlgorithmMismatchException | SignatureVerificationException | JWTDecodeException |
                 InvalidClaimException e) {
            throw new TokenInvalidException(e.getMessage(), 10041);
        }
        return getClaim(claims);
    }

    @Override
    public boolean handleGroup(HttpServletRequest request, HttpServletResponse response, MetaInfo meta) {
        handleLogin(request, response, meta);
        UserDo user = LocalUser.getLocalUser();
        if (verifyAdmin(user)) {
            return true;
        }
        long userId = user.getId();
        String permission = meta.getPermission();
        String module = meta.getModule();
        List<PermissionDo> permissions = userService.getUserPermissions(userId);
        boolean matched = permissions.stream().anyMatch(it -> it.getModule().equals(module) && it.getName().equals(permission));
        if (!matched) {
            throw new AuthenticationException("you don't have the permission to access", 10001);
        }
        return true;
    }

    @Override
    public boolean handleAdmin(HttpServletRequest request, HttpServletResponse response, MetaInfo meta) {
        handleLogin(request, response, meta);
        UserDo user = LocalUser.getLocalUser();
        if (!verifyAdmin(user)) {
            throw new AuthenticationException("you don't have the permission to access", 10001);
        }
        return true;
    }


    @Override
    public boolean handleRefresh(HttpServletRequest request, HttpServletResponse response, MetaInfo meta) {
        String tokenStr = verifyHeader(request, response);
        Map<String, Claim> claims;
        try {
            claims = jwt.decodeRefreshToken(tokenStr);
        } catch (TokenExpiredException e) {
            throw new com.horaoen.sailor.autoconfigure.exception.TokenExpiredException(e.getMessage(), 10051);
        } catch (AlgorithmMismatchException | SignatureVerificationException | JWTDecodeException |
                 InvalidClaimException e) {
            throw new TokenInvalidException(e.getMessage(), 10041);
        }
        return getClaim(claims);
    }

    @Override
    public boolean handleNotHandlerMethod(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return true;
    }

    @Override
    public void handleAfterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // ?????????????????????????????????????????????????????? ThreadLocal ??????????????????
        LocalUser.clearLocalUser();
    }

    private boolean getClaim(Map<String, Claim> claims) {
        if (claims == null) {
            throw new TokenInvalidException("token is invalid, can't be decode", 10041);
        }
        Long identity = claims.get("identity").asLong();
        UserDo user = userService.getUserByUserId(identity);
        if (user == null) {
            throw new NotFoundException("user is not found", 10021);
        }
        String avatarUrl;
        if (user.getAvatar() == null) {
            avatarUrl = null;
        } else if (user.getAvatar().startsWith("http")) {
            avatarUrl = user.getAvatar();
        } else {
            avatarUrl = domain + servePath.split("/")[0] + "/" + user.getAvatar();
        }
        user.setAvatar(avatarUrl);
        LocalUser.setLocalUser(user);
        return true;
    }

    /**
     * ??????????????????????????????
     *
     * @param user ??????
     */
    private boolean verifyAdmin(UserDo user) {
        return groupService.checkIsRootByUserId(user.getId());
    }

    private String verifyHeader(HttpServletRequest request, HttpServletResponse response) {
        // ????????????header,??????access_token???????????????
        String authorization = request.getHeader(AUTHORIZATION_HEADER);
        if (authorization == null || Strings.isBlank(authorization)) {
            throw new AuthorizationException("authorization field is required", 10012);
        }
        String[] splits = authorization.split(" ");
        if (splits.length != 2) {
            throw new AuthorizationException("authorization field is invalid", 10013);
        }
        // Bearer ??????
        String scheme = splits[0];
        // token ??????
        String tokenStr = splits[1];
        if (!Pattern.matches(BEARER_PATTERN, scheme)) {
            throw new AuthorizationException("authorization field is invalid", 10013);
        }
        return tokenStr;
    }
}
