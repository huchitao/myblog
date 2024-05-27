package cn.zptc.blog.dao;


import cn.zptc.blog.entity.Blog;
import cn.zptc.blog.entity.Config;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConfigDao {
    Integer updateValue(@Param("key") String key, @Param("value") String value);

    List<Config> getAll();

}
