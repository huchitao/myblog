package cn.zptc.blog.dao;

import cn.zptc.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    User findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);
}
