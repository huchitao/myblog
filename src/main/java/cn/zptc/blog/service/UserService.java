package cn.zptc.blog.service;

import cn.zptc.blog.entity.User;

public interface UserService {
    User checkUser(String username,String password);
}
