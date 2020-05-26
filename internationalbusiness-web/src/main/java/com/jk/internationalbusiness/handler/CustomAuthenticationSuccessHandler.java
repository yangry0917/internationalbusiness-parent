package com.jk.internationalbusiness.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jk.internationalbusiness.common.util.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        //什么都不做的话，那就直接调用父类的方法
        //super.onAuthenticationSuccess(request, response, authentication);

        //这里可以根据实际情况，来确定是跳转到页面或者json格式。
        //如果是返回json格式，那么我们这么写
        RespBean.success("登录成功", authentication);
        /*Map<String,String> map=new HashMap<>();
        map.put("code", "200");
        map.put("msg", "登录成功");*/
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(RespBean.success("登录成功", authentication)));


        //如果是要跳转到某个页面的，比如我们的那个whoim的则
        //new DefaultRedirectStrategy().sendRedirect(request, response, "/whoim");

    }
}
