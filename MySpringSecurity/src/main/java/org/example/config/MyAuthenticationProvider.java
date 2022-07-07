package org.example.config;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-07-05 23:31
 */
public class MyAuthenticationProvider extends DaoAuthenticationProvider {
    /** 下面是抽象类 AbstractUserDetailsAuthenticationProvider#additionalAuthenticationChecks()方法的注释。
     * Allows subclasses to perform any additional checks of a returned (or cached) UserDetails for a given authentication request.
     * Generally a subclass will at least compare the Authentication.getCredentials() with a UserDetails.getPassword().
     * If custom logic is needed to compare additional properties of UserDetails and/or UsernamePasswordAuthenticationToken,
     * these should also appear in this method.
     * @param userDetails
     * @param authentication
     * @throws AuthenticationException
     */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //用户前端输入的
        String code = request.getParameter("code");
        //后台生成的
        String verifyCode = (String) request.getSession().getAttribute("verifyCode");
        if(verifyCode == null || code == null || !code.equals(verifyCode)){
            throw new AuthenticationServiceException("验证么输入错误, 请注意大小写哦～");
        }
        //也可以这样调用 将校验逻辑放在MyWebAuthenticationDetails
//        if (!((MyWebAuthenticationDetails) authentication.getDetails()).isPassed()) {
//            throw new AuthenticationServiceException("沙雕 验证码错误啦");
//        }
        super.additionalAuthenticationChecks(userDetails, authentication);
    }
}
