package org.example.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-07-02 22:39
 */
@Entity(name = "t_user")
public class User implements UserDetails {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 用户拥有的角色
     */
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    List<Role> roles;
    /**
     * username used to authenticate the user. Cannot be <code>null</code>.
     */
    private String username;
    /**
     * password used to authenticate the user.
     */
    private String password;
    /**
     * Indicates whether the user's account has expired. An expired account cannot be
     * authenticated.
     */
    private boolean accountNonExpired;
    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     */
    private boolean accountNonLocked;

    /**
     * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication.
     */
    private boolean credentialsNonExpired;
    /**
     * Indicates whether the user is enabled or disabled. A disabled user cannot be
     * authenticated.
     */
    private boolean enable;

    public boolean isAccountNonExpired(){
        return this.accountNonExpired;
    }

    public boolean isAccountNonLocked(){
        return this.accountNonLocked;
    }

    public boolean isCredentialsNonExpired(){
        return this.credentialsNonExpired;
    }

    public boolean isEnabled(){
        return this.enable;
    }

    /**
     * The authorities granted to the user. Cannot return null. sorted by natural key (never null)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role:getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public Long getId() {
        return id;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
