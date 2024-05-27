package cn.zptc.blog.service.serviceImpl;

import cn.zptc.blog.dao.CommentDao;
import cn.zptc.blog.entity.Comment;
import cn.zptc.blog.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    private List<Comment> childComment=new ArrayList<>();

    @Override
    public List<Comment> listParentCommentByBlogId(Long id) {
        List<Comment> comments = commentDao.listParentCommentByBlogId(id);
        return eachComment(comments);
    }

    @Override
    public List<Comment> listChildCommentByParentId(Long blogId, Long parentId) {
        return commentDao.listChildCommentByParentId(blogId,parentId);
    }

    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> copyComments=new ArrayList<>();
        for (Comment c:comments) {
            Comment comment = new Comment();
            BeanUtils.copyProperties(c,comment);
            copyComments.add(comment);
        }
        combineChildren(copyComments);
        return copyComments;
    }

    private void combineChildren(List<Comment> copyComments) {
        for (Comment c:copyComments) {
            List<Comment> replyComments = c.getReplyComments();
            for(Comment replyComment:replyComments){
                recursively(replyComment);
            }
            c.setReplyComments(childComment);
            childComment=new ArrayList<>();
        }
    }

    private void recursively(Comment replyComment) {
        List<Comment> comments = listChildCommentByParentId(replyComment.getBlog().getId(), replyComment.getId());
        replyComment.setReplyComments(comments);
        if(!childComment.contains(replyComment)){
            childComment.add(replyComment);
        }
        if(replyComment.getReplyComments().size()>0){
            List<Comment> replyComments = replyComment.getReplyComments();
            for(Comment c:replyComments){
                childComment.add(c);
                if(c.getReplyComments().size()>0){
                    recursively(c);
                }
            }
        }
    }


    @Transactional
    @Override
    public int savaComment(Comment comment) {
        Long id = comment.getParentComment().getId();
        if(id==0){
            comment.setParentComment(null);
        }else{
            comment.setParentComment(getCommentById(id));
        }
        comment.setCreateTime(new Date());
        return commentDao.savaComment(comment);
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentDao.getCommentById(id);
    }
}
