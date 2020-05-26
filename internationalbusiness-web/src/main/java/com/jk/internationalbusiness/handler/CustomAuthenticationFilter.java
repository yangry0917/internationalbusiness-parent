package com.jk.internationalbusiness.handler;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    public CustomAuthenticationFilter() {
        //父类中定义了拦截的请求URL，/login的post请求，直接使用这个配置，也可以自己重写
        new AntPathRequestMatcher("/hello", "POST");
        //super();
        //添加了自定义的登陆失败处理器，配置文件中直接配置failureUrl没能直接生效
        //super.setAuthenticationFailureHandler(new LoginFailureHandler());
    }

    /**
     * 这里主要是把 request中的用户名和密码参数取出来，封装CustomAuthenticationToken，然后getAuthenticationManager().authenticate(authRequest)进行校验
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        if (!request.getMethod().equals(HttpMethod.POST.name())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String username = obtainUsername(request).trim();
        String password = obtainPassword(request).trim();
        final int userNameLen = 32;
        if (username.length() == 0 || username.length() > userNameLen || password.length() == 0
                || password.length() > userNameLen) {
            throw new BadCredentialsException("username or password is wrong!");
        }

        CustomAuthenticationToken authRequest = new CustomAuthenticationToken(username, password);
        setDetails(request, authRequest);
        Authentication authentication = getAuthenticationManager().authenticate(authRequest);
        return authentication;

    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        String username = super.obtainUsername(request);
        return username == null ? "" : username;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String obtainPassword(HttpServletRequest request) {
        String password = super.obtainPassword(request);
        return password == null ? "" : password;
    }

}


