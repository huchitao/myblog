package cn.zptc.blog.service;

import cn.zptc.blog.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentService {

    List<Comment> listParentCommentByBlogId(Long id);
    List<Comment> listChildCommentByParentId(Long blogId,Long parentId);
    int savaComment(Comment comment);
    Comment getCommentById(Long id);
}
