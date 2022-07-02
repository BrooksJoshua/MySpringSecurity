package org.example.controller;

import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-07-02 14:13
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/admin/hello")
    public String admin() {
        return "admin hello接口, 角色用户: \t" + getRoleAndUsername();
    }

    @GetMapping("/user/hello")
    public String user() {
        return "user hello接口, 角色用户: \t" + getRoleAndUsername();
    }

    @GetMapping("/guest/hello")
    public String guest() {
        return "guest hello接口, 角色用户: \t" + getRoleAndUsername();
    }


    String getRoleAndUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //list.stream().skip(list.size() - 1).findFirst().orElse("no last element");
        String role = authentication.getAuthorities().stream().skip(0).findFirst().orElseThrow(() -> new RuntimeException("Empty Collection")).getAuthority();
        if (role.startsWith("ROLE_")) {
            role = role.substring("ROLE_".length(), role.length());
        }
        String username = authentication.getName();
        return role + "\t" + username;
    }
}