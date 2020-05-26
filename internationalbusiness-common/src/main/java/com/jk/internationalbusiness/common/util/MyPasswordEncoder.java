package com.jk.internationalbusiness.common.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 1、主要对密码进行加密处理；
 * 2、用户传递过来的密码和数据库密码进行比对
 */
@Component
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        //加密方法可以根据自己的需要修改
        return new BCryptPasswordEncoder().encode(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        System.out.println(encode(charSequence).equals(s));
        return encode(charSequence).equals(s) ;
    }
}
