package cn.zptc.blog.controller;


import cn.zptc.blog.constant.ConfigConstant;
import cn.zptc.blog.entity.Config;
import cn.zptc.blog.service.ConfigService;
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
public class AboutShowController {


    @GetMapping("/about")
    public String about(Model model){
        Map<String, String> data = ConfigUtils.getInfo();
        model.addAllAttributes(data);
        return "about";
    }
}
