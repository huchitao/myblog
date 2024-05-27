package cn.zptc.blog.controller.admin;


import cn.zptc.blog.constant.ConfigConstant;
import cn.zptc.blog.entity.Config;
import cn.zptc.blog.entity.Type;
import cn.zptc.blog.service.ConfigService;
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
public class TypeController {

    @Autowired
    private TypeServiceImpl typeService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/types")
    public String types(@RequestParam(value = "pn",defaultValue = "1") Integer pn, Model model){
        PageHelper.startPage(pn,5);
        List<Type> types = typeService.listType();
        PageInfo pageInfo = new PageInfo(types);
        model.addAttribute("pageInfo",pageInfo);
        Map<String, String> data = ConfigUtils.getInfo();
        model.addAllAttributes(data);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String typeInput(Model model){
        model.addAttribute("type",new Type());
        Map<String, String> data = ConfigUtils.getInfo();
        model.addAllAttributes(data);
        return "admin/type-input";
    }

    @PostMapping("/types")
    public String addType(@Valid Type type, BindingResult result, RedirectAttributes redirectAttributes){
        Type t= typeService.getTypeByName(type.getName());
        if(t!=null){
            result.rejectValue("name","nameError","分类名称已存在！");
        }
        if(result.hasErrors()){
            return "admin/type-input";
        }
            int i = typeService.saveType(type);
            if(i!=1){
                redirectAttributes.addFlashAttribute("message","新增失败！");
            }else {
                redirectAttributes.addFlashAttribute("message","新增成功！");
            }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/input")
    public String updateInput(@PathVariable Long id,Model model){
        Type t = typeService.getTypeById(id);
        model.addAttribute("type",t);
        Map<String, String> data = ConfigUtils.getInfo();
        model.addAllAttributes(data);
        return "admin/type-input";
    }

    @PostMapping("/types/{id}")
    public String updateSaveType(@Valid Type type, BindingResult result, RedirectAttributes redirectAttributes,@PathVariable Long id){
        Type t= typeService.getTypeByName(type.getName());
        if(t!=null){
            result.rejectValue("name","nameError","分类名称已存在！");
        }
        if(result.hasErrors()){
            return "admin/type-input";
        }

        int i = typeService.updateType(id,type);
        if(i!=1){
            redirectAttributes.addFlashAttribute("message","修改失败！");
        }else {
            redirectAttributes.addFlashAttribute("message","修改成功！");
        }
        return "redirect:/admin/types";
    }


    @GetMapping("/types/{id}/delete")
    public String deleteType(@PathVariable Long id,RedirectAttributes redirectAttributes){
        int i = typeService.deleteType(id);
        if (i!=1){
            redirectAttributes.addFlashAttribute("message","删除失败！");
        }else {
            redirectAttributes.addFlashAttribute("message","删除成功！");
        }
        return "redirect:/admin/types";
    }
}
