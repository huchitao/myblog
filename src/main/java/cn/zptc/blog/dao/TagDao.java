package cn.zptc.blog.dao;


import cn.zptc.blog.entity.Tag;
import cn.zptc.blog.entity.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagDao {
    int saveTag(Tag tag);
    Tag getTagById(Long id);
    Tag getTagByName(String name);
    int deleteTag(Long id);
    int updateTag(Tag tag);
    List<Tag> listTag();
    List<Tag> listTagTop();
    List<Tag> listTagOrderByCount();
}
