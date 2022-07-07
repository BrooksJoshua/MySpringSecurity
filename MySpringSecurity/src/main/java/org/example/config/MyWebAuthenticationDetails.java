package org.example.config;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-07-06 12:11
 */
public class MyWebAuthenticationDetails extends WebAuthenticationDetails {

    private boolean isPassed;

    public MyWebAuthenticationDetails(HttpServletRequest req) {
        super(req);
        String code = req.getParameter("code");
        String verifyCode = (String) req.getSession().getAttribute("verifyCode");
        if (code != null && verifyCode != null && code.equals(verifyCode)) {
            isPassed = true;
        }
        else{
            System.out.println("校验未通过:"+ isPassed);
            throw new AuthenticationServiceException("验证码错误");
        }
    }

    public boolean isPassed() {
        return isPassed;
    }
}

