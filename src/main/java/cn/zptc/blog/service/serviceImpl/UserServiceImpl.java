package cn.zptc.blog.service.serviceImpl;

import cn.zptc.blog.dao.UserDao;
import cn.zptc.blog.entity.User;
import cn.zptc.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        return userDao.findByUsernameAndPassword(username,password);
    }
}
