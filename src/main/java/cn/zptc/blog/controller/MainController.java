package cn.zptc.blog.controller;


import cn.zptc.blog.constant.ConfigConstant;
import cn.zptc.blog.entity.Blog;
import cn.zptc.blog.entity.Config;
import cn.zptc.blog.entity.Tag;
import cn.zptc.blog.entity.Type;
import cn.zptc.blog.exception.NotFoundException;
import cn.zptc.blog.service.ConfigService;
import cn.zptc.blog.service.serviceImpl.BlogServiceImpl;
import cn.zptc.blog.service.serviceImpl.TagServiceImpl;
import cn.zptc.blog.service.serviceImpl.TypeServiceImpl;
import cn.zptc.blog.util.ConfigUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired
    private BlogServiceImpl blogService;

    @Autowired
    private TypeServiceImpl typeService;

    @Autowired
    private TagServiceImpl tagService;


    @GetMapping("/")
    public String index(@RequestParam(value = "pn",defaultValue ="1") Integer pn ,Model model){
        PageHelper.startPage(pn,5);
        List<Blog> blogs = blogService.listBlog(null);
        PageInfo pageInfoBlog = new PageInfo(blogs, 1);
        model.addAttribute("blogs",pageInfoBlog);
        model.addAttribute("types",typeService.listTypeTop());
        model.addAttribute("tags",tagService.listTagTop());
        model.addAttribute("newBlogs",blogService.listNewBlog(6));
        Map<String, String> data = ConfigUtils.getInfo();
        model.addAllAttributes(data);
        return "main";
    }
    @GetMapping("/search")
    public String search(@RequestParam(value = "pn",defaultValue ="1")Integer pn,@RequestParam("query")String query,Model model){
        PageHelper.startPage(pn,5);
        List<Blog> blogs = blogService.queryBlogs(query);
        PageInfo pageInfoBlog = new PageInfo(blogs, 1);
        model.addAttribute("blogs",pageInfoBlog);
        model.addAttribute("query",query);
        Map<String, String> data = ConfigUtils.getInfo();
        model.addAllAttributes(data);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String goDetail(@PathVariable("id")Integer id,Model model){
        Blog b = blogService.getBlogtoDetail(Long.valueOf(id));
        model.addAttribute("blog",b);
        Map<String, String> data = ConfigUtils.getInfo();
        model.addAllAttributes(data);
        return "blog";
    }

    @GetMapping("/footer/blog")
    public String footerBlogs(Model model){
        Map<String, String> data = ConfigUtils.getInfo();
        model.addAttribute("footerNewBlogs",blogService.listNewBlog(3));
        model.addAllAttributes(data);
        return "_fragment :: footerListBlog";
    }
}
