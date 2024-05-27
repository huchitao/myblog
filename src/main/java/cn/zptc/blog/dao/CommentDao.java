package cn.zptc.blog.dao;


import cn.zptc.blog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentDao {

    List<Comment> listParentCommentByBlogId(Long id);
    List<Comment> listChildCommentByParentId(@Param("blogId")Long blogId,@Param("parentId")Long parentId);
    int savaComment(Comment comment);
    Comment getCommentById(Long id);
}
