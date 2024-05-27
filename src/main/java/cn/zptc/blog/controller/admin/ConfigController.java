package cn.zptc.blog.controller.admin;


import cn.hutool.core.io.FileUtil;
import cn.zptc.blog.constant.ConfigConstant;
import cn.zptc.blog.entity.Config;
import cn.zptc.blog.service.ConfigService;
import cn.zptc.blog.util.ConfigUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/config")
    public String blog(Model model){
        Map<String, String> data = ConfigUtils.getInfo();
        model.addAllAttributes(data);
        return "admin/config";
    }

    @PostMapping(value = "/updateMess")
    public String updateMess(@RequestParam(value = "updateValue") String updateValue,
                             @RequestParam(value = "type")Integer type) throws IOException {
        configService.updateMess(updateValue,type);
        return "redirect:/admin/config";
    }

    @PostMapping(value = "/upload")
    public String upload(@RequestPart(value = "file") MultipartFile multipartFile,
                         @RequestParam(value = "type")Integer type) throws IOException {
        configService.upload(multipartFile,type);
        return "redirect:/admin/config";
    }

}
