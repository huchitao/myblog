package cn.zptc.blog.service;

import cn.zptc.blog.entity.Tag;
import cn.zptc.blog.entity.Type;

import java.util.List;

public interface TagService {
    int saveTag(Tag tag);
    Tag getTagById(Long id);
    Tag getTagByName(String name);
    int deleteTag(Long id);
    int updateTag(Long id,Tag tag);
    List<Tag> listTag();
    List<Tag> listTagTop();
    List<Tag> listTagOrderByCount();
}
