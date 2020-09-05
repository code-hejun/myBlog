package com.hj.blog.service;

import com.hj.blog.common.MD5Utils;
import com.hj.blog.mapper.BlogMapper;
import com.hj.blog.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    BlogMapper blogMapper;

    @Override
    public User checkUser(String username, String password) {

        return blogMapper.checkUser(username, MD5Utils.code(password));
    }
}
