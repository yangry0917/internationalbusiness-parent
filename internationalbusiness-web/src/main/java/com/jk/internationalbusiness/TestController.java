package com.jk.internationalbusiness;


import com.jk.internationalbusiness.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TestController {
    @Autowired
    private TestService testService;


    @CrossOrigin
    @RequestMapping("/hello")
    // 返回json字符串的时候，采用这个注解
    //@ResponseBody
    public List hello(){
        List<Map<String,String>> list = testService.hello();
        return  list;
    }

    //跨域
    @CrossOrigin
    @RequestMapping("/helloWorld")
    // 返回json字符串的时候，采用这个注解
    //@ResponseBody
    public List helloWorld(){
        List<Map<String,String>> list = testService.helloWorld();
        return list;
    }
}
