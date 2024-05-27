package cn.zptc.blog.dao;


import cn.zptc.blog.entity.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypeDao {
    int saveType(Type type);
    Type getTypeById(Long id);
    Type getTypeByName(String name);
    int deleteType(Long id);
    int updateType(Type type);
    List<Type> listType();
    List<Type> listTypeTop();
    List<Type> listTypeOrderByCount();
}
