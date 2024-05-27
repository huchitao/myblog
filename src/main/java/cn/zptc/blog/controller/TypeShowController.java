package cn.zptc.blog.controller;


import cn.zptc.blog.constant.ConfigConstant;
import cn.zptc.blog.entity.Blog;
import cn.zptc.blog.entity.Config;
import cn.zptc.blog.entity.Type;
import cn.zptc.blog.service.ConfigService;
import cn.zptc.blog.service.serviceImpl.BlogServiceImpl;
import cn.zptc.blog.service.serviceImpl.TypeServiceImpl;
import cn.zptc.blog.util.ConfigUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class TypeShowController {


    @Autowired
    private TypeServiceImpl typeService;

    @Autowired
    private BlogServiceImpl blogService;




    @GetMapping("/types/{id}")
    public String goType(@PathVariable("id")Long id, @RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){
        List<Type> types = typeService.listTypeOrderByCount();
        if(id==-1){
            if (types!=null&&types.size()!=0){
                id=types.get(0).getId();
            }
        }
        Type type = new Type();
        type.setId(id);
        Blog blog = new Blog();
        blog.setType(type);
        PageHelper.startPage(pn,3);
        List<Blog> blogs = blogService.listBlog(blog);
        PageInfo pageInfo = new PageInfo(blogs);
        model.addAttribute("blogs",pageInfo);
        model.addAttribute("types",types);
        model.addAttribute("currentTypeId",id);
        Map<String, String> data = ConfigUtils.getInfo();
        model.addAllAttributes(data);
        return "types";
    }
}
