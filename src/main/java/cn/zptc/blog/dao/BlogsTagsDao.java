package cn.zptc.blog.dao;

import cn.zptc.blog.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface BlogsTagsDao {
    void addTags(@Param("tagIds")List<Long> tag_id,@Param("blogId")Long blog_id);
    void deleteTags(Long blogId);
    String getAllBlogIds(Long id);
}