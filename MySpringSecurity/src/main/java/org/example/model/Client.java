package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author Joshua.H.Brooks
 * @description
 * 用来演示使用Mybatis作为持久层框架的例子中的实体类(等价于原来整合JPA例子中的User类)
 * 注意: 除了UserDetails里是必须的，其他都是用户可以自己定义添加的
 * @date 2022-07-05 10:32
 */
public class Client implements UserDetails {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 用户拥有的角色
     */
    List<Cos> coses;
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

    private String name;
    private String phone;
    private String telephone;
    private String address;
    private String userface;
    private String remarks;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public boolean isAccountNonExpired(){
        return this.accountNonExpired;
    }
    @Override
    public boolean isAccountNonLocked(){
        return this.accountNonLocked;
    }
    @Override
    public boolean isCredentialsNonExpired(){
        return this.credentialsNonExpired;
    }
    @Override
    public boolean isEnabled(){
        return this.enable;
    }

    public Long getId() {
        return id;
    }

    public List<Cos> getCoses() {
        return coses;
    }

    public boolean isEnable() {
        return enable;
    }
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities(){
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Cos cos:getCoses()) {
            authorities.add(new SimpleGrantedAuthority(cos.getName()));
        }
        return authorities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserface() {
        return userface;
    }

    public void setUserface(String userface) {
        this.userface = userface;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setCoses(List<Cos> coses) {
        this.coses = coses;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", coses=" + coses +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", enable=" + enable +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", userface='" + userface + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        return Objects.equals(this.getUsername(),((Client) obj).getUsername());
    }
}
