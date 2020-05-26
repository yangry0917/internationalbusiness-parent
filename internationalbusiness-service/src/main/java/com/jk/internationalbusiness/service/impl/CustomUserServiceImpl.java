package com.jk.internationalbusiness.service.impl;



import com.jk.internationalbusiness.dao.CustomUserMapper;
import com.jk.internationalbusiness.entity.Role;
import com.jk.internationalbusiness.entity.User;
import com.jk.internationalbusiness.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service("customUserDetailsService")
public class CustomUserServiceImpl implements CustomUserService, UserDetailsService {
    @Autowired
    private CustomUserMapper customUserMapper;

    /*public  UserDetails findUserByName(String userName){
        return loadUserByUsername(userName);
    }*/

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        User user = customUserMapper.findUserByUsername(loginName);
        if(user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        //String pwd = new BCryptPasswordEncoder().encode(user.getPassword());
        String pwd = null;
        try {
            pwd = user.getPassword();
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setPassword(pwd);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
        for(Role role:user.getRoles())
        {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        user.setAuthorities(authorities);
        return user;
    }


}
