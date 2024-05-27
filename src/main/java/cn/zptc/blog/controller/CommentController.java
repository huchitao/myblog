package cn.zptc.blog.controller;


import cn.zptc.blog.entity.Blog;
import cn.zptc.blog.entity.Comment;
import cn.zptc.blog.entity.User;
import cn.zptc.blog.service.serviceImpl.BlogServiceImpl;
import cn.zptc.blog.service.serviceImpl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private BlogServiceImpl blogService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{blogId}")
    public String getComment(@PathVariable("blogId")Long id, Model model){
        List<Comment> comments = commentService.listParentCommentByBlogId(id);
        model.addAttribute("comments",comments);
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String savaComment(Comment comment, HttpSession httpSession){
        Long id = comment.getBlog().getId();
        Blog b= blogService.getBlogById(id);
        comment.setBlog(b);
        User user = (User) httpSession.getAttribute("user");
        if (user!=null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else{
            comment.setAvatar(avatar);
            comment.setAdminComment(false);
        }
        commentService.savaComment(comment);
        return "redirect:/comments/"+id;
    }
}
