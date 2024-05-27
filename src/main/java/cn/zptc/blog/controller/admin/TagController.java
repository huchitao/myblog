package cn.zptc.blog.controller.admin;


import cn.zptc.blog.constant.ConfigConstant;
import cn.zptc.blog.entity.Config;
import cn.zptc.blog.entity.Tag;
import cn.zptc.blog.entity.Type;
import cn.zptc.blog.service.ConfigService;
import cn.zptc.blog.service.serviceImpl.TagServiceImpl;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagServiceImpl tagService;

    @GetMapping("/tags")
    public String types(@RequestParam(value = "pn",defaultValue = "1") Integer pn, Model model){
        PageHelper.startPage(pn,5);
        List<Tag> tags = tagService.listTag();
        PageInfo pageInfo = new PageInfo(tags);
        model.addAttribute("pageInfo",pageInfo);
        Map<String, String> data = ConfigUtils.getInfo();
        model.addAllAttributes(data);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String tagInput(Model model){
        model.addAttribute("tag",new Tag());
        Map<String, String> data = ConfigUtils.getInfo();
        model.addAllAttributes(data);
        return "admin/tag-input";
    }

    @PostMapping("/tags")
    public String addType(@Valid Tag tag, BindingResult result, RedirectAttributes redirectAttributes){
        Tag t= tagService.getTagByName(tag.getName());
        if(t!=null){
            result.rejectValue("name","nameError","标签名称已存在！");
        }
        if(result.hasErrors()){
            return "admin/tag-input";
        }
            int i = tagService.saveTag(tag);
            if(i!=1){
                redirectAttributes.addFlashAttribute("message","新增失败！");
            }else {
                redirectAttributes.addFlashAttribute("message","新增成功！");
            }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/input")
    public String updateInput(@PathVariable Long id,Model model){
        Tag t = tagService.getTagById(id);
        model.addAttribute("tag",t);
        return "admin/tag-input";
    }

    @PostMapping("/tags/{id}")
    public String updateSaveTag(@Valid Tag tag, BindingResult result, RedirectAttributes redirectAttributes,@PathVariable Long id){
        Tag t= tagService.getTagByName(tag.getName());
        if(t!=null){
            result.rejectValue("name","nameError","标签名称已存在！");
        }
        if(result.hasErrors()){
            return "admin/tag-input";
        }

        int i = tagService.updateTag(id,tag);
        if(i!=1){
            redirectAttributes.addFlashAttribute("message","修改失败！");
        }else {
            redirectAttributes.addFlashAttribute("message","修改成功！");
        }
        return "redirect:/admin/tags";
    }


    @GetMapping("/tags/{id}/delete")
    public String deleteType(@PathVariable Long id,RedirectAttributes redirectAttributes){
        int i = tagService.deleteTag(id);
        if (i!=1){
            redirectAttributes.addFlashAttribute("message","删除失败！");
        }else {
            redirectAttributes.addFlashAttribute("message","删除成功！");
        }
        return "redirect:/admin/tags";
    }
}
