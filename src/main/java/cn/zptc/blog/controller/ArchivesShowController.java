package cn.zptc.blog.controller;

import cn.zptc.blog.constant.ConfigConstant;
import cn.zptc.blog.entity.Blog;
import cn.zptc.blog.entity.Config;
import cn.zptc.blog.service.ConfigService;
import cn.zptc.blog.service.serviceImpl.BlogServiceImpl;
import cn.zptc.blog.util.ConfigUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ArchivesShowController {

    @Autowired
    private BlogServiceImpl blogService;

    @GetMapping("/archives")
    public String archives(Model model){
        Map<Integer, List<Blog>> map = blogService.archivesBlog();
        Integer blogCount = blogService.getBlogCount();
        model.addAttribute("archives",map);
        model.addAttribute("blogCount",blogCount);
        Map<String, String> data = ConfigUtils.getInfo();
        model.addAllAttributes(data);
        return "archives";
    }
}
