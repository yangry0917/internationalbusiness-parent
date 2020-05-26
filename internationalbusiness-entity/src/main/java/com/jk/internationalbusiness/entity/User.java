package com.jk.internationalbusiness.entity;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * 使用springSecurity用户类必须实现UserDetails
 */
@Data
@Entity
@Table(name = "tbl_user_info")
public class User implements UserDetails {

    public User(String username, String password, List<Role> roles) {
        this.userName = username;
        this.password = password;
        this.roles = roles;
    }

    @Column(name = "id", length = 50)
    private String id;

    @Column(name = "user_name", length = 20)
    private String userName;

    @Column(name = "sex", length = 1)
    private String sex;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "card_id", length = 25)
    private String cardId;

    @Column(name = "login_name", length = 20)
    private String loginName;

    @Column(name = "pwd", length = 60)
    private String password;

    @Column(name = "enable", length = 1)
    private Boolean enable;

    @Column(name = "unit_id", length = 1)
    private String unitId;

    @ManyToMany(targetEntity = Role.class, cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    private Collection<? extends GrantedAuthority> authorities;
    /**
     * 返回用户角色
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }

}
