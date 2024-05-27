package cn.zptc.blog.controller.admin;


import cn.zptc.blog.entity.User;
import cn.zptc.blog.service.serviceImpl.UserServiceImpl;
import cn.zptc.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController{

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession httpSession, RedirectAttributes redirectAttributes){
        User user = userService.checkUser(username, MD5Utils.code(password));
        if (user != null) {
            user.setPassword(null);
            httpSession.setAttribute("user",user);
            return "redirect:/admin/goIndex";
        }else {
            redirectAttributes.addFlashAttribute("message","用户名和密码错误！");
            return "redirect:/admin";
        }
    }

    @GetMapping("/goIndex")
    public String goIndex(){
        return "admin/index";
    }

    @GetMapping("/loginOut")
    public String loginOut(HttpSession httpSession){
        httpSession.removeAttribute("user");
        return "redirect:/admin";
    }
}
