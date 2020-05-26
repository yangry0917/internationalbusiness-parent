package com.jk.internationalbusiness.handler;


import com.jk.internationalbusiness.service.impl.CustomUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomUserServiceImpl customUserService;

    // 根用户拥有全部的权限
    //private final List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("CAN_SEARCH"), new SimpleGrantedAuthority("CAN_EXPORT"), new SimpleGrantedAuthority("CAN_IMPORT"), new SimpleGrantedAuthority("CAN_BORROW"), new SimpleGrantedAuthority("CAN_RETURN"), new SimpleGrantedAuthority("CAN_REPAIR"), new SimpleGrantedAuthority("CAN_DISCARD"), new SimpleGrantedAuthority("CAN_EMPOWERMENT"), new SimpleGrantedAuthority("CAN_BREED"));

    //这里通过用户名和密码的检查匹配判断登陆是否成功。
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication.isAuthenticated()) {
            return authentication;
        }
        //获取表单输入的用户名
        String username = authentication.getName();
        UserDetails user = null;
        try {
            user = customUserService.loadUserByUsername(username);
        } catch (Exception e) {
            throw new BadCredentialsException("该用户不存在，用户名: " + username);
        }
        if (user == null) {
            throw new BadCredentialsException("该用户不存在，用户名: " + username);
        }
        String password;
        try {
            //获取表单输入的密码
            password = (String)authentication.getCredentials();
        } catch (Exception e) {
            throw new BadCredentialsException("密码错误");
        }
        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("密码错误");
        }

        //这个是自定义用户登陆信息
        /*SecurityUser securityUser = new SecurityUser();
        securityUser.setUsername(user.getUsername());
        securityUser.setPassword(user.getPassword());
        securityUser.setUid(user.getUid());*/

        //return new CustomAuthenticationToken(securityUser, authentication.getCredentials(), authorities);
        // Allow subclasses to set the "details" property
        return new CustomAuthenticationToken(user, authentication.getCredentials(),user.getAuthorities());
    }


    //这里定义provider是否被调用，需要执行结果为true才会执行验证逻辑
    @Override
    public boolean supports(Class<?> authentication) {
        //return true;
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}

