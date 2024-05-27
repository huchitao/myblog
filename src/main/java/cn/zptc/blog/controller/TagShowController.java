package cn.zptc.blog.controller;


import cn.zptc.blog.constant.ConfigConstant;
import cn.zptc.blog.entity.Blog;
import cn.zptc.blog.entity.Config;
import cn.zptc.blog.entity.Tag;
import cn.zptc.blog.entity.Type;
import cn.zptc.blog.service.ConfigService;
import cn.zptc.blog.service.serviceImpl.BlogServiceImpl;
import cn.zptc.blog.service.serviceImpl.TagServiceImpl;
import cn.zptc.blog.service.serviceImpl.TypeServiceImpl;
import cn.zptc.blog.util.ConfigUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class TagShowController {


    @Autowired
    private TagServiceImpl tagService;

    @Autowired
    private BlogServiceImpl blogService;


    @GetMapping("/tags/{id}")
    public String goTag(@PathVariable("id")Long id, @RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){
        List<Tag> tags = tagService.listTagOrderByCount();
        if(id==-1){
            if(tags!=null&&tags.size()!=0){
                id=tags.get(0).getId();
            }
        }
        model.addAttribute("tags",tags);
        model.addAttribute("currentTagId",id);

        PageHelper.startPage(pn,3);
        List<Blog> blogs = blogService.listBlogByTagId(id);
        PageInfo pageInfo = new PageInfo(blogs);
        model.addAttribute("blogs",pageInfo);
        Map<String, String> data = ConfigUtils.getInfo();
        model.addAllAttributes(data);
        return "tags";
    }
}
