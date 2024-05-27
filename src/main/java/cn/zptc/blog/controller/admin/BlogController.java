package cn.zptc.blog.controller.admin;


import cn.zptc.blog.constant.ConfigConstant;
import cn.zptc.blog.entity.*;
import cn.zptc.blog.service.ConfigService;
import cn.zptc.blog.service.serviceImpl.BlogServiceImpl;
import cn.zptc.blog.service.serviceImpl.TagServiceImpl;
import cn.zptc.blog.service.serviceImpl.TypeServiceImpl;
import cn.zptc.blog.service.serviceImpl.UserServiceImpl;
import cn.zptc.blog.util.ConfigUtils;
import cn.zptc.blog.util.MD5Utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogServiceImpl blogService;

    @Autowired
    private TypeServiceImpl typeService;

    @Autowired
    private TagServiceImpl tagService;

    @GetMapping("/blogs")
    public String blog(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){
        PageHelper.startPage(pn,5);
        List<Blog> blogs = blogService.listBlog(null);
        PageInfo pageInfo = new PageInfo(blogs);
        model.addAttribute("pageInfo",pageInfo);
        List<Type> types = typeService.listType();
        model.addAttribute("types",types);
        Map<String, String> data = ConfigUtils.getInfo();
        model.addAllAttributes(data);
        return "admin/blog";
    }

    @PostMapping("/blogs/SearchAll")
    public String blogSearchAll(@RequestParam(value = "pn",defaultValue = "1") Integer pn, Model model){
        PageHelper.startPage(pn,5);
        List<Blog> blogs = blogService.listBlog(null);
        PageInfo pageInfo = new PageInfo(blogs);
        model.addAttribute("pageInfo",pageInfo);
        Map<String, String> data = ConfigUtils.getInfo();
        model.addAllAttributes(data);
        return "admin/blog :: blogs";
    }

    @PostMapping("/blogs/search")
    public String blogSearch(@RequestParam(value = "pn",defaultValue = "1") Integer pn,String title,Integer typeId,String recommed,Model model){
        Blog blog = new Blog();
        if(!title.isEmpty()){
            String trimTitle= title.trim();
            blog.setTitle(trimTitle);
        }
        if(typeId!=null){
            Type type = new Type();
            Long type_id = Long.valueOf(typeId);
            type.setId(type_id);
            blog.setType(type);
        }
        int isRecommed;
        if(recommed.equals("true")){
            isRecommed=1;
        }else{
            isRecommed=0;
        }
        blog.setRecommend(isRecommed);

        PageHelper.startPage(pn,5);
        List<Blog> blogs = blogService.listBlog(blog);
        PageInfo pageInfo = new PageInfo(blogs);
        model.addAttribute("pageInfo",pageInfo);
        Map<String, String> data = ConfigUtils.getInfo();
        model.addAllAttributes(data);
        return "admin/blog :: blogs";
    }

    @GetMapping("/blogs/input")
    public String blogInput(Model model){
        List<Type> types = typeService.listType();
        List<Tag> tags = tagService.listTag();
        model.addAttribute("types",types);
        model.addAttribute("tags",tags);
        model.addAttribute("blog",new Blog());
        Map<String, String> data = ConfigUtils.getInfo();
        model.addAllAttributes(data);
        return "admin/blog-input";
    }

    @PostMapping("/blogs/input")
    public String blogInput(Blog blog, HttpSession httpSession, RedirectAttributes redirectAttributes){
        if(blog.getId()!=null){
            int i = blogService.updateBlog(blog.getId(), blog);
            if(i==1){
                redirectAttributes.addFlashAttribute("message","修改成功！");
            }else {
                redirectAttributes.addFlashAttribute("message","修改失败！");
            }
        }else{
            User user = (User) httpSession.getAttribute("user");
            blog.setUser(user);
            int i = blogService.savaBlog(blog);
            if(i==1){
                redirectAttributes.addFlashAttribute("message","添加成功！");
            }else {
                redirectAttributes.addFlashAttribute("message","添加失败！");
            }
        }

        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/input")
    public String goEditBlog(@PathVariable("id")Integer id,Model model){
        List<Type> types = typeService.listType();
        List<Tag> tags = tagService.listTag();
        model.addAttribute("types",types);
        model.addAttribute("tags",tags);
        model.addAttribute("blog",blogService.getBlogById(Long.valueOf(id)));
        Map<String, String> data = ConfigUtils.getInfo();
        model.addAllAttributes(data);
        return "admin/blog-input";
    }

    @GetMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable("id")Integer id,RedirectAttributes redirectAttributes){
        int i = blogService.deleteBlog(Long.valueOf(id));
        if (i==1){
            redirectAttributes.addFlashAttribute("message","删除成功！");
        }else {
            redirectAttributes.addFlashAttribute("message","删除失败！");
        }
        return "redirect:/admin/blogs";
    }
}
