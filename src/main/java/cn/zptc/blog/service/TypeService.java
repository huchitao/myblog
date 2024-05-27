package cn.zptc.blog.service;

import cn.zptc.blog.entity.Type;

import java.util.List;

public interface TypeService {
    int saveType(Type type);
    Type getTypeById(Long id);
    int deleteType(Long id);
    Type getTypeByName(String name);
    int updateType(Long id,Type type);
    List<Type> listType();
    List<Type> listTypeTop();
    List<Type> listTypeOrderByCount();
}
