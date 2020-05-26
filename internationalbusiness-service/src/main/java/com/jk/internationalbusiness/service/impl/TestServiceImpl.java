package com.jk.internationalbusiness.service.impl;


import com.jk.internationalbusiness.dao.HelloMapper;
import com.jk.internationalbusiness.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private HelloMapper helloMapper;

    @Override
    public List<Map<String,String>> hello(){
        List<Map<String,String>> list = helloMapper.hello();
        return list;
    }

    @Override
    public List<Map<String,String>> helloWorld(){
        String id = "1";
        List<Map<String,String>> list = helloMapper.helloWorld(id);
        return list;
    }
}
