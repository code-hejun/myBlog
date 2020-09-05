package com.hj.blog.service;

import com.hj.blog.po.User;

public interface LoginService {

    User checkUser(String username , String password);
}
